package com.example.whopper.common.util;

public enum SortDirection {
    ASC, DESC;

    public static String getDefault() {
        return ASC.name();
    }
}
