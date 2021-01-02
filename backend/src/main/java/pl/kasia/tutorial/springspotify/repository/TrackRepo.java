package pl.kasia.tutorial.springspotify.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.kasia.tutorial.springspotify.entity.Track;

@Repository
public interface TrackRepo extends MongoRepository<Track, String> {
}
