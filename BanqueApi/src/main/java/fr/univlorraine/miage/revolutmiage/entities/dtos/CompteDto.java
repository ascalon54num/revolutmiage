package fr.univlorraine.miage.revolutmiage.entities.dtos;

import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class CompteDto {
    @NotNull
    @NotBlank
    private String id;
    @NotNull
    @NotBlank
    private String nom;

    @NotNull
    @NotBlank
    private String prenom;

    @NotNull
    @NotBlank
    private String pays;

    @NotNull
    @NotBlank
    @Size(min=9, max=9)
    @Pattern(regexp = "^[0-9]{2}[a-zA-Z]{2}[0-9]{5}$")
    @Accessors(chain = true)
    private String noPasseport;

    @Pattern(regexp = "^\\+[1-9]{1}[0-9]{3,14}$")
    private String numTel;

    @NotNull
    @NotBlank
    private String secret;

    @NotNull
    @NotBlank
    private String iban;
}
