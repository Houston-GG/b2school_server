package tech.visdom.b2school_server.dao.learning_tests;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import tech.visdom.b2school_server.model.learning_tests.Exercise;

import java.util.List;
import java.util.Optional;

public interface ExerciseDao extends CrudRepository<Exercise, Long> {

    @Query(value="SELECT * FROM exercise WHERE LEVEL_ID = ?1 ORDER BY RAND() LIMIT ?2", nativeQuery = true)
    Optional<List<Exercise>> findByLevelId(Long levelId, Integer limit);
}
