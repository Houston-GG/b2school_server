package tech.visdom.b2school_server.service;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.visdom.b2school_server.dao.GroupDao;
import tech.visdom.b2school_server.dto.UserStatistic;
import tech.visdom.b2school_server.dto.group.ClassGroupDto;
import tech.visdom.b2school_server.dto.group.SampleClassGroupDto;
import tech.visdom.b2school_server.exception.GroupNotFoundException;
import tech.visdom.b2school_server.model.ClassGroup;
import tech.visdom.b2school_server.model.User;
import tech.visdom.b2school_server.service.learning_tests.ThemeService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GroupService {

    private GroupDao groupDao;
    private UserService userService;
    private ThemeService themeService;

    @Autowired
    public GroupService(GroupDao groupDao, UserService userService, ThemeService themeService) {
        this.groupDao = groupDao;
        this.userService = userService;
        this.themeService = themeService;
    }

    public ClassGroup getClassGroupById(Long id) {
        return groupDao.findById(id).orElseThrow(() -> new GroupNotFoundException("Group with ID " + id + " not found."));
    }

    public ClassGroupDto getClassGroupDtoById(Long id) {
        return getClassGroupById(id).toDto();
    }

    public List<ClassGroupDto> getAllClassGroupsDto() {
        return IterableUtils.toList(groupDao.findAll()).stream().map(ClassGroup::toDto).collect(Collectors.toList());
    }

    public List<ClassGroupDto> getClassGroupsByCity(String name) {
        return groupDao.findByCity(name).orElseThrow(() -> new GroupNotFoundException("Group with city name " + name + " not found.")).stream().map(ClassGroup::toDto).collect(Collectors.toList());
    }

    public ClassGroupDto createClassGroup(SampleClassGroupDto sampleClassGroupDto) {
        ClassGroup classGroup = sampleClassGroupDto.toClassGroupModel();
        User user = userService.getAuthUserCredentials();
        classGroup.setCreator(user.getId());
        classGroup.setUsers(Collections.singletonList(user));
        return groupDao.save(classGroup).toDto();
    }

    public ClassGroupDto joinClassGroup(Long classGroupId) {
        ClassGroup classGroup = getClassGroupById(classGroupId);
        User user = userService.getAuthUserCredentials();
        classGroup.getUsers().add(user);
        return groupDao.save(classGroup).toDto();
    }

    public List<UserStatistic> getGroupStatistic(Long groupId) {
        ClassGroup classGroup = getClassGroupById(groupId);
        List<UserStatistic> userStatistics = new ArrayList<>();

        for (User user:classGroup.getUsers()) {
            UserStatistic userStatistic = new UserStatistic(user.getId(), user.getUserName(), user.getFirstName(), user.getLastName(), user.getPoints());
            userStatistic.setThemesDto(themeService.getAllThemes(user.getId()));
            userStatistics.add(userStatistic);
        }

        return userStatistics;
    }

    public Set<String> getCities() {
       return IterableUtils.toList(groupDao.findAll()).stream().map(ClassGroup::getCity).collect(Collectors.toSet());
    }
}
