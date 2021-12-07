package fr.univlorraine.miage.revolutmiage.repositories;

import fr.univlorraine.miage.revolutmiage.entities.Operation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends CrudRepository<Operation, String> {
}
