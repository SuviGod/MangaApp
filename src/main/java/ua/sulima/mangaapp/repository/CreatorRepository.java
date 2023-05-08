package ua.sulima.mangaapp.repository;

import org.springframework.data.repository.CrudRepository;
import ua.sulima.mangaapp.domain.Creator;

public interface CreatorRepository
        extends CrudRepository<Creator, Integer> {
}
