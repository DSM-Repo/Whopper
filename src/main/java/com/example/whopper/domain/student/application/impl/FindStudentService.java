package com.example.whopper.domain.student.application.impl;

import com.example.whopper.domain.document.dao.DocumentRepository;
import com.example.whopper.domain.document.domain.DocumentEntity;
import com.example.whopper.domain.student.application.usecase.FindStudentUseCase;
import com.example.whopper.domain.student.domain.StudentInfo;
import com.example.whopper.global.utils.DataResponseInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindStudentService implements FindStudentUseCase {
    private final DocumentRepository documentRepository;

    @Override
    public DataResponseInfo<StudentInfo> findStudentWithNoSubmittedDocumentStatus() {
        var data = documentRepository.getNotSubmittedDocuments()
                .map(DocumentEntity::getStudent)
                .map(StudentInfo::of)
                .toList();

        return DataResponseInfo.of(data);
    }
}
