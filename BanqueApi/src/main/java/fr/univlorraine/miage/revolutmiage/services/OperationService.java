package fr.univlorraine.miage.revolutmiage.services;


import fr.univlorraine.miage.revolutmiage.entities.dtos.NewOperation;
import fr.univlorraine.miage.revolutmiage.entities.dtos.OperationDto;
import fr.univlorraine.miage.revolutmiage.exceptions.CompteNotFound;
import fr.univlorraine.miage.revolutmiage.mappers.OperationMapper;
import fr.univlorraine.miage.revolutmiage.repositories.CompteRepository;
import fr.univlorraine.miage.revolutmiage.repositories.OperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OperationService {
    private final OperationRepository repo;
    private final CompteRepository compteRepo;
    private final OperationMapper mapper;

    public Iterable<OperationDto> findAll() {
        return mapper.toDto(repo.findAll());
    }

    public OperationDto create(NewOperation newOp) {
        var no = mapper.toObject(newOp);
        var compte =
                compteRepo
                        .findById(newOp.getCompteCrediteurId())
                        .orElseThrow(() -> CompteNotFound.of(newOp));

        no.setCompteCrediteur(compte);
        no.setId(UUID.randomUUID());
        no = repo.save(no);

        return mapper.toDto(no);
    }
}
