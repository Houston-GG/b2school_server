package tech.visdom.b2school_server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.visdom.b2school_server.dto.group.SampleClassGroupDto;
import tech.visdom.b2school_server.exception.GroupNotFoundException;
import tech.visdom.b2school_server.service.GroupService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/group")
public class GroupController {

    private GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }


    @GetMapping("/all")
    public ResponseEntity getAllClassGroupsDto() {
        try {
            return new ResponseEntity<>(groupService.getAllClassGroupsDto(), HttpStatus.OK);
        } catch (GroupNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getGroupById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(groupService.getClassGroupDtoById(id), HttpStatus.OK);
        } catch (GroupNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/city/{city}")
    public ResponseEntity getClassGroupsByCity(@PathVariable(value = "city") String city) {
        try {
            return new ResponseEntity<>(groupService.getClassGroupsByCity(city), HttpStatus.OK);
        } catch (GroupNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/new")
    public ResponseEntity createClassGroup(@RequestBody @Valid SampleClassGroupDto sampleClassGroupDto) {
        try {
            return new ResponseEntity<>(groupService.createClassGroup(sampleClassGroupDto), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/join/{classGroupId}")
    public ResponseEntity joinClassGroup(@PathVariable(value = "classGroupId") Long classGroupId) {
        try {
            return new ResponseEntity<>(groupService.joinClassGroup(classGroupId), HttpStatus.OK);
        } catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/statistic/{classGroupId}")
    public ResponseEntity getGroupStatistic(@PathVariable(value = "classGroupId") Long classGroupId) {
        try {
            return new ResponseEntity<>(groupService.getGroupStatistic(classGroupId), HttpStatus.OK);
        } catch (GroupNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/cities")
    public ResponseEntity getCities() {
        try {
            return new ResponseEntity<>(groupService.getCities(), HttpStatus.OK);
        } catch (GroupNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


}
