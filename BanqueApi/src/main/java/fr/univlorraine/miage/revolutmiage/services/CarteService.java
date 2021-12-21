package fr.univlorraine.miage.revolutmiage.services;

import fr.univlorraine.miage.revolutmiage.entities.dtos.CarteDto;
import fr.univlorraine.miage.revolutmiage.entities.dtos.NewCarte;
import fr.univlorraine.miage.revolutmiage.exceptions.CompteNotFound;
import fr.univlorraine.miage.revolutmiage.mappers.CarteMapper;

import fr.univlorraine.miage.revolutmiage.repositories.CarteRepository;
import fr.univlorraine.miage.revolutmiage.repositories.CompteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CarteService {
    private final CarteRepository repo;
    private final CompteRepository compteRepo;
    private final CarteMapper mapper;

    public Iterable<CarteDto> findAll() {
        return mapper.toDto(repo.findAll());
    }

    public CarteDto create(NewCarte newCarte) {
        var nc = mapper.toObject(newCarte);
        var compte =
                compteRepo
                        .findById(newCarte.getCompteId())
                        .orElseThrow(() -> CompteNotFound.of(newCarte));

        nc.setCompte(compte);
        nc.setId(UUID.randomUUID().toString());
        nc = repo.save(nc);

        return mapper.toDto(nc);
    }

    public Optional<CarteDto> findById(String id) {
        return Optional.of(mapper.toDto(repo.findById(id).get()));
    }
}
