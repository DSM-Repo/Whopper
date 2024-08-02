package com.example.whopper.domain.document.domain;

import com.example.whopper.domain.student.domain.ClassInfo;

import java.time.Year;

public record ShardDocument(
        Integer year,
        Integer generation,
        ClassInfo classInfo,
        String name,
        String documentId
) {
    public static ShardDocument of(Integer year, Integer generation, ClassInfo classInfo, String name, String documentId) {
        return new ShardDocument(year, generation, classInfo, name, documentId);
    }

    public static ShardDocument fromDocumentEntity(DocumentEntity document) {
        var student = document.getStudent();
        return new ShardDocument(
                Year.now().getValue(),
                document.getWriter().generation(),
                student.getClassInfo(),
                student.getName(),
                document.getId()
        );
    }
}
