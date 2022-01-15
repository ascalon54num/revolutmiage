package fr.univlorraine.miage.revolutmiage.controllers;

import fr.univlorraine.miage.revolutmiage.assemblers.OperationAssembler;
import fr.univlorraine.miage.revolutmiage.entities.Operation;
import fr.univlorraine.miage.revolutmiage.entities.dtos.NewOperation;
import fr.univlorraine.miage.revolutmiage.entities.dtos.OperationDto;
import fr.univlorraine.miage.revolutmiage.mappers.OperationMapper;
import fr.univlorraine.miage.revolutmiage.services.OperationService;
import fr.univlorraine.miage.revolutmiage.services.validaters.OperationValidater;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.MediaType;
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
@RequestMapping(value = "transactions", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@ExposesResourceFor(Operation.class)
public class OperationController {
    private final OperationService operationService;
    private final OperationAssembler assembler;
    private final OperationMapper mapper;
    private final OperationValidater validater;

    @GetMapping
    public ResponseEntity<?> getAllOperations() {
        Iterable<OperationDto> allOps = operationService.findAll();
        return ResponseEntity.ok(assembler.toCollectionModel(allOps));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> saveOperation(@RequestBody NewOperation op){
        OperationDto saved =operationService.create(op);
        URI location = linkTo(OperationController.class).slash(saved.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    @DeleteMapping(value = "/{opId}")
    @Transactional
    public ResponseEntity<?> deleteOperation(@PathVariable("opId") String opId){
        Optional<Operation> operation = operationService.findById(opId);
        if(operation.isPresent()){
            operationService.delete(operation.get());
        }
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/{opId}")
    @Transactional
    public ResponseEntity<?> updateComptePartiel(@PathVariable("opId") String opId,
                                                 @RequestBody Map<Object, Object> fields) {
        Optional<Operation> body = operationService.findById(opId);
        if (body.isPresent()) {
            Operation op = body.get();
            fields.forEach((f, v) -> {
                Field field = ReflectionUtils.findField(Operation.class, f.toString());
                field.setAccessible(true);
                ReflectionUtils.setField(field, op, v);
            });
            NewOperation newOperation = new NewOperation();
            newOperation.setLibelle(op.getLibelle());
            newOperation.setDate(op.getDate().toString());
            newOperation.setPays(op.getPays());
            newOperation.setCategorie(op.getCategorie());
            newOperation.setMontant(op.getMontant());
            newOperation.setTauxApplique(op.getTauxApplique());
            newOperation.setCompteCrediteurId(op.getCompteCrediteur().getId());
            newOperation.setNomCompteCrediteur(op.getNomCompteCrediteur());
            validater.validate(newOperation);
            op.setId(opId);
            operationService.update(op);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    @RolesAllowed("ROLE_USER")
    @GetMapping(value = "/{opId}")
    public ResponseEntity<?> getOneOperation(@PathVariable("opId") String id) {
        return Optional.ofNullable(operationService.findById(id)).filter(Optional::isPresent)
                .map(i -> ResponseEntity.ok(assembler.toModel(mapper.toDto(i.get()))))
                .orElse(ResponseEntity.notFound().build());
    }
}
