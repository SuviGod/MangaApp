package ua.sulima.mangaapp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Getter @Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Creator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String secondaryNames;

    private String description;

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
