package fr.univlorraine.miage.revolutmiage.mappers;

import fr.univlorraine.miage.revolutmiage.entities.Carte;
import fr.univlorraine.miage.revolutmiage.entities.dtos.CarteDto;
import fr.univlorraine.miage.revolutmiage.entities.dtos.NewCarte;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;


@Mapper(componentModel = "spring")
public interface CarteMapper {

    @Mapping(target = "compte", expression ="java(entity.getCompte().getId().toString())")
    CarteDto toDto(Carte entity);

    default List<CarteDto> toDto(Iterable<Carte> cartes) {
        var result = new ArrayList<CarteDto>();
        cartes.forEach(carte -> result.add(toDto(carte)));

        return result;
    }

    @Mapping(target = "compte", ignore = true)
    Carte toObject(NewCarte dto);
}
