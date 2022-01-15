package fr.univlorraine.miage.revolutmiage.entities.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
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
    private Double montant; //(à convertir dans la devise du pays du compte)

    private Double tauxApplique;
    @NotNull
    @NotBlank
    private String compteCrediteur;
    private String nomCompteCrediteur;
    private String categorie; //(personne, restaurant, transport, commerces, énergies, communication...)
    @NotNull
    @NotBlank
    private String pays;
}
