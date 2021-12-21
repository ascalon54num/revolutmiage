package fr.univlorraine.miage.revolutmiage.assembler;

import fr.univlorraine.miage.revolutmiage.controllers.CompteController;
import fr.univlorraine.miage.revolutmiage.entities.dtos.CompteDto;
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
public class CompteAssembler implements RepresentationModelAssembler<CompteDto, EntityModel<CompteDto>> {

    @Override
    public EntityModel<CompteDto> toModel(CompteDto compte) {
        return EntityModel.of(compte,
                linkTo(methodOn(CompteController.class)
                        .getOneCompte(compte.getId())).withSelfRel(),
                linkTo(methodOn(CompteController.class)
                        .getAllComptes()).withRel("collection"));
    }

    @Override
    public CollectionModel<EntityModel<CompteDto>> toCollectionModel(Iterable<? extends CompteDto> entities) {
        List<EntityModel<CompteDto>> compteModel = StreamSupport
                .stream(entities.spliterator(), false)
                .map(i -> toModel(i))
                .collect(Collectors.toList());
        return CollectionModel.of(compteModel,
                linkTo(methodOn(CompteController.class)
                        .getAllComptes()).withSelfRel());
    }
}
