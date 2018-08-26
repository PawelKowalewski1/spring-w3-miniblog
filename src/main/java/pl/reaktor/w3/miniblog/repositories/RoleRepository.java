package pl.reaktor.w3.miniblog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.reaktor.w3.miniblog.entities.Role;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository <Role, Long>{


    Optional <Role> findOneByRoleName(String roleName);
}

