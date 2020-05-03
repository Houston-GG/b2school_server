package tech.visdom.b2school_server.service.learning_tests;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.visdom.b2school_server.dao.learning_tests.ThemeDao;
import tech.visdom.b2school_server.dto.learning_tests.ThemeDto;
import tech.visdom.b2school_server.model.learning_tests.Theme;
import tech.visdom.b2school_server.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ThemeService {

    private ThemeDao themeDao;
    private UserLevelService userLevelService;
    private UserService userService;

    @Autowired
    public ThemeService(ThemeDao themeDao, UserLevelService userLevelService, UserService userService) {
        this.themeDao = themeDao;
        this.userLevelService = userLevelService;
        this.userService = userService;
    }

    public List<ThemeDto> getAllThemes() {
        List<ThemeDto> themeDtoList = IterableUtils.toList(themeDao.findAll()).stream().map(Theme::toDto).collect(Collectors.toList());
        themeDtoList.forEach(t -> t.getLevels()
                .forEach(l -> l.setUserLevelDto(userLevelService.getLastUserLevel(userService.getAuthUserCredentials().getId(), l.getId()))));
        return themeDtoList;
    }
}
