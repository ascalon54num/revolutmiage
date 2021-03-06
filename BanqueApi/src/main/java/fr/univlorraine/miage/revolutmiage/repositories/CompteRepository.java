package fr.univlorraine.miage.revolutmiage.repositories;

import fr.univlorraine.miage.revolutmiage.entities.Compte;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompteRepository extends CrudRepository<Compte, String> {
    Optional<Compte> findByNoPasseport(String passport);
}
