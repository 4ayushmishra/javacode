package edu.ccrm.util;

import java.util.Comparator;
import java.util.List;

public class Validators {
    // Lambda for sorting
    public static <T> void sortList(List<T> list, Comparator<T> comparator) {
        list.sort(comparator);
    }

    // Predicate example
    // Used in services
}