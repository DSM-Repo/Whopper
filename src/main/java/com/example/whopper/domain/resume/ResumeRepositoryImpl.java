package com.example.whopper.domain.resume;

import com.example.whopper.infrastructure.mongo.MongoUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
class ResumeRepositoryImpl extends AbstractResumeRepository {
    private final MongoUtils mongoUtils;

    public ResumeRepositoryImpl(ResumeMongoRepository resumeMongoRepository, MongoUtils mongoUtils) {
        super(resumeMongoRepository);
        this.mongoUtils = mongoUtils;
    }

    @Override
    public Stream<DocumentEntity> searchDocuments(String name, Integer grade, Integer classNumber, String majorId, String status) {
        Query query = searchQuery(name, grade, classNumber, majorId, status);
        query.with(getSort());

        return mongoUtils.find(query, DocumentEntity.class);
    }

    private Query searchQuery(String name, Integer grade, Integer classNumber, String majorId, String status) {
        Query query = new Query();

        if (isNotBlank(name)) {
            query.addCriteria(where("privateStudentInfo.name", name));
        }
        if (isNotBlank(majorId)) {
            query.addCriteria(where("privateStudentInfo.majorId", majorId));
        }

        Optional.ofNullable(classNumber)
                .ifPresent(cn -> query.addCriteria(where("privateStudentInfo.classNumber", classNumber)));

        Optional.ofNullable(grade)
                .ifPresent(g -> query.addCriteria(where("privateStudentInfo.grade", grade)));

        if (isNotBlank(status)) {
            query.addCriteria(where("status", status.toUpperCase()));
        }

        return query;
    }

    private Sort getSort() {
        return Sort.by(Sort.DEFAULT_DIRECTION, "privateStudentInfo.schoolNumber");
    }

    private Criteria where(String key, Object checkValue) {
        return Criteria.where(key).is(checkValue);
    }

    private boolean isNotBlank(String str) {
        return str != null && !str.trim().isEmpty();
    }
}
