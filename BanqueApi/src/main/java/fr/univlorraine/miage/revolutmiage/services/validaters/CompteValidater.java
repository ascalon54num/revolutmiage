package fr.univlorraine.miage.revolutmiage.services.validaters;

import fr.univlorraine.miage.revolutmiage.entities.dtos.NewCompte;
import fr.univlorraine.miage.revolutmiage.utils.DefaultValidater;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.Map;

@Service
public class CompteValidater extends DefaultValidater<NewCompte> {
    public CompteValidater( Validator validator) {
        super("compte", validator);
    }

    @Override
    protected void customValidate(Map<String, String> problems, NewCompte newCompte) {

    }
}
