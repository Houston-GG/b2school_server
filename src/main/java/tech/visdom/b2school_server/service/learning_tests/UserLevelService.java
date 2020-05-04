package tech.visdom.b2school_server.service.learning_tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.visdom.b2school_server.dao.learning_tests.AnswerDao;
import tech.visdom.b2school_server.dao.learning_tests.UserLevelDao;
import tech.visdom.b2school_server.dto.learning_tests.UserLevelDto;
import tech.visdom.b2school_server.dto.learning_tests.UserTestResult;
import tech.visdom.b2school_server.exception.AnswerNotFoundException;
import tech.visdom.b2school_server.model.learning_tests.Answer;
import tech.visdom.b2school_server.model.learning_tests.Result;
import tech.visdom.b2school_server.model.learning_tests.UserLevel;
import tech.visdom.b2school_server.service.UserService;

import java.util.ArrayList;
import java.util.List;

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

    public UserLevelDto saveResults(UserTestResult userTestResult) {
        UserLevel userLevel = new UserLevel();
        userLevel.setUserId(userService.getAuthUserCredentials().getId());
        userLevel.setLevelId(userTestResult.getLevelId());
        userLevel.setDateStart(userTestResult.getDateStart());
        userLevel.setDateFinish(userTestResult.getDateFinish());
        userLevel.setExerciseCount(userTestResult.getAnswers().size());
        List<Result> results = new ArrayList<>();

        Integer successExerciseCount = 0;
        Integer points = 0;

        for (Long answer: userTestResult.getAnswers()) {
            Answer answerTmp = answerDao.findById(answer).orElseThrow(() -> new AnswerNotFoundException("Answer with ID " + answer + " not found."));
            if (!answerTmp.getExercise().getLevelId().equals(userTestResult.getLevelId()))
                throw new AnswerNotFoundException("Answer with ID " + answer + " not found in level with ID " + userTestResult.getLevelId() + ".");
            if (answerTmp.getIsCorrect()) {
                successExerciseCount ++;
                points += answerTmp.getExercise().getComplexity();
            }
            results.add(new Result(answerTmp.getExercise().getText(), answerTmp.getText(), answerTmp.getIsCorrect(), userLevel));
        }

        userLevel.setSuccessExerciseCount(successExerciseCount);
        userLevel.setSuccessfullyPassed(userTestResult.getAnswers().size() == successExerciseCount);
        userLevel.setResults(results);

        if(userTestResult.getAnswers().size() == successExerciseCount)
        userService.addPoints(points);

        return userLevelDao.save(userLevel).toDto();
    }

    public UserLevelDto getLastUserLevel(Long userId, Long levelId) {
        UserLevel userLevel = userLevelDao.findTopByUserIdAndLevelIdOrderByDateStartDesc(userId, levelId);
        if (userLevel != null)
            return userLevel.toDto();
        else
            return null;
    }
}
