package ua.sulima.mangaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.sulima.mangaapp.domain.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {

}
