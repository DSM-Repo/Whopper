package com.example.whopper.global.utils;

public enum SortDirection {
    ASC, DESC;

    public static String getDefault() {
        return ASC.name();
    }
}
