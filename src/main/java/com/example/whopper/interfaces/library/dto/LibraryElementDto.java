package com.example.whopper.interfaces.library.dto;

public class LibraryElementDto {

    public record ResumeIndex(
            String name,
            String major,
            Integer studentNumber,
            Integer pageNumber
    ) {
    }

    public enum AccessRight {
        PUBLIC,
        PRIVATE
    }
}
