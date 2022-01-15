package fr.univlorraine.miage.revolutmiage.services;

import fr.univlorraine.miage.revolutmiage.entities.Carte;
import fr.univlorraine.miage.revolutmiage.entities.Compte;
import fr.univlorraine.miage.revolutmiage.entities.dtos.CarteDto;
import fr.univlorraine.miage.revolutmiage.repositories.CarteRepository;
import fr.univlorraine.miage.revolutmiage.repositories.CompteRepository;
import org.assertj.core.internal.Iterables;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class CarteServiceTest {
    public static final String CODE = "0000";
    public static final String CODE2 = "0405";
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
    public static final String ID = UUID.randomUUID().toString();
    public static final String ID2 = UUID.randomUUID().toString();
    @Autowired
    private CarteService subject;
    @Autowired
    private CarteRepository repo;
    @Autowired
    private CompteRepository repoCompte;

    @Test
    @DisplayName("Devrait retourner un tableau contenant les infos de 2 cartes")
    void testFindAll() {
        final Compte c = new Compte();
        UUID id = UUID.randomUUID();
        c.setId(id.toString()).setIban(IBAN)
                .setNom(NOM)
                .setPrenom(PRENOM)
                .setNoPasseport(PASSEPORT)
                .setPays(PAYS)
                .setNumTel(TEL)
                .setSecret(SECRET);
        repoCompte.save(c);
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
                 obj.setCompte(c);

        final Carte obj2 = new Carte()
                .setId(ID2)
                .setBloquee(true)
                .setLocalisation(true)
                .setPlafond(PLAFOND)
                .setSansContact(true)
                .setVirtuelle(true)
                .setCode(CODE2)
                .setCryptogramme(CRYPTOGRAMME)
                .setNumCarte(NUMERO_CARTE);
                obj2.setCompte(c);
        repo.save(obj);
        repo.save(obj2);
        Iterable<CarteDto> cartes = subject.findAll();
        Iterables.instance().assertHasSize(null,cartes,2);
        Assertions.assertEquals(ID, org.mockito.internal.util.collections.Iterables.firstOf(cartes).getId());
        Assertions.assertEquals(CODE, org.mockito.internal.util.collections.Iterables.firstOf(cartes).getCode());
        repo.delete(obj);
        repo.delete(obj2);
        repoCompte.delete(c);
    }
}
