package pl.kasia.tutorial.springspotify.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import pl.kasia.tutorial.springspotify.entity.Track;
import pl.kasia.tutorial.springspotify.model.SpotifyAlbum;
import pl.kasia.tutorial.springspotify.model.dto.SpotifyAlbumDto;
import pl.kasia.tutorial.springspotify.repository.TrackRepo;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SpotifyAlbumClient {

    private TrackRepo trackRepo;

    public SpotifyAlbumClient(TrackRepo trackRepo) {
        this.trackRepo = trackRepo;
    }

    @GetMapping("/album/{authorName}")
    public List<SpotifyAlbumDto> getAlbumsByAuthor(OAuth2Authentication details, @PathVariable String authorName) {

        String token = ((OAuth2AuthenticationDetails) details.getDetails()).getTokenValue();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + token);
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        ResponseEntity<SpotifyAlbum> exchange = restTemplate.exchange("https://api.spotify.com/v1/search?q=" + authorName + "&type=track&market=US&limit=10&offset=5",
                HttpMethod.GET,
                httpEntity,
                SpotifyAlbum.class);

        List<SpotifyAlbumDto> spotifyAlbumDtos = exchange.getBody().getTracks().getItems()
                .stream()
                .map(item -> new SpotifyAlbumDto(item.getName(), item.getAlbum().getImages().get(0).getUrl()))
                .collect(Collectors.toList());

        return spotifyAlbumDtos;
    }

    @PostMapping("/add")
    public void addTrack(@RequestBody Track track) {
        trackRepo.save(track);
    }

    @DeleteMapping("/delete")
    public void deleteTrack(Track track) {
        trackRepo.delete(track);
    }

}
