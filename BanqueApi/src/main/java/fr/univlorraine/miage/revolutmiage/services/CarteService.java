package fr.univlorraine.miage.revolutmiage.services;

import fr.univlorraine.miage.revolutmiage.mappers.CarteMapper;

import fr.univlorraine.miage.revolutmiage.repositories.CarteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarteService {
    private final CarteRepository repo;
    private final CarteMapper mapper;
}
