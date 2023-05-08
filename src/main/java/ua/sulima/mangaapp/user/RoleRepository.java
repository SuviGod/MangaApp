package ua.sulima.mangaapp.user;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.sulima.mangaapp.user.Role;

public interface RoleRepository extends JpaRepository<Role, Short> {
    Role getByName(String name);
}
