package tech.visdom.b2school_server.service.learning_tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.visdom.b2school_server.dao.learning_tests.ExerciseDao;
import tech.visdom.b2school_server.dto.learning_tests.ExerciseDto;
import tech.visdom.b2school_server.exception.ExercisesNotFoundException;
import tech.visdom.b2school_server.model.learning_tests.Exercise;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciseService {

    private ExerciseDao exerciseDao;

    @Autowired
    public ExerciseService(ExerciseDao exerciseDao) {
        this.exerciseDao = exerciseDao;
    }

    public List<ExerciseDto> getExercisesByLevelId(Long levelId) {
        return exerciseDao.findByLevelId(levelId, 6).orElseThrow(() -> new ExercisesNotFoundException("No exercises found by level ID " + levelId + "."))
                .stream().map(Exercise::toDto).collect(Collectors.toList());
    }
}
