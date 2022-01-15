package fr.univlorraine.miage.revolutmiage.services;

import fr.univlorraine.miage.revolutmiage.entities.Compte;
import fr.univlorraine.miage.revolutmiage.entities.dtos.CompteDto;
import fr.univlorraine.miage.revolutmiage.entities.dtos.NewCompte;
import fr.univlorraine.miage.revolutmiage.mappers.CompteMapper;
import fr.univlorraine.miage.revolutmiage.repositories.CompteRepository;
import fr.univlorraine.miage.revolutmiage.services.validaters.CompteValidater;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompteService {
    private final CompteRepository repo;
    private final CompteValidater validater;
    private final CompteMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public Iterable<CompteDto> findAll() {
        return mapper.toDto(repo.findAll());
    }

    public CompteDto create(NewCompte newCompte) {
        validater.validate(newCompte);
        var nc = mapper.toObject(newCompte);
        nc.setId(UUID.randomUUID().toString());
        nc.setSecret(passwordEncoder.encode(newCompte.getSecret()));
        nc = repo.save(nc);

        return mapper.toDto(nc);
    }

    public Optional<Compte> findById(String id) {
        return repo.findById(id);
    }

    public void delete(Compte compte) {
        repo.delete(compte);
    }

    public void update(Compte compte) {
        repo.save(compte);
    }
}
