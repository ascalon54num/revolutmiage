package fr.univlorraine.miage.revolutmiage.services;

import fr.univlorraine.miage.revolutmiage.entities.dtos.CompteDto;
import fr.univlorraine.miage.revolutmiage.entities.dtos.NewCompte;
import fr.univlorraine.miage.revolutmiage.mappers.CompteMapper;
import fr.univlorraine.miage.revolutmiage.repositories.CompteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompteService {
    private final CompteRepository repo;
    private final CompteMapper mapper;

    public Iterable<CompteDto> findAll() {
        return mapper.toDto(repo.findAll());
    }

    public CompteDto create(NewCompte newCompte) {
        var nc = mapper.toObject(newCompte);
        nc.setId(UUID.randomUUID().toString());
        nc = repo.save(nc);

        return mapper.toDto(nc);
    }

    public Optional<CompteDto> findById(String id) {
        return Optional.of(mapper.toDto(repo.findById(id).get()));
    }
}
