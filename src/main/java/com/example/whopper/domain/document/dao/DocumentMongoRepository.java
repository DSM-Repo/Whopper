package com.example.whopper.domain.document.dao;

import com.example.whopper.domain.document.domain.DocumentEntity;
import com.example.whopper.domain.document.domain.element.DocumentStatus;
import jakarta.annotation.Nullable;
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
                            "{ $and: [" +
                                "{ $expr: { $ne: [?0, null] } }," +
                                "{'studentInfo.name': {$regex: ?0, $options: 'i'} }" +
                            "] }," +
                            "{ $expr: { $eq: [?0, null] } }" +
                        "] }," +
                        "{$or: [" +
                            "{ $and: [" +
                                "{ $expr: { $ne: [?1, null] } }," +
                                "{'studentInfo.classInfo.grade': ?1 }" +
                            "] }," +
                            "{ $expr: { $eq: [?1, null] } }" +
                        "] }," +
                        "{$or: [" +
                            "{ $and: [" +
                                "{ $expr: { $ne: [?2, null] } }," +
                                "{'studentInfo.classInfo.classNumber': ?2 }" +
                            "] }," +
                            "{ $expr: { $eq: [?2, null] } }" +
                        "] }," +
                        "{$or: [" +
                            "{ $and: [" +
                                "{ $expr: { $ne: [?3, null] } }," +
                                "{'majorInfo._id': {$regex: ?3, $options: 'i'} }" +
                            "] }," +
                            "{ $expr: { $eq: [?3, null] } }" +
                        "] }," +
                        "{$or: [" +
                            "{ $and: [" +
                                "{ $expr: { $ne: [?4, null] } }," +
                                "{'status': {$regex: ?4, $options: 'i'} }" +
                            "] }," +
                            "{ $expr: { $eq: [?4, null] } }" +
                        "] }" +
                    "] } }",
            "{ $sort: { 'studentInfo.classInfo.schoolNumber': 1 } }"
    })
    Stream<DocumentEntity> searchDocuments(
            @Nullable String name,
            @Nullable Integer grade,
            @Nullable Integer classNumber,
            @Nullable String majorId,
            @Nullable String status
    );

}
