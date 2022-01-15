package fr.univlorraine.miage.revolutmiage.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.ValidationException;
import java.util.Map;

@AllArgsConstructor
@Getter
public class InputValidationException extends ValidationException {
    private Map<String, String> problems;
}
