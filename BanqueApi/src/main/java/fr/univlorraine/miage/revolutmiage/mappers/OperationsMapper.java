package fr.univlorraine.miage.revolutmiage.mappers;

import fr.univlorraine.miage.revolutmiage.entities.Operation;
import fr.univlorraine.miage.revolutmiage.entities.dtos.OperationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.time.Instant;
import java.time.LocalDateTime;


@Mapper(uses = {CompteMapper.class}, componentModel = "spring", imports = {Instant.class, LocalDateTime.class})
public interface OperationsMapper {
    @Mapping(target = "dateOperation", expression = "java(entity.getDateOperation().toString())")
    @Mapping(target = "compteCrediteur", source = "entity.compteCrediteur")
    OperationDto toDto(Operation entity);

    @Mapping(target = "dateOperation", expression = "java(LocalDateTime.parse(entity.getDateOperation()).toInstant())")
    @Mapping(target = "compteCrediteur", source = "entity.compteCrediteur")
    Operation toObject(OperationDto dto);
}
