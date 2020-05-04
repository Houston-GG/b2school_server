package tech.visdom.b2school_server.dao;

import org.springframework.data.repository.CrudRepository;
import tech.visdom.b2school_server.model.ClassGroup;

import java.util.List;
import java.util.Optional;

public interface GroupDao extends CrudRepository<ClassGroup, Long> {

    Optional<List<ClassGroup>> findByCity(String name);
}
