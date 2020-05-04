package tech.visdom.b2school_server.dao;

import org.springframework.data.repository.CrudRepository;
import tech.visdom.b2school_server.model.ClassGroup;

public interface GroupDao extends CrudRepository<ClassGroup, Long> {
}
