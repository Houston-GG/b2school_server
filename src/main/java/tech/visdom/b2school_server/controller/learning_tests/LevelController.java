package tech.visdom.b2school_server.controller.learning_tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.visdom.b2school_server.dto.learning_tests.UserTestResult;
import tech.visdom.b2school_server.service.learning_tests.UserLevelService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/level")
public class LevelController {

    private UserLevelService userLevelService;

    @Autowired
    public LevelController(UserLevelService userLevelService) {
        this.userLevelService = userLevelService;
    }

    @PostMapping("")
    public ResponseEntity sendResult(@RequestBody @Valid UserTestResult userTestResult) {
        userLevelService.saveResults(userTestResult);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
