package fr.univlorraine.miage.revolutmiage.mappers;

import fr.univlorraine.miage.revolutmiage.entities.Carte;
import fr.univlorraine.miage.revolutmiage.entities.dtos.CarteDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarteMapper {
    CarteDto toDto(Carte entity);
    Carte toObject(CarteDto dto);
}
