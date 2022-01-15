package fr.univlorraine.miage.revolutmiage.mappers;

import fr.univlorraine.miage.revolutmiage.entities.Carte;
import fr.univlorraine.miage.revolutmiage.entities.Compte;
import fr.univlorraine.miage.revolutmiage.entities.dtos.CarteDto;
import fr.univlorraine.miage.revolutmiage.entities.dtos.NewCarte;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class CarteMapperTest {
    public  static final String ID = UUID.randomUUID().toString();
    public static final String CODE = "0000";
    public static final String CRYPTOGRAMME = "666";
    public static final String NUMERO_CARTE = "5000657823458954";
    public static final double PLAFOND = 1000.00;
    public static final String NOM = "Dupont";
    public static final String IBAN = "FR761034328909432347";
    public static final String PRENOM = "Jean";
    public static final String PASSEPORT = "09Az54625";
    public static final String PAYS = "France";
    public static final String TEL = "+33650214256";
    public static final String SECRET = "hf56dq+6e98fdf65dqqe";

    @Autowired
    private CarteMapper subject;

    @Test
    @DisplayName("Devrait retourner l'obj en DTO")
    void toDto() {
        // GIVEN
        final Carte obj = new Carte()
                .setId(ID)
                .setBloquee(true)
                .setLocalisation(true)
                .setPlafond(PLAFOND)
                .setSansContact(true)
                .setVirtuelle(true)
                .setCode(CODE)
                .setCryptogramme(CRYPTOGRAMME)
                .setNumCarte(NUMERO_CARTE);
                final Compte c = new Compte();
                UUID id = UUID.randomUUID();
                c.setId(id.toString()).setIban(IBAN)
                        .setNom(NOM)
                        .setPrenom(PRENOM)
                        .setNoPasseport(PASSEPORT)
                        .setPays(PAYS)
                        .setNumTel(TEL)
                        .setSecret(SECRET);
                obj.setCompte(c);

        // WHEN
        final CarteDto actual = subject.toDto(obj);

        // THEN
        Assertions.assertTrue(actual.isBloquee());
        Assertions.assertTrue(actual.isLocalisation());
        Assertions.assertEquals(PLAFOND,actual.getPlafond());
        Assertions.assertTrue(actual.isSansContact());
        Assertions.assertTrue(actual.isVirtuelle());
        Assertions.assertEquals(CODE, actual.getCode());
        Assertions.assertEquals(CRYPTOGRAMME, actual.getCryptogramme());
        Assertions.assertEquals(NUMERO_CARTE, actual.getNumCarte());
        Assertions.assertEquals(id.toString(),actual.getCompte());
    }

    @Test
    @DisplayName("Devrait retourner le DTO en obj")
    void toObj() {
        // GIVEN
        final NewCarte dto = new NewCarte()
                .setBloquee(true)
                .setLocalisation(true)
                .setPlafond(PLAFOND)
                .setSansContact(true)
                .setVirtuelle(true)
                .setCode(CODE)
                .setCryptogramme(CRYPTOGRAMME)
                .setNumCarte(NUMERO_CARTE);
                String uuid = UUID.randomUUID().toString();
                dto.setCompteId(uuid);

        // WHEN
        final Carte actual = subject.toObject(dto);

        // THEN
        Assertions.assertTrue(actual.isBloquee());
        Assertions.assertTrue(actual.isLocalisation());
        Assertions.assertEquals(PLAFOND,actual.getPlafond());
        Assertions.assertTrue(actual.isSansContact());
        Assertions.assertTrue(actual.isVirtuelle());
        Assertions.assertEquals(CODE, actual.getCode());
        Assertions.assertEquals(CRYPTOGRAMME, actual.getCryptogramme());
        Assertions.assertEquals(NUMERO_CARTE, actual.getNumCarte());
        Assertions.assertNull(actual.getCompte());
    }
}