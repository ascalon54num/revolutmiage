package fr.univlorraine.miage.revolutmiage.controllers;

import fr.univlorraine.miage.revolutmiage.assemblers.CompteAssembler;
import fr.univlorraine.miage.revolutmiage.entities.Carte;
import fr.univlorraine.miage.revolutmiage.entities.Compte;
import fr.univlorraine.miage.revolutmiage.entities.Operation;
import fr.univlorraine.miage.revolutmiage.entities.dtos.CompteDto;
import fr.univlorraine.miage.revolutmiage.entities.dtos.NewCompte;
import fr.univlorraine.miage.revolutmiage.entities.dtos.NewCompte;
import fr.univlorraine.miage.revolutmiage.mappers.CompteMapper;
import fr.univlorraine.miage.revolutmiage.mappers.OperationMapper;
import fr.univlorraine.miage.revolutmiage.services.CompteService;
import fr.univlorraine.miage.revolutmiage.services.validaters.CompteValidater;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.net.URI;
import java.util.Map;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("comptes")
@RequiredArgsConstructor
@ExposesResourceFor(Compte.class)
public class CompteController {
    private final CompteService compteService;
    private final CompteAssembler assembler;
    private final CompteMapper mapper;
    private final CompteValidater validater;

    @GetMapping
    public ResponseEntity<?> getAllComptes() {
        Iterable<CompteDto> allComptes = compteService.findAll();
        return ResponseEntity.ok(assembler.toCollectionModel(allComptes));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> saveCompte(@RequestBody NewCompte c){
        CompteDto saved = compteService.create(c);
        URI location = linkTo(CompteController.class).slash(saved.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(value = "/{compteId}")
    @Transactional
    public ResponseEntity<?> deleteCompte(@PathVariable("compteId") String compteId){
        Optional<Compte> compte = compteService.findById(compteId);
        if(compte.isPresent()){
            compteService.delete(compte.get());
        }
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/{compteId}")
    @Transactional
    public ResponseEntity<?> updateComptePartiel(@PathVariable("compteId") String compteId,
                                                @RequestBody Map<Object, Object> fields) {
        Optional<Compte> body = compteService.findById(compteId);
        if (body.isPresent()) {
            Compte compte = body.get();
            fields.forEach((f, v) -> {
                Field field = ReflectionUtils.findField(Compte.class, f.toString());
                field.setAccessible(true);
                ReflectionUtils.setField(field, compte, v);
            });
            NewCompte newCompte = new NewCompte();
            newCompte.setNom(compte.getNom());
            newCompte.setPrenom(compte.getPrenom());
            newCompte.setPays(compte.getPays());
            newCompte.setIban(compte.getIban());
            newCompte.setSecret(compte.getSecret());
            newCompte.setNoPasseport(compte.getNoPasseport());
            newCompte.setNumTel(compte.getNumTel());
            validater.validate(newCompte);
            compte.setId(compteId);
            compteService.update(compte);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    @RolesAllowed("ROLE_USER")
    @GetMapping(value="/{compteId}")
    public ResponseEntity<?> getOneCompte(@PathVariable("compteId") String id) {
        return Optional.ofNullable(compteService.findById(id)).filter(Optional::isPresent)
                .map(i -> ResponseEntity.ok(assembler.toModel(mapper.toDto(i.get()))))
                .orElse(ResponseEntity.notFound().build());
    }
}
