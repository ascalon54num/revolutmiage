package fr.univlorraine.miage.revolutmiage.entities.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class NewCarte {
    @NotNull
    @NotBlank
    @Pattern(regexp = "^[0-9]{16}")
    private String numCarte;
    @NotNull
    @NotBlank
    @Size(min=4, max=4)
    @Pattern(regexp = "^[0-9]{4}")
    private String code;
    @NotNull
    @NotBlank
    @Size(min=3, max=3)
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
    private String compteId;
}
