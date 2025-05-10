package com.example.sirma.backend.service.impl;

import com.example.sirma.backend.component.Assignment;
import com.example.sirma.backend.component.AssignmentComparator;
import com.example.sirma.backend.component.OverlappingAssignment;
import com.example.sirma.backend.exception.AssignmentException;
import com.example.sirma.backend.service.ProjectAssignmentService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

@Service
public class ProjectAssignmentServiceImpl implements ProjectAssignmentService {

    @Override
    public List<OverlappingAssignment> checkOverlappingAssignments(MultipartFile multipartFile) {

        Map<Integer, Set<Assignment>> assignmentsMap = readFile(multipartFile);

        List<OverlappingAssignment> overlappingAssignments = new LinkedList<>();
        for (Map.Entry<Integer, Set<Assignment>> entry : assignmentsMap.entrySet()) {
            Set<Assignment> assignments = entry.getValue();

            List<Assignment> assignmentList = new ArrayList<>(assignments);

            for (int i = 0; i < assignmentList.size(); i++) {
                Assignment assignmentOne = assignmentList.get(i);
                for (int j = i + 1; j < assignmentList.size(); j++) {
                    Assignment assignmentTwo = assignmentList.get(j);
                    if (Objects.equals(assignmentOne.getEmployeeId(), assignmentTwo.getEmployeeId())) {
                        continue;
                    }
                    compareAssignments(overlappingAssignments, assignmentOne, assignmentTwo);
                }
            }
        }
        return overlappingAssignments;
    }

    private Map<Integer, Set<Assignment>> readFile(MultipartFile multipartFile) {
        Map<Integer, Set<Assignment>> assignmentsMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(multipartFile.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] rowValues = line.split(",");

                Integer projectId = Integer.parseInt(rowValues[1]);
                LocalDate dateFrom = LocalDate.parse(rowValues[2]);
                LocalDate dateTo = rowValues[3].equals("NULL") ? LocalDate.now() : LocalDate.parse(rowValues[3]);
                if (dateFrom.isAfter(dateTo)) continue;

                fillMap(assignmentsMap, rowValues, projectId, dateFrom, dateTo);
            }
        } catch (IOException e) {
            throw new AssignmentException();
        }
        return assignmentsMap;
    }

    private void fillMap(Map<Integer, Set<Assignment>> assignmentsMap, String[] rowValues, Integer projectId, LocalDate dateFrom, LocalDate dateTo) {
        assignmentsMap.computeIfAbsent(projectId, k -> new TreeSet<>(new AssignmentComparator()))
                .add(Assignment.builder()
                        .employeeId(Integer.parseInt(rowValues[0]))
                        .projectId(Integer.parseInt(rowValues[1]))
                        .dateFrom(dateFrom)
                        .dateTo(dateTo)
                        .build());
    }

    private void compareAssignments(List<OverlappingAssignment> overlappingAssignments, Assignment assignmentOne, Assignment assignmentTwo) {
        if (!assignmentOne.getDateTo().isBefore(assignmentTwo.getDateFrom())) {
            LocalDate overlapStart = assignmentOne.getDateFrom().isAfter(assignmentTwo.getDateFrom()) ? assignmentOne.getDateFrom() : assignmentTwo.getDateFrom();
            LocalDate overlapEnd = assignmentOne.getDateTo().isBefore(assignmentTwo.getDateTo()) ? assignmentOne.getDateTo() : assignmentTwo.getDateTo();
            Integer days = (int) ChronoUnit.DAYS.between(overlapStart, overlapEnd.plusDays(1));

            overlappingAssignments.add(OverlappingAssignment.builder()
                    .firstEmployeeId(assignmentOne.getEmployeeId())
                    .secondEmployeeId(assignmentTwo.getEmployeeId())
                    .days(days)
                    .projectId(assignmentOne.getProjectId())
                    .build());
        }
    }

}
