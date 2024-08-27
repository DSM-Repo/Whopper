package com.example.whopper.domain.library;

import com.example.whopper.interfaces.library.dto.LibraryElementDto;
import com.example.whopper.interfaces.resume.dto.response.ResumeResponse;

import java.time.LocalDateTime;
import java.util.List;

public record LibraryModel(
        String id,
        Integer year,
        Integer grade,
        String filePath,
        LocalDateTime createAt,
        LibraryElementDto.AccessRight accessRight,
        List<LibraryElementDto.ResumeIndex> index
) {
    public Integer getGeneration() {
        return year - grade - 2013;
    }

    public LibraryModel changeAccessRight(LibraryElementDto.AccessRight accessRight) {
        return new LibraryModel(id, year, grade, filePath, createAt, accessRight, index);
    }
}