package com.example.whopper.domain.document.dao;

import com.example.whopper.domain.document.domain.DocumentEntity;
import com.example.whopper.domain.document.domain.element.DocumentStatus;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.stream.Stream;

public interface DocumentMongoRepository extends MongoRepository<DocumentEntity, String> {
    Optional<DocumentEntity> findByStudent_Id(String writerId);
    Stream<DocumentEntity> findAllByStatus(DocumentStatus status);

    @Aggregation(pipeline = {
            "{ $lookup: { " +
                    "    from: 'user_repo', " +
                    "    localField: 'student', " +
                    "    foreignField: '_id', " +
                    "    as: 'studentInfo' " +
                    "} }",
            "{ $unwind: '$studentInfo' }",
            "{ $lookup: { " +
                    "    from: 'major_repo', " +
                    "    localField: 'studentInfo.major', " +
                    "    foreignField: '_id', " +
                    "    as: 'majorInfo' " +
                    "} }",
            "{ $unwind: '$majorInfo' }",
            "{ $match: {" +
                    "$and:  [" +
                        "{$or: [" +
                            "{'studentInfo.name': {$regex: ?0} }," +
                            "{ $expr: { $eq:  [?0, null] } }" +
                        "] }," +
                        "{$or: [" +
                            "{'studentInfo.classInfo.grade': {$regex: ?1} }," +
                            "{ $expr: { $eq:  [?1, null] } }" +
                        "] }," +
                        "{$or: [" +
                            "{'studentInfo.classInfo.classNumber': {$regex: ?2} }," +
                            "{ $expr: { $eq:  [?2, null] } }" +
                        "] }," +
                        "{$or: [" +
                            "{'majorInfo._id': {$regex: ?3} }," +
                            "{ $expr: { $eq:  [?3, null] } }" +
                        "] }," +
                        "{$or: [" +
                            "{'status': {$regex: ?4} }," +
                            "{ $expr: { $eq:  [?4, null] } }" +
                        "] }" +
                    "] } }",
            "{ $sort: { 'studentInfo.classInfo.schoolNumber': 1 } }"
    })
    Stream<DocumentEntity> searchDocuments(
            String name,
            Integer grade,
            Integer classNumber,
            String majorId,
            String status
    );

}
