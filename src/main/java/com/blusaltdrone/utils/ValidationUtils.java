package com.blusaltdrone.utils;

import com.blusaltdrone.exceptions.BadRequestException;

import java.util.Collection;
import java.util.Objects;

public class ValidationUtils {

    public static void validateNotNull(Object obj, String fieldName) {
        if (Objects.isNull(obj)) {
            throw new BadRequestException(fieldName + " cannot be null");
        }
    }

    public static void validateNotEmpty(String value, String fieldName) {
        if (Objects.isNull(value) || value.trim().isEmpty()) {
            throw new BadRequestException(fieldName + " cannot be empty");
        }
    }

    public static void validatePositive(int number, String fieldName) {
        if (number < 0) {
            throw new BadRequestException(fieldName + " cannot be negative");
        }
    }

    public static <T> void validateNotEmpty(Collection<T> collection, String fieldName) {
        if (Objects.isNull(collection) || collection.isEmpty()) {
            throw new BadRequestException(fieldName + " cannot be null or empty");
        }
    }
}