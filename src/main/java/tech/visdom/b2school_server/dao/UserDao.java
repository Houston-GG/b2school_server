package tech.visdom.b2school_server.dao;

import org.springframework.data.repository.CrudRepository;
import tech.visdom.b2school_server.model.User;

import java.util.Optional;

public interface UserDao extends CrudRepository<User, Long> {

    Optional<User> findByUserName(String userName);
}
