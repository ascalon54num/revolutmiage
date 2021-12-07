package fr.univlorraine.miage.revolutmiage.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Compte {
    @Id
    private String id;
    private String nom;
    private String prenom;
    private String pays;
    private String noPasseport;
    private String numTel;
    private String secret;
    private String IBAN;
}
