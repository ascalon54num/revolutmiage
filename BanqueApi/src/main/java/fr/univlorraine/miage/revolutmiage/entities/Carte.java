package fr.univlorraine.miage.revolutmiage.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Carte {
    @Id
    private UUID id;
    private String numCarte;
    private String code;
    private String cryptogramme;
    private boolean bloquee;
    private boolean localisation;
    private Double  plafond;
    private boolean sansContact;
    private boolean virtuelle;
    @JoinColumn(name = "compte_id", referencedColumnName = "id")
    @OneToOne
    private Compte compte;
}
