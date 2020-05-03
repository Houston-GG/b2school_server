package tech.visdom.b2school_server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import tech.visdom.b2school_server.dto.UserDto;
import tech.visdom.b2school_server.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public ResponseEntity loginUser() {
        try {
            return new ResponseEntity<>(userService.getLoginInfo(), HttpStatus.OK);
        } catch (UsernameNotFoundException e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/registration/teacher")
    public ResponseEntity registerUserTeacher(@RequestBody @Valid UserDto userDto) {
        try {
            return new ResponseEntity<>(userService.registerUser(userDto, "ROLE_TEACHER"), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/registration/student")
    public ResponseEntity registerUserStudent(@RequestBody @Valid UserDto userDto) {
        try {
            return new ResponseEntity<>(userService.registerUser(userDto, "ROLE_STUDENT"), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/registration/parent")
    public ResponseEntity registerUserParent(@RequestBody @Valid UserDto userDto) {
        try {
            return new ResponseEntity<>(userService.registerUser(userDto, "ROLE_PARENT"), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
