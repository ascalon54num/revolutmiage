package fr.univlorraine.miage.revolutmiage.mappers;

import fr.univlorraine.miage.revolutmiage.entities.Compte;
import fr.univlorraine.miage.revolutmiage.entities.dtos.CompteDto;
import fr.univlorraine.miage.revolutmiage.entities.dtos.NewCompte;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class CompteMapperTest {

    public static final String NOM = "Dupont";
    public static final String IBAN = "FR761034328909432347";
    public static final String PRENOM = "Jean";
    public static final String PASSEPORT = "09Az54625";
    public static final String PAYS = "France";
    public static final String TEL = "+33650214256";
    public static final String SECRET = "hf56dq+6e98fdf65dqqe";
    public static final String ID = UUID.randomUUID().toString();
    @Autowired
    private CompteMapper subject;

    @Test
    @DisplayName("Devrait retourner l'obj en DTO")
    void toDto() {
        // GIVEN
        final Compte obj = new Compte()
                .setId(ID)
                .setIban(IBAN)
                .setNom(NOM)
                .setPrenom(PRENOM)
                .setNoPasseport(PASSEPORT)
                .setPays(PAYS)
                .setNumTel(TEL)
                .setSecret(SECRET);

        // WHEN
        final CompteDto actual = subject.toDto(obj);

        // THEN
        Assertions.assertEquals(IBAN,actual.getIban());
        Assertions.assertEquals(TEL,actual.getNumTel());
        Assertions.assertEquals(NOM,actual.getNom());
        Assertions.assertEquals(PRENOM, actual.getPrenom());
        Assertions.assertEquals(PASSEPORT, actual.getNoPasseport());
        Assertions.assertEquals(PAYS, actual.getPays());
        Assertions.assertEquals(SECRET, actual.getSecret());
        Assertions.assertEquals(ID,actual.getId());
    }

    @Test
    @DisplayName("Devrait retourner le DTO en obj")
    void toObj() {
        // GIVEN
        final NewCompte dto = new NewCompte()
                .setNom(NOM)
                .setPrenom(PRENOM)
                .setPays(PAYS)
                .setIban(IBAN)
                .setSecret(SECRET)
                .setNumTel(TEL)
                .setNoPasseport(PASSEPORT);

        // WHEN
        final Compte actual = subject.toObject(dto);

        // THEN
        Assertions.assertEquals(IBAN,actual.getIban());
        Assertions.assertEquals(PAYS, actual.getPays());
        Assertions.assertEquals(TEL, actual.getNumTel());
        Assertions.assertEquals(PASSEPORT, actual.getNoPasseport());
        Assertions.assertEquals(SECRET, actual.getSecret());
        Assertions.assertEquals(PRENOM, actual.getPrenom());
        Assertions.assertEquals(NOM, actual.getNom());
        Assertions.assertNull(actual.getId());
    }
}