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
    private boolean bloquee;
    @NotNull
    private boolean localisation;
    @NotNull
    @Positive
    private Double  plafond;
    @NotNull
    private boolean sansContact;
    @NotNull
    private boolean virtuelle;
    @NotNull
    @NotBlank
    private String compte;
}
