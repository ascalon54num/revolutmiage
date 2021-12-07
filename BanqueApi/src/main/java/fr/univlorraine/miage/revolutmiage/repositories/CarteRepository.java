package fr.univlorraine.miage.revolutmiage.repositories;

import fr.univlorraine.miage.revolutmiage.entities.Carte;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteRepository extends CrudRepository<Carte,String> {
}
