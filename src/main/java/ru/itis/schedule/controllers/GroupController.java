package ru.itis.schedule.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.schedule.models.Group;
import ru.itis.schedule.services.GroupService;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping
    public ResponseEntity<?> addGroup(Group group){
        groupService.save(group);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<Group>> getGroups(){
        return new ResponseEntity<>(groupService.getGroups(), HttpStatus.OK);
    }

    @GetMapping("/groupset-id/{id}")
    public ResponseEntity<List<Group>> getGroupsByCourse(@PathVariable("id") Long id){
        return new ResponseEntity<>(groupService.getGroupsByGroupSet(id), HttpStatus.OK);
    }
}
