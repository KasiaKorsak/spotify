package pl.kasia.tutorial.springspotify.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "roles")
@Getter
@Setter
@NoArgsConstructor
public class Role {

    @Id
    private String id;

    private ERole name;

}
