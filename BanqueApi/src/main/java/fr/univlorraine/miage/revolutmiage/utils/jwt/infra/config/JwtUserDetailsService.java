package fr.univlorraine.miage.revolutmiage.utils.jwt.infra.config;

import fr.univlorraine.miage.revolutmiage.entities.Compte;
import fr.univlorraine.miage.revolutmiage.repositories.CompteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final CompteRepository compteRepo;

    @Override
    public UserDetails loadUserByUsername(final String passport) throws UsernameNotFoundException {
        final Optional<Compte> optionalCompte = compteRepo.findByNoPasseport(passport);

        if (optionalCompte.isPresent()) {
            final Compte currentUtilisateur = optionalCompte.get();
            return new User(passport, currentUtilisateur.getSecret(),
                    new ArrayList<>() {{
                        add(new SimpleGrantedAuthority("ROLE_USER"));
                    }});
        } else {
            throw new UsernameNotFoundException("Utilisateur non trouvé avec le passeport : " + passport);
        }
    }
}
