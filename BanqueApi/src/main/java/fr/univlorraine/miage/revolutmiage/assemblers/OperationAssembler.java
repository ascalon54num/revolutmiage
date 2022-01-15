package fr.univlorraine.miage.revolutmiage.assemblers;

import fr.univlorraine.miage.revolutmiage.controllers.CompteController;
import fr.univlorraine.miage.revolutmiage.controllers.OperationController;
import fr.univlorraine.miage.revolutmiage.entities.dtos.OperationDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OperationAssembler implements RepresentationModelAssembler<OperationDto, EntityModel<OperationDto>> {

    @Override
    public EntityModel<OperationDto> toModel(OperationDto op) {
        return EntityModel.of(op,
                linkTo(methodOn(OperationController.class)
                        .getOneOperation(op.getId())).withSelfRel(),
                linkTo(methodOn(CompteController.class)
                        .getOneCompte(op.getCompteCrediteur())).withRel("compte"),
                linkTo(methodOn(OperationController.class)
                        .getAllOperations()).withRel("collection"));
    }

    @Override
    public CollectionModel<EntityModel<OperationDto>> toCollectionModel(Iterable<? extends OperationDto> entities) {
        List<EntityModel<OperationDto>> opModel = StreamSupport
                .stream(entities.spliterator(), false)
                .map(i -> toModel(i))
                .collect(Collectors.toList());
        return CollectionModel.of(opModel,
                linkTo(methodOn(OperationController.class)
                        .getAllOperations()).withSelfRel());
    }
}

