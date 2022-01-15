package fr.univlorraine.miage.revolutmiage.services;

import fr.univlorraine.miage.revolutmiage.entities.Carte;
import fr.univlorraine.miage.revolutmiage.entities.dtos.CarteDto;
import fr.univlorraine.miage.revolutmiage.entities.dtos.NewCarte;
import fr.univlorraine.miage.revolutmiage.exceptions.CompteNotFound;
import fr.univlorraine.miage.revolutmiage.mappers.CarteMapper;

import fr.univlorraine.miage.revolutmiage.repositories.CarteRepository;
import fr.univlorraine.miage.revolutmiage.repositories.CompteRepository;
import fr.univlorraine.miage.revolutmiage.services.validaters.CarteValidater;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CarteService {
    private final CarteRepository repo;
    private final CarteValidater validater;
    private final CompteRepository compteRepo;
    private final CarteMapper mapper;

    public Iterable<CarteDto> findAll() {
        return mapper.toDto(repo.findAll());
    }

    public CarteDto create(NewCarte newCarte) {
        validater.validate(newCarte);
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

    public Optional<Carte> findById(String id) {
        return repo.findById(id);
    }

    public void delete(Carte carte) {
        repo.delete(carte);
    }

    public void update(Carte carte) {
        repo.save(carte);
    }

    public Optional<Carte> findByNumCarte(String numCarte) {
        return repo.findByNumCarte(numCarte);
    }
}
