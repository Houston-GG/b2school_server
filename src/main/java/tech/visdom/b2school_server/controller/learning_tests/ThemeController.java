package tech.visdom.b2school_server.controller.learning_tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.visdom.b2school_server.service.learning_tests.ThemeService;

@RestController
@RequestMapping(value = "/api/theme")
public class ThemeController {

    private ThemeService themeService;

    @Autowired
    public ThemeController(ThemeService themeService) {
        this.themeService = themeService;
    }

    @GetMapping("/all")
    public ResponseEntity getAllThemes() {
            return new ResponseEntity<>(themeService.getAllThemes(), HttpStatus.OK);
    }
}
