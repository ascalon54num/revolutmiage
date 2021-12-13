package fr.univlorraine.miage.revolutmiage.mappers;

import fr.univlorraine.miage.revolutmiage.entities.Operation;
import fr.univlorraine.miage.revolutmiage.entities.dtos.NewOperation;
import fr.univlorraine.miage.revolutmiage.entities.dtos.OperationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@Mapper(componentModel = "spring", imports = { LocalDate.class, DateTimeFormatter.class, Locale.class })
public interface OperationMapper {

    @Mapping(target = "date", expression = "java(entity.getDate().toString())")
    @Mapping(target = "id", expression = "java(entity.getId().toString())")
    @Mapping(target = "compteCrediteur", expression = "java(entity.getCompteCrediteur().getId().toString())")
    OperationDto toDto(Operation entity);

    default List<OperationDto> toDto(Iterable<Operation> ops) {
        var result = new ArrayList<OperationDto>();
        ops.forEach(op -> result.add(toDto(op)));

        return result;
    }

    @Mapping(target = "date", expression = "java(LocalDate.parse(dto.getDate(), DateTimeFormatter.ofPattern(\"yyyy-M-d\",Locale.FRANCE)))")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "compteCrediteur", ignore = true)
    Operation toObject(NewOperation dto);
}
