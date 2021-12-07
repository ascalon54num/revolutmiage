package fr.univlorraine.miage.revolutmiage.mappers;

import fr.univlorraine.miage.revolutmiage.entities.Compte;
import fr.univlorraine.miage.revolutmiage.entities.dtos.CompteDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompteMapper {
    CompteDto toDto(Compte entity);
    Compte toObject(CompteDto dto);
}
