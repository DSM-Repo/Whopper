package com.example.whopper.domain.document.dao;

import com.example.whopper.domain.document.domain.DocumentEntity;
import com.example.whopper.domain.document.domain.element.DocumentStatus;
import com.example.whopper.domain.document.dto.request.SearchDocumentRequest;
import com.example.whopper.global.utils.mongo.MongoUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class DocumentRepositoryImpl extends AbstractDocumentRepository {
    private final MongoUtils mongoUtils;

    public DocumentRepositoryImpl(DocumentMongoRepository documentMongoRepository, MongoUtils mongoUtils) {
        super(documentMongoRepository);
        this.mongoUtils = mongoUtils;
    }

    @Override
    public Stream<DocumentEntity> searchDocument(SearchDocumentRequest request) {
        Query query = searchQuery(request);
        query.with(getSort());

        return mongoUtils.find(query, DocumentEntity.class);
    }

    private Query searchQuery(SearchDocumentRequest request) {
        Query query = new Query();

        if (isNotBlank(request.name())) {
            query.addCriteria(where("student.name", request.name()));
        }
        if (isNotBlank(request.majorId())) {
            query.addCriteria(where("student.majorId", request.majorId()));
        }

        Optional.ofNullable(request.classNumber())
                .ifPresent(classNumber -> query.addCriteria(where("student.classInfo.classNumber", classNumber)));

        Optional.ofNullable(request.grade())
                .ifPresent(grade -> query.addCriteria(where("student.classInfo.grade", grade)));

        if (isNotBlank(request.status())) {
            query.addCriteria(where("status", DocumentStatus.valueOf(request.status().toUpperCase()).name()));
        }

        return query;
    }

    private Sort getSort() {
        return Sort.by(Sort.DEFAULT_DIRECTION, "student.classInfo.schoolNumber");
    }

    private Criteria where(String key, Object checkValue) {
        return Criteria.where(key).is(checkValue);
    }

    private boolean isNotBlank(String str) {
        return str != null && !str.trim().isEmpty();
    }
}
