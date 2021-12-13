package fr.univlorraine.miage.revolutmiage.entities;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class Operation {
    @Id
    private UUID id;
    private LocalDate date;
    private String libelle;
    private Double montant; //(à convertir dans la devise du pays du compte)
    private Double tauxApplique;
    @OneToOne
    private Compte compteCrediteur;
    private String nomCompteCrediteur;
    private String categorie; //(personne, restaurant, transport, commerces, énergies, communication...)
    private String pays;
}
