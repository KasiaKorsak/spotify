package pl.kasia.tutorial.springspotify.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.kasia.tutorial.springspotify.entity.ERole;
import pl.kasia.tutorial.springspotify.entity.Role;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role,String> {

    Optional<Role> findByName(ERole name);

}
