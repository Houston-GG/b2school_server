package tech.visdom.b2school_server.service.learning_tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.visdom.b2school_server.dao.learning_tests.AnswerDao;
import tech.visdom.b2school_server.dao.learning_tests.UserLevelDao;
import tech.visdom.b2school_server.dto.learning_tests.UserTestResult;
import tech.visdom.b2school_server.exception.AnswerNotFoundException;
import tech.visdom.b2school_server.model.learning_tests.Answer;
import tech.visdom.b2school_server.model.learning_tests.UserLevel;
import tech.visdom.b2school_server.service.UserService;

import java.util.concurrent.atomic.AtomicReference;

@Service
public class UserLevelService {

    private UserLevelDao userLevelDao;
    private UserService userService;
    private ExerciseService exerciseService;
    private AnswerDao answerDao;

    @Autowired
    public UserLevelService(UserLevelDao userLevelDao, UserService userService, ExerciseService exerciseService, AnswerDao answerDao) {
        this.userLevelDao = userLevelDao;
        this.userService = userService;
        this.exerciseService = exerciseService;
        this.answerDao = answerDao;
    }

    public void saveResults(UserTestResult userTestResult) {
        UserLevel userLevel = new UserLevel();
        userLevel.setUserId(userService.getAuthUserCredentials().getId());
        userLevel.setLevelId(userTestResult.getLevelId());
        userLevel.setDateStart(userTestResult.getDateStart());
        userLevel.setDateFinish(userTestResult.getDateFinish());
        userLevel.setExerciseCount(userTestResult.getAnswers().size());

        AtomicReference<Integer> successExerciseCount = new AtomicReference<>(0);
        userTestResult.getAnswers().forEach((a) -> {
            Answer answer = answerDao.findById(a).orElseThrow(() -> new AnswerNotFoundException("Answer with ID " + a + " not found."));
            if (!answer.getExercise().getLevelId().equals(userTestResult.getLevelId()))
                throw new AnswerNotFoundException("Answer with ID " + a + " not found in level with ID " + userTestResult.getLevelId() + ".");
            if (answer.getIsCorrect()) {
                successExerciseCount.updateAndGet(v -> v + 1);
            }
        });
        userLevel.setSuccessExerciseCount(successExerciseCount.get());
        userLevel.setSuccessfullyPassed(userTestResult.getAnswers().size() == successExerciseCount.get());

        userLevelDao.save(userLevel);
    }
}
