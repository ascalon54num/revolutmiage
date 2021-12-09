package fr.univlorraine.miage.revolutmiage.entities.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OperationDto {
    @NotNull
    @NotBlank
    private String id;
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
    private String compteCrediteur;
    private String nomCompteCrediteur;
    private String categorie; //(personne, restaurant, transport, commerces, énergies, communication...)
    private String pays;
}
