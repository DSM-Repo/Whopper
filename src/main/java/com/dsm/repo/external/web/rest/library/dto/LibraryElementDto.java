package com.dsm.repo.external.web.rest.library.dto;

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