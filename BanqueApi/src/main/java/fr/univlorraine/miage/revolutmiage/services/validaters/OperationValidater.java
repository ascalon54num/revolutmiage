package fr.univlorraine.miage.revolutmiage.services.validaters;

import fr.univlorraine.miage.revolutmiage.entities.dtos.NewOperation;
import fr.univlorraine.miage.revolutmiage.utils.DefaultValidater;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.Map;

@Service
public class OperationValidater extends DefaultValidater<NewOperation> {

    public OperationValidater( Validator validator) {
        super("operation", validator);
    }

    @Override
    protected void customValidate(Map<String, String> problems, NewOperation newOperation) {

    }
}
