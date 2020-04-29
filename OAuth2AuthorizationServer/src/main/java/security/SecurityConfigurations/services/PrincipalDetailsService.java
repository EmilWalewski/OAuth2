package security.SecurityConfigurations.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import security.SecurityConfigurations.repositories.UserRepository;

@Service
public class PrincipalDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public PrincipalDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) {
        return new PrincipalDetails(userRepository.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException("User "+s+" could not be found")));
    }
}
