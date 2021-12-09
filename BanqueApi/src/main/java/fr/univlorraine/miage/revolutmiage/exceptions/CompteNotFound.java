package fr.univlorraine.miage.revolutmiage.exceptions;

import fr.univlorraine.miage.revolutmiage.entities.dtos.NewCarte;
import fr.univlorraine.miage.revolutmiage.entities.dtos.NewOperation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Getter
@ToString
@RequiredArgsConstructor
public class CompteNotFound extends  RuntimeException{
    private final String id;

    public static CompteNotFound of(NewCarte nc) {
        return new CompteNotFound(nc.getCompteId());
    }
    public static CompteNotFound of(NewOperation no) {
        return new CompteNotFound(no.getCompteCrediteurId());
    }
}
