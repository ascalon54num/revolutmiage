package fr.univlorraine.miage.revolutmiage.mappers;

import fr.univlorraine.miage.revolutmiage.entities.Operation;
import fr.univlorraine.miage.revolutmiage.entities.dtos.NewOperation;
import fr.univlorraine.miage.revolutmiage.entities.dtos.OperationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;


@Mapper(uses = {CompteMapper.class}, componentModel = "spring", imports = {Instant.class, LocalDateTime.class, ZoneId.class})
public interface OperationsMapper {
    @Mapping(target = "date", expression = "java(entity.getDate().toString())")
    @Mapping(target = "id", expression = "java(entity.getId().toString())")
    @Mapping(target = "compteCrediteur", expression = "java(entity.getCompteCrediteur().getId().toString())")
    OperationDto toDto(Operation entity);

    default List<OperationDto> toDto(Iterable<Operation> comptes) {
        var result = new ArrayList<OperationDto>();
        comptes.forEach(op -> result.add(toDto(op)));

        return result;
    }

    @Mapping(target = "date", expression = "java(LocalDateTime.parse(dto.getDate()).atZone( ZoneId.of( \"France\")).toInstant())")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "compteCrediteur", ignore = true)
    Operation toObject(NewOperation dto);
}
