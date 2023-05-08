package ua.sulima.mangaapp.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder  @AllArgsConstructor
public class Manga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String alternativeNames;

//    private Integer authorId;

//    private Integer artistId;

    private Short releaseYear;

    private Long translatorId;

    private LocalDateTime addDatetime;

    private LocalDateTime updateDatetime;

    private LocalDateTime approvalDatetime;

    private String previewImagePath;

    private Boolean isApproved;

    @ManyToOne
    @JoinColumn(name="artist_id", nullable=false)
    private Creator artist;

    @ManyToOne
    @JoinColumn(name="author_id", nullable=false)
    private Creator author;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Manga manga = (Manga) o;
        return getId() != null && Objects.equals(getId(), manga.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
