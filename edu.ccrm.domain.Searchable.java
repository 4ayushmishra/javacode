package edu.ccrm.domain;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@FunctionalInterface
public interface Searchable<T> {
    List<T> search(Predicate<T> predicate);

    // Default method to demonstrate diamond problem (if another interface has same)
    default List<T> filter(List<T> items, Predicate<T> predicate) {
        return items.stream().filter(predicate).collect(Collectors.toList());
    }
}