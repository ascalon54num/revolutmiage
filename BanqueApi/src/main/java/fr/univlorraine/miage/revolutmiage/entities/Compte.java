package fr.univlorraine.miage.revolutmiage.entities;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class Compte {
    @Id
    private String id;
    private String nom;
    private String prenom;
    private String pays;
    private String noPasseport;
    private String numTel;
    private String secret;
    private String iban;
}
