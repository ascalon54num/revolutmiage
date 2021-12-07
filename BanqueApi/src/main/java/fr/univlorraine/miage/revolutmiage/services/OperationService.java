package fr.univlorraine.miage.revolutmiage.services;

import fr.univlorraine.miage.revolutmiage.mappers.OperationsMapper;
import fr.univlorraine.miage.revolutmiage.repositories.OperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OperationService {
    private final OperationRepository repo;
    private final OperationsMapper mapper;
}
