package com.example.sirma.backend.service;

import com.example.sirma.backend.component.OverlappingAssignment;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProjectAssignmentService {

    /**
     * Check for overlapping assignments
     *
     * @param multipartFile multipartFile
     * @return list of overlapping assignments
     */
    List<OverlappingAssignment> checkOverlappingAssignments(MultipartFile multipartFile);
}
