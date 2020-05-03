package tech.visdom.b2school_server.controller.learning_tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.visdom.b2school_server.exception.ExercisesNotFoundException;
import tech.visdom.b2school_server.service.learning_tests.ExerciseService;

@RestController
@RequestMapping(value = "/api/exercise")
public class ExerciseController {

    private ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/{exerciseId}")
    public ResponseEntity getExercises(@PathVariable(value = "exerciseId") Long exerciseId) {
        try {
            return new ResponseEntity<>(exerciseService.getExercisesByLevelId(exerciseId), HttpStatus.OK);
        } catch (ExercisesNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
