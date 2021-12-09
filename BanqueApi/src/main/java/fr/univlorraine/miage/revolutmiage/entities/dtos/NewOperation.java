package fr.univlorraine.miage.revolutmiage.entities.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class NewOperation {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "UTC")
    private String date;
    @NotNull
    @NotBlank
    private String libelle;
    @NotNull
    @NotBlank
    private Float montant; //(à convertir dans la devise du pays du compte)
    @NotNull
    @NotBlank
    private Float tauxApplique;
    @NotNull
    @NotBlank
    private String compteCrediteurId;
    private String nomCompteCrediteur;
    private String categorie; //(personne, restaurant, transport, commerces, énergies, communication...)
    private String pays;
}
