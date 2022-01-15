package fr.univlorraine.miage.revolutmiage.utils.jwt.infra.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
public class JwtAuthenticationDTO {
    private String token;
}
