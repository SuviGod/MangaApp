package ua.sulima.mangaapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.sulima.mangaapp.domain.Creator;
import ua.sulima.mangaapp.domain.Manga;

public interface MangaRepository extends JpaRepository<Manga, Integer> {

    @Query("select m from Manga m where m.author = ?1 or m.artist = ?1")
    Page<Manga> findAllByAuthorOrArtist(Creator creator, Pageable pageable);

}
