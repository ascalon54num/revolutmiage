package fr.univlorraine.miage.revolutmiage.controllers;

import fr.univlorraine.miage.revolutmiage.assembler.CarteAssembler;
import fr.univlorraine.miage.revolutmiage.entities.Carte;
import fr.univlorraine.miage.revolutmiage.entities.dtos.CarteDto;
import fr.univlorraine.miage.revolutmiage.entities.dtos.NewCarte;
import fr.univlorraine.miage.revolutmiage.services.CarteService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("cartes")
@RequiredArgsConstructor
@ExposesResourceFor(Carte.class)
public class CarteController {
    private final CarteService carteService;
    private final CarteAssembler assembler;

    @GetMapping
    public ResponseEntity<?> getAllCartes() {
        Iterable<CarteDto> allCartes = carteService.findAll();
        return ResponseEntity.ok(assembler.toCollectionModel(allCartes));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> saveCarte(@RequestBody NewCarte c){
        CarteDto saved = carteService.create(c);
        URI location = linkTo(CarteController.class).slash(saved.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    @GetMapping(value = "/{carteId}")
    public ResponseEntity<?> getOneCarte(@PathVariable("carteId") String id) {
        return Optional.ofNullable(carteService.findById(id)).filter(Optional::isPresent)
                .map(i -> ResponseEntity.ok(assembler.toModel(i.get())))
                .orElse(ResponseEntity.notFound().build());
    }
}
