package fr.univlorraine.miage.revolutmiage.controllers;

import fr.univlorraine.miage.revolutmiage.entities.Operation;
import fr.univlorraine.miage.revolutmiage.entities.dtos.NewOperation;
import fr.univlorraine.miage.revolutmiage.entities.dtos.OperationDto;
import fr.univlorraine.miage.revolutmiage.services.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "transactions", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@ExposesResourceFor(Operation.class)
public class OperationController {
    private final OperationService operationService;

    @GetMapping
    public ResponseEntity<?> getAllOperations() {
        Iterable<OperationDto> allOps = operationService.findAll();
        return ResponseEntity.ok(allOps);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> saveOperation(@RequestBody NewOperation op){
        OperationDto saved =operationService.create(op);
        URI location = linkTo(OperationController.class).slash(saved.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
