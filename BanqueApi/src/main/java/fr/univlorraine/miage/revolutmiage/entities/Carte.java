package fr.univlorraine.miage.revolutmiage.entities;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class Carte {
    @Id
    private String id;
    private String numCarte;
    private String code;
    private String cryptogramme;
    private boolean bloquee;
    private boolean localisation;
    private Double  plafond;
    private boolean sansContact;
    private boolean virtuelle;
    @OneToOne
    private Compte compte;
}
