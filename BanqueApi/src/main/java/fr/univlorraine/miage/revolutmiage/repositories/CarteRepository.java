package fr.univlorraine.miage.revolutmiage.repositories;

import fr.univlorraine.miage.revolutmiage.entities.Carte;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarteRepository extends CrudRepository<Carte,String> {
    Optional<Carte> findByNumCarte(String numCarte);
}
