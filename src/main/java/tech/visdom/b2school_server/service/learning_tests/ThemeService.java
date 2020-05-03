package tech.visdom.b2school_server.service.learning_tests;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.visdom.b2school_server.dao.learning_tests.ThemeDao;
import tech.visdom.b2school_server.dto.learning_tests.ThemeDto;
import tech.visdom.b2school_server.model.learning_tests.Theme;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ThemeService {

    private ThemeDao themeDao;

    @Autowired
    public ThemeService(ThemeDao themeDao) {
        this.themeDao = themeDao;
    }

    public List<ThemeDto> getAllThemes() {
        return IterableUtils.toList(themeDao.findAll()).stream().map(Theme::toDto).collect(Collectors.toList());
    }
}
