package fr.univlorraine.miage.revolutmiage.mappers;

import fr.univlorraine.miage.revolutmiage.entities.Compte;
import fr.univlorraine.miage.revolutmiage.entities.Operation;
import fr.univlorraine.miage.revolutmiage.entities.dtos.NewOperation;
import fr.univlorraine.miage.revolutmiage.entities.dtos.OperationDto;
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
class OperationMapperTest {
    public static final UUID ID = UUID.randomUUID();
    public static final String CATEGORIE = "Paiement";
    public static final UUID idCompte = UUID.randomUUID();
    public static final Compte CREDITEUR = new Compte().setId(idCompte);
    public static final LocalDate DATE = LocalDate.parse("2021-02-12", DateTimeFormatter.ofPattern("yyyy-M-d", Locale.FRANCE));
    public static final String LIBELLE = "Achat pizza";
    public static final String NOM_COMPTE_CREDITEUR = "Dupont";
    public static final double TAUX_APPLIQUE = 1.5;
    public static final double MONTANT = 18.00;
    public static final String PAYS = "France";

    @Autowired
    private OperationMapper subject;

    @Test
    @DisplayName("Devrait retourner l'obj en DTO")
    void toDto() {
        // GIVEN
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


        // WHEN
        final OperationDto actual = subject.toDto(obj);

        // THEN
        Assertions.assertEquals(idCompte.toString(),actual.getCompteCrediteur());
        Assertions.assertEquals(CATEGORIE,actual.getCategorie());
        Assertions.assertEquals(DATE.toString(),actual.getDate());
        Assertions.assertEquals(PAYS, actual.getPays());
        Assertions.assertEquals(LIBELLE, actual.getLibelle());
        Assertions.assertEquals(NOM_COMPTE_CREDITEUR, actual.getNomCompteCrediteur());
        Assertions.assertEquals(MONTANT, actual.getMontant());
        Assertions.assertEquals(TAUX_APPLIQUE, actual.getTauxApplique());
        Assertions.assertEquals(ID.toString(),actual.getId());
    }

    @Test
    @DisplayName("Devrait retourner le DTO en obj")
    void toObj() {
        // GIVEN
        final NewOperation dto = new NewOperation()
                .setCategorie(CATEGORIE)
                .setDate(DATE.toString())
                .setLibelle(LIBELLE)
                .setNomCompteCrediteur(NOM_COMPTE_CREDITEUR)
                .setMontant(MONTANT)
                .setPays(PAYS)
                .setCompteCrediteurId(idCompte.toString())
                .setTauxApplique(TAUX_APPLIQUE);


        // WHEN
        final Operation actual = subject.toObject(dto);

        // THEN
        Assertions.assertNull(actual.getCompteCrediteur());
        Assertions.assertEquals(CATEGORIE,actual.getCategorie());
        Assertions.assertEquals(DATE,actual.getDate());
        Assertions.assertEquals(PAYS, actual.getPays());
        Assertions.assertEquals(LIBELLE, actual.getLibelle());
        Assertions.assertEquals(NOM_COMPTE_CREDITEUR, actual.getNomCompteCrediteur());
        Assertions.assertEquals(MONTANT, actual.getMontant());
        Assertions.assertEquals(TAUX_APPLIQUE, actual.getTauxApplique());
        Assertions.assertNull(actual.getId());
    }
}
