package tech.visdom.b2school_server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.visdom.b2school_server.dao.GroupDao;
import tech.visdom.b2school_server.dto.group.ClassGroupDto;
import tech.visdom.b2school_server.dto.group.SampleClassGroupDto;
import tech.visdom.b2school_server.exception.GroupNotFoundException;
import tech.visdom.b2school_server.model.ClassGroup;
import tech.visdom.b2school_server.model.User;
import java.util.Collections;

@Service
public class GroupService {

    private GroupDao groupDao;
    private UserService userService;

    @Autowired
    public GroupService(GroupDao groupDao, UserService userService) {
        this.groupDao = groupDao;
        this.userService = userService;
    }

    public ClassGroup getClassGroupById(Long id) {
        return groupDao.findById(id).orElseThrow(() -> new GroupNotFoundException("Group with ID " + id + " not found."));
    }

    public ClassGroupDto getClassGroupDtoById(Long id) {
        return getClassGroupById(id).toDto();
    }

    public ClassGroupDto createClassGroup(SampleClassGroupDto sampleClassGroupDto) {
        ClassGroup classGroup = sampleClassGroupDto.toClassGroupModel();
        User user = userService.getAuthUserCredentials();
        classGroup.setCreator(user.getId());
        classGroup.setUsers(Collections.singletonList(user));
        return groupDao.save(classGroup).toDto();
    }
}
