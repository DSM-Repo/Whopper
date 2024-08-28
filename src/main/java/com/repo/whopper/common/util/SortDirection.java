package com.repo.whopper.common.util;

public enum SortDirection {
    ASC, DESC;

    public static String getDefault() {
        return ASC.name();
    }
}
