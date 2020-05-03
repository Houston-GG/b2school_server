package tech.visdom.b2school_server.dao;

import org.springframework.data.repository.CrudRepository;
import tech.visdom.b2school_server.model.Role;

import java.util.Optional;

public interface RoleDao extends CrudRepository<Role, Long> {

    Optional<Role> findByName(String roleName);
}
