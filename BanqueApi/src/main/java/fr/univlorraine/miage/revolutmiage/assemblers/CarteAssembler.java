package fr.univlorraine.miage.revolutmiage.assemblers;

import fr.univlorraine.miage.revolutmiage.controllers.CarteController;
import fr.univlorraine.miage.revolutmiage.controllers.CompteController;
import fr.univlorraine.miage.revolutmiage.entities.dtos.CarteDto;
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
public class CarteAssembler implements RepresentationModelAssembler<CarteDto, EntityModel<CarteDto>> {

    @Override
    public EntityModel<CarteDto> toModel(CarteDto carte) {
        return EntityModel.of(carte,
                linkTo(methodOn(CarteController.class)
                        .getOneCarte(carte.getId())).withSelfRel(),
                linkTo(methodOn(CompteController.class)
                        .getOneCompte(carte.getCompte())).withRel("compte"),
                linkTo(methodOn(CarteController.class)
                        .getAllCartes()).withRel("collection"));
    }

    @Override
    public CollectionModel<EntityModel<CarteDto>> toCollectionModel(Iterable<? extends CarteDto> entities) {
        List<EntityModel<CarteDto>> carteModel = StreamSupport
                .stream(entities.spliterator(), false)
                .map(i -> toModel(i))
                .collect(Collectors.toList());
        return CollectionModel.of(carteModel,
                linkTo(methodOn(CarteController.class)
                        .getAllCartes()).withSelfRel());
    }
}
