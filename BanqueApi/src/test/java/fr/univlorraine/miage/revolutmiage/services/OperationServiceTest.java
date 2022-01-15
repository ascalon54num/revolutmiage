package fr.univlorraine.miage.revolutmiage.services;


import fr.univlorraine.miage.revolutmiage.entities.Compte;
import fr.univlorraine.miage.revolutmiage.entities.Operation;
import fr.univlorraine.miage.revolutmiage.entities.dtos.OperationDto;
import fr.univlorraine.miage.revolutmiage.repositories.CompteRepository;
import fr.univlorraine.miage.revolutmiage.repositories.OperationRepository;
import org.assertj.core.internal.Iterables;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.UUID;

@SpringBootTest
class OperationServiceTest {
    public static final String ID = UUID.randomUUID().toString();
    public static final String ID2 = UUID.randomUUID().toString();
    public static final String CATEGORIE = "Paiement";
    public static final String idCompte = UUID.randomUUID().toString();
    public static final String NOM = "Dupont";
    public static final String IBAN = "FR761034328909432347";
    public static final String PRENOM = "Jean";
    public static final String PASSEPORT = "09Az54625";
    public static final String PAYS = "France";
    public static final String TEL = "+33650214256";
    public static final String SECRET = "hf56dq+6e98fdf65dqqe";
    public static final Compte CREDITEUR = new Compte().setId(idCompte).setIban(IBAN)
            .setNom(NOM)
            .setPrenom(PRENOM)
            .setNoPasseport(PASSEPORT)
            .setPays(PAYS)
            .setNumTel(TEL)
            .setSecret(SECRET);
    public static final LocalDate DATE = LocalDate.parse("2021-02-12", DateTimeFormatter.ofPattern("yyyy-M-d", Locale.FRANCE));
    public static final String LIBELLE = "Achat pizza";
    public static final String LIBELLE2 = "Vente pizza";
    public static final String NOM_COMPTE_CREDITEUR = "Dupont";
    public static final double TAUX_APPLIQUE = 1.5;
    public static final double MONTANT = 18.00;
    @Autowired
    private OperationService subject;
    @Autowired
    private OperationRepository repo;
    @Autowired
    private CompteRepository repoCompte;

    @Test
    @DisplayName("Devrait retourner un tableau contenant les infos de 2 operations")
    void testFindAll() {
        repoCompte.save(CREDITEUR);
        final Operation obj = new Operation()
                .setId(ID)
                .setCategorie(CATEGORIE)
                .setCompteCrediteur(CREDITEUR)
                .setDate(DATE)
                .setLibelle(LIBELLE)
                .setNomCompteCrediteur(NOM_COMPTE_CREDITEUR)
                .setMontant(MONTANT)
                .setTauxApplique(TAUX_APPLIQUE)
                .setPays(PAYS);

        final Operation obj2 = new Operation()
                .setId(ID2)
                .setCategorie(CATEGORIE)
                .setCompteCrediteur(CREDITEUR)
                .setDate(DATE)
                .setLibelle(LIBELLE2)
                .setNomCompteCrediteur(NOM_COMPTE_CREDITEUR)
                .setMontant(MONTANT)
                .setTauxApplique(TAUX_APPLIQUE)
                .setPays(PAYS);
        repo.save(obj);
        repo.save(obj2);
        Iterable<OperationDto> cartes = subject.findAll();
        Iterables.instance().assertHasSize(null,cartes,2);
        Assertions.assertEquals(ID, org.mockito.internal.util.collections.Iterables.firstOf(cartes).getId());
        Assertions.assertEquals(LIBELLE, org.mockito.internal.util.collections.Iterables.firstOf(cartes).getLibelle());
        repo.delete(obj);
        repo.delete(obj2);
        repoCompte.delete(CREDITEUR);
    }
}
