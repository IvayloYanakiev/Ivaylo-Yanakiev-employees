package com.example.sirma.backend.component;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Objects;

public class AssignmentComparator implements Comparator<Assignment> {
    @Override
    public int compare(Assignment o1, Assignment o2) {
//        if (Objects.equals(o1.getEmployeeId(), o2.getEmployeeId()) && Objects.equals(o1.getProjectId(), o2.getProjectId()) && o1.getDateFrom().equals(o2.getDateFrom()) && o1.getDateTo().equals(o2.getDateTo())) {
//            return 0;
//        }
        LocalDate o1dateFrom = o1.getDateFrom();
        LocalDate o2dateFrom = o2.getDateFrom();
        if (o1dateFrom.isBefore(o2dateFrom)) {
            return -1;
        } else if (o1dateFrom.isAfter(o2dateFrom)) {
            return 1;
        }
        else if (o1.getDateTo().isBefore(o2.getDateTo())) {
            return -1;
        } else if (o1.getDateTo().isAfter(o2.getDateTo())) {
            return 1;
        }
        else {
            return 0;
        }
    }
}
