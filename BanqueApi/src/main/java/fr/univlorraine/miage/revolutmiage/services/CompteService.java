package fr.univlorraine.miage.revolutmiage.services;

import fr.univlorraine.miage.revolutmiage.mappers.CompteMapper;
import fr.univlorraine.miage.revolutmiage.repositories.CompteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompteService {
    private final CompteRepository repo;
    private final CompteMapper mapper;
}
