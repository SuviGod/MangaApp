package ua.sulima.mangaapp.user;

import jakarta.persistence.*;
import lombok.*;
import ua.sulima.mangaapp.user.User;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ROLES")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String name;

    @ManyToMany
    private List<User> users;
}
