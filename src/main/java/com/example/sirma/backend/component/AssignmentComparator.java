package com.example.sirma.backend.component;

import java.time.LocalDate;
import java.util.Comparator;

public class AssignmentComparator implements Comparator<Assignment> {
    @Override
    public int compare(Assignment o1, Assignment o2) {
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
