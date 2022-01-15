package fr.univlorraine.miage.revolutmiage.services;


import fr.univlorraine.miage.revolutmiage.entities.Operation;
import fr.univlorraine.miage.revolutmiage.entities.dtos.NewOperation;
import fr.univlorraine.miage.revolutmiage.entities.dtos.OperationDto;
import fr.univlorraine.miage.revolutmiage.exceptions.CompteNotFound;
import fr.univlorraine.miage.revolutmiage.mappers.OperationMapper;
import fr.univlorraine.miage.revolutmiage.repositories.CompteRepository;
import fr.univlorraine.miage.revolutmiage.repositories.OperationRepository;
import fr.univlorraine.miage.revolutmiage.services.validaters.OperationValidater;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OperationService {
    private final OperationRepository repo;
    private final CompteRepository compteRepo;
    private final OperationValidater validater;
    private final OperationMapper mapper;

    public Iterable<OperationDto> findAll() {
        return mapper.toDto(repo.findAll());
    }

    public OperationDto create(NewOperation newOp) {
        validater.validate(newOp);
        var no = mapper.toObject(newOp);
        var compte =
                compteRepo
                        .findById(newOp.getCompteCrediteurId())
                        .orElseThrow(() -> CompteNotFound.of(newOp));

        no.setCompteCrediteur(compte);
        no.setId(UUID.randomUUID().toString());
        no = repo.save(no);

        return mapper.toDto(no);
    }

    public Optional<Operation> findById(String id) {
        return repo.findById(id);
    }

    public void delete(Operation operation) {
        repo.delete(operation);
    }

    public void update(Operation op) {
        repo.save(op);
    }
}
