package com.example.sirma.backend.component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Getter
public final class Assignment {

    private Integer employeeId;
    private Integer projectId;
    private LocalDate dateFrom;
    private LocalDate dateTo;

    @Override
    public String toString() {
        return employeeId + "," + projectId + "," + dateFrom.toString() + "," + dateTo.toString();
    }
}
