package ua.sulima.mangaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.sulima.mangaapp.domain.Creator;

public interface CreatorRepository
        extends JpaRepository<Creator, Integer> {
}
