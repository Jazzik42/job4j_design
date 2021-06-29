package ru.job4j.filesearch;

import java.nio.file.Path;
import java.util.function.Predicate;

public class ArgsValidation {

    public static Predicate<Path> validation(final String mask, final String typeMask) {
        Predicate<Path> predicate = null;
        if (typeMask.equals("name")) {
            predicate = x -> x.getFileName().toString().equals(mask);
        } else if (typeMask.equals("mask")) {
            String buffer = mask;
            buffer = buffer.replace(".", "\\.");
            buffer = buffer.replace("*", ".*");
            String finalMask = buffer;
            predicate = x -> x.getFileName().toString().matches(finalMask);
        } else if (typeMask.equals("regex")) {
            predicate = x -> x.getFileName().toString().matches(mask);
        }
        if (predicate == null) {
            throw new IllegalArgumentException("Enter correct search arguments");
        }
        return predicate;
    }
}
