package api.v1.authentication.persistency;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    String password;
    String email;

    @Enumerated(value = EnumType.STRING)
    Role role;

    @Enumerated(value = EnumType.STRING)
    StatusUser statusUser;
}
