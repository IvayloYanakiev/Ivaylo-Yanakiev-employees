package com.example.sirma.backend.controller;

import com.example.sirma.backend.component.OverlappingAssignment;
import com.example.sirma.backend.service.ProjectAssignmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class ProjectAssignmentController {

    private final ProjectAssignmentService projectAssignmentService;

    public ProjectAssignmentController(ProjectAssignmentService projectAssignmentService) {
        this.projectAssignmentService = projectAssignmentService;
    }

    @PostMapping("/upload")
    public ResponseEntity<List<OverlappingAssignment>> checkOverlappingAssignments(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(projectAssignmentService.checkOverlappingAssignments(file));
    }
}
