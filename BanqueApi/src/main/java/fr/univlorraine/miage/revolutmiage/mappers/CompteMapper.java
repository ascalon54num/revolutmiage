package fr.univlorraine.miage.revolutmiage.mappers;

import fr.univlorraine.miage.revolutmiage.entities.Compte;
import fr.univlorraine.miage.revolutmiage.entities.dtos.CompteDto;
import fr.univlorraine.miage.revolutmiage.entities.dtos.NewCompte;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface CompteMapper {
    CompteDto toDto(Compte entity);

    default List<CompteDto> toDto(Iterable<Compte> comptes) {
        var result = new ArrayList<CompteDto>();
        comptes.forEach(compte -> result.add(toDto(compte)));

        return result;
    }

    Compte toObject(NewCompte dto);
}
