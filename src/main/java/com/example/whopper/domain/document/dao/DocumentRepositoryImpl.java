package com.example.whopper.domain.document.dao;

import com.example.whopper.domain.document.domain.DocumentEntity;
import com.example.whopper.domain.document.dto.FindDocumentDto;
import com.example.whopper.global.utils.SortDirection;
import com.example.whopper.global.utils.mongo.MongoUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DocumentRepositoryImpl extends AbstractDocumentRepository {
    private final MongoUtils mongoUtils;

    public DocumentRepositoryImpl(DocumentMongoRepository documentMongoRepository, MongoUtils mongoUtils) {
        super(documentMongoRepository);
        this.mongoUtils = mongoUtils;
    }

    @Override
    public List<DocumentEntity> findByDetails(FindDocumentDto dto) {
        Query query = findByDetailsQuery(dto);
        query.with(Sort.by(getSortDirection(dto.direction()), getSortBy(dto.sortBy())));

        return mongoUtils.find(query, DocumentEntity.class);
    }

    private Query findByDetailsQuery(FindDocumentDto dto) {
        Query query = new Query();

        if (isNotBlank(dto.name())) {
            query.addCriteria(where("writer.name", dto.name()));
        }
        if (isNotBlank(dto.major())) {
            query.addCriteria(where("writer.major", dto.major()));
        }

        Optional.ofNullable(dto.classNumber())
                .ifPresent(classNumber -> query.addCriteria(where("writer.classNumber", classNumber)));

        Optional.ofNullable(dto.grade())
                .ifPresent(grade -> query.addCriteria(where("writer.grade", grade)));

        Optional.ofNullable(dto.status())
                .ifPresent(status -> query.addCriteria(where("status", status.name())));

        return query;
    }

    private String getSortBy(String sortBy) {
        return isNotBlank(sortBy) ? sortBy : "writer.name";
    }

    private String getSortDirection(SortDirection direction) {
        return Optional.ofNullable(direction)
                .map(Enum::name)
                .orElseGet(SortDirection::getDefault);
    }

    private Criteria where(String key, Object checkValue) {
        return Criteria.where(key).is(checkValue);
    }

    private boolean isNotBlank(String str) {
        return !str.isBlank();
    }
}
