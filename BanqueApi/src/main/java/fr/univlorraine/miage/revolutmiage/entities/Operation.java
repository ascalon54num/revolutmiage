package fr.univlorraine.miage.revolutmiage.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.Instant;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Operation {
    @Id
    private UUID id;
    private Instant date;
    private String libelle;
    private Float montant; //(à convertir dans la devise du pays du compte)
    private Float tauxApplique;
    @OneToOne
    private Compte compteCrediteur;
    private String nomCompteCrediteur;
    private String categorie; //(personne, restaurant, transport, commerces, énergies, communication...)
    private String pays;
}
