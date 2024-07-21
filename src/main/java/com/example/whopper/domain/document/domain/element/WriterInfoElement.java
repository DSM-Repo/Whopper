package com.example.whopper.domain.document.domain.element;

import com.example.whopper.domain.student.domain.StudentEntity;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Value;

import java.time.Year;
import java.util.Collections;
import java.util.Set;

@Builder
public record WriterInfoElement(
        Integer generation,
        String email,
        String profileImagePath,
        String major,
        Set<String> skillSet,
        String url
) {
    @Value("${generation.one}")
    private static Integer oneGenYear; // 1gen 2015year 9 2023

    public static WriterInfoElement createEmptyElement(StudentEntity student) {
        return WriterInfoElement.builder()
                .generation(Year.now().getValue() - oneGenYear + 2 - student.getClassInfo().grade())
                .email("")
                .profileImagePath(student.getProfileImagePath())
                .skillSet(Collections.emptySet())
                .url("")
                .build();
    }
}
