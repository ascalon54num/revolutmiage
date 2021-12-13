package fr.univlorraine.miage.revolutmiage.entities.dtos;

import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class CarteDto {
    @NotNull
    @NotBlank
    private String id;
    @NotNull
    @NotBlank
    private String numCarte;
    @NotNull
    @NotBlank
    @Size(min=4, max=4)
    @Pattern(regexp = "^[0-9]{4}")
    private String code;
    @NotNull
    @NotBlank
    @Size(min=4, max=4)
    @Pattern(regexp = "^[0-9]{3}")
    private String cryptogramme;
    @NotNull
    @NotBlank
    private boolean bloquee;
    @NotNull
    @NotBlank
    private boolean localisation;
    @NotBlank
    @Positive
    private Double  plafond;
    @NotNull
    @NotBlank
    private boolean sansContact;
    @NotNull
    @NotBlank
    private boolean virtuelle;
    @NotNull
    @NotBlank
    private String compte;
}
