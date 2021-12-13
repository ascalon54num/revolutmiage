package fr.univlorraine.miage.revolutmiage.services;

import fr.univlorraine.miage.revolutmiage.entities.Compte;
import fr.univlorraine.miage.revolutmiage.entities.dtos.CompteDto;
import fr.univlorraine.miage.revolutmiage.repositories.CompteRepository;
import org.assertj.core.api.IterableSizeAssert;
import org.assertj.core.internal.Iterables;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;

@SpringBootTest
class CompteServiceTest {
    public static final String NOM = "Dupont";
    public static final String NOM2 = "Dujardin";
    public static final String IBAN = "FR761034328909432347";
    public static final String PRENOM = "Jean";
    public static final String PRENOM2 = "Anna";
    public static final String PASSEPORT = "09Az54625";
    public static final String PAYS = "France";
    public static final String TEL = "+33650214256";
    public static final String SECRET = "hf56dq+6e98fdf65dqqe";
    public static final UUID ID = UUID.randomUUID();
    public static final UUID ID2 = UUID.randomUUID();
    @Autowired
    private CompteService subject;
    @Autowired
    private CompteRepository repo;

    @Test
    @DisplayName("Devrait retourner un tableau contenant les infos de 2 comptes")
    void testFindAll() {
        final Compte obj = new Compte()
                .setId(ID)
                .setIban(IBAN)
                .setNom(NOM)
                .setPrenom(PRENOM)
                .setNoPasseport(PASSEPORT)
                .setPays(PAYS)
                .setNumTel(TEL)
                .setSecret(SECRET);
        final Compte obj2 = new Compte()
                .setId(ID2)
                .setIban(IBAN)
                .setNom(NOM2)
                .setPrenom(PRENOM2)
                .setNoPasseport(PASSEPORT)
                .setPays(PAYS)
                .setNumTel(TEL)
                .setSecret(SECRET);
        repo.save(obj);
        repo.save(obj2);
        Iterable<CompteDto> comptes = subject.findAll();
        Iterables.instance().assertHasSize(null,comptes,2);
        Assertions.assertEquals(ID.toString(), org.mockito.internal.util.collections.Iterables.firstOf(comptes).getId());
    }
}
