package com.example.sirma.backend.component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class OverlappingAssignment {
    private Integer firstEmployeeId;
    private Integer secondEmployeeId;
    private Integer days;
    private Integer projectId;
}
