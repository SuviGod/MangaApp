package ua.sulima.mangaapp.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name = "GROUPS")
@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String groupName;

    private String link1;

    private String link2;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Creator creator = (Creator) o;
        return getId() != null && Objects.equals(getId(), creator.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
