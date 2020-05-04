package tech.visdom.b2school_server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.visdom.b2school_server.dao.RoleDao;
import tech.visdom.b2school_server.dao.UserDao;
import tech.visdom.b2school_server.dto.ExtendedUserDto;
import tech.visdom.b2school_server.dto.UserDto;
import tech.visdom.b2school_server.model.User;

import java.util.stream.Collectors;

@Service
public class UserService {

    private UserDao userDao;
    private RoleDao roleDao;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDao userDao, RoleDao roleDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    private User getUserByUserName(String userName) {
        return userDao.findByUserName(userName).orElseThrow(() -> new UsernameNotFoundException("Unknown username"));
    }

    public User getAuthUserCredentials() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return getUserByUserName(userDetails.getUsername());
    }

    public ExtendedUserDto getLoginInfo() {
        return getAuthUserCredentials().toExtendedUserDto();
    }

    public UserDto registerUser(UserDto userDto, String roleName) {
        User user = userDto.toModel();
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(roleDao.findByName(roleName).stream().collect(Collectors.toSet()));
        return userDao.save(user).toDto();
    }

    public void addPoints(Integer points) {
        User user = getAuthUserCredentials();
        user.setPoints(user.getPoints() + points);
    }

}
