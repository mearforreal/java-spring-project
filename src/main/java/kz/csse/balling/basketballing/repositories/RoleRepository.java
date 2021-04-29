package kz.csse.balling.basketballing.repositories;


import kz.csse.balling.basketballing.entites.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Roles,Long> {

    Roles findByRole(String role);

}
