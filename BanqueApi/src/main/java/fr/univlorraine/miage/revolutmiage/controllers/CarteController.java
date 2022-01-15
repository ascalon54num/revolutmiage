package fr.univlorraine.miage.revolutmiage.controllers;

import fr.univlorraine.miage.revolutmiage.assemblers.CarteAssembler;
import fr.univlorraine.miage.revolutmiage.entities.Carte;
import fr.univlorraine.miage.revolutmiage.entities.dtos.CarteDto;
import fr.univlorraine.miage.revolutmiage.entities.dtos.NewCarte;
import fr.univlorraine.miage.revolutmiage.entities.forms.CheckCarteForm;
import fr.univlorraine.miage.revolutmiage.mappers.CarteMapper;
import fr.univlorraine.miage.revolutmiage.services.CarteService;
import fr.univlorraine.miage.revolutmiage.services.validaters.CarteValidater;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.net.URI;
import java.util.Hashtable;
import java.util.Map;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("cartes")
@RequiredArgsConstructor
@ExposesResourceFor(Carte.class)
public class CarteController {
    private final CarteService carteService;
    private final CarteAssembler assembler;
    private final CarteMapper mapper;
    private final CarteValidater validater;

    @GetMapping
    public ResponseEntity<?> getAllCartes() {
        Iterable<CarteDto> allCartes = carteService.findAll();
        return ResponseEntity.ok(assembler.toCollectionModel(allCartes));
    }

    @PostMapping("/validate")
    public ResponseEntity<?> checkCarte(@RequestBody CheckCarteForm body){
        Optional<Carte> carte = carteService.findByNumCarte(body.getNumCarte());
        if(carte.isPresent()){
            Carte c = carte.get();
            if((body.getCode().trim()).equals(c.getCode().trim())){
                Hashtable res = new Hashtable();
                res.put("compteId",c.getCompte().getId());
                return ResponseEntity.accepted().body(res);
            }
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> saveCarte(@RequestBody NewCarte c){
        CarteDto saved = carteService.create(c);
        URI location = linkTo(CarteController.class).slash(saved.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(value = "/{carteId}")
    @Transactional
    public ResponseEntity<?> deleteOperation(@PathVariable("carteId") String carteId){
        Optional<Carte> carte = carteService.findById(carteId);
        if(carte.isPresent()){
            carteService.delete(carte.get());
        }
        return ResponseEntity.noContent().build();
    }

    // PATCH
    @PatchMapping(value = "/{carteId}")
    @Transactional
    public ResponseEntity<?> updateCartePartiel(@PathVariable("carteId") String carteId,
                                                      @RequestBody Map<Object, Object> fields) {
        Optional<Carte> body = carteService.findById(carteId);
        if (body.isPresent()) {
            Carte carte = body.get();
            fields.forEach((f, v) -> {
                Field field = ReflectionUtils.findField(Carte.class, f.toString());
                field.setAccessible(true);
                ReflectionUtils.setField(field, carte, v);
            });
            NewCarte newCarte = new NewCarte();
            newCarte.setNumCarte(carte.getNumCarte());
            newCarte.setCode(carte.getCode());
            newCarte.setCompteId(carte.getCompte().getId());
            newCarte.setBloquee(carte.isBloquee());
            newCarte.setCryptogramme(carte.getCryptogramme());
            newCarte.setLocalisation(carte.isLocalisation());
            newCarte.setSansContact(carte.isSansContact());
            newCarte.setVirtuelle(carte.isVirtuelle());
            newCarte.setPlafond(carte.getPlafond());
            validater.validate(newCarte);
            carte.setId(carteId);
            carteService.update(carte);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @RolesAllowed("ROLE_USER")
    @GetMapping(value = "/{carteId}")
    public ResponseEntity<?> getOneCarte(@PathVariable("carteId") String id) {
        return Optional.ofNullable(carteService.findById(id)).filter(Optional::isPresent)
                .map(i -> ResponseEntity.ok(assembler.toModel(mapper.toDto(i.get()))))
                .orElse(ResponseEntity.notFound().build());
    }
}
