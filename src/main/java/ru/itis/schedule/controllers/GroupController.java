package ru.itis.schedule.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
        return ResponseEntity.ok(groupService.getGroups());
    }

    @GetMapping("/course-id/{course}")
    public ResponseEntity<List<Group>> getGroupsByCourse(@PathVariable("course") Long course){
        return ResponseEntity.ok(groupService.getGroupsByCourse(course));
    }
}
