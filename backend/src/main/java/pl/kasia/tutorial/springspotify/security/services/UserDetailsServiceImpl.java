package pl.kasia.tutorial.springspotify.security.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.kasia.tutorial.springspotify.entity.User;
import pl.kasia.tutorial.springspotify.repository.UserRepository;

import javax.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user with given username not found: " + username));
        return UserDetailsImpl.build(user);
    }
}
