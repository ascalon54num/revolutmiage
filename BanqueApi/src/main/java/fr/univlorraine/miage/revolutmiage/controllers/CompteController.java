package fr.univlorraine.miage.revolutmiage.controllers;

import fr.univlorraine.miage.revolutmiage.entities.Compte;
import fr.univlorraine.miage.revolutmiage.entities.dtos.CompteDto;
import fr.univlorraine.miage.revolutmiage.entities.dtos.NewCompte;
import fr.univlorraine.miage.revolutmiage.services.CompteService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("comptes")
@RequiredArgsConstructor
@ExposesResourceFor(Compte.class)
public class CompteController {
    private final CompteService compteService;
    @GetMapping
    public ResponseEntity<?> getAllComptes() {
        Iterable<CompteDto> allComptes = compteService.findAll();
        return ResponseEntity.ok(allComptes);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> saveCompte(@RequestBody NewCompte c){
        CompteDto saved = compteService.create(c);
        URI location = linkTo(CompteController.class).slash(saved.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
