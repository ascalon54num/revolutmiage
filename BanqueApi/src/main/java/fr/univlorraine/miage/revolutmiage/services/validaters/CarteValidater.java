package fr.univlorraine.miage.revolutmiage.services.validaters;

import fr.univlorraine.miage.revolutmiage.entities.dtos.NewCarte;
import fr.univlorraine.miage.revolutmiage.utils.DefaultValidater;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.Map;

@Service
public class CarteValidater extends DefaultValidater<NewCarte> {
    public CarteValidater(Validator validator) {
        super("carte", validator);
    }

    @Override
    protected void customValidate(Map<String, String> problems, NewCarte newCarte) {

    }
}
