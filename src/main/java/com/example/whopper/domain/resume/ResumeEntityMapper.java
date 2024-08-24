package com.example.whopper.domain.resume;

import java.util.Optional;

public class ResumeEntityMapper {

    public static Optional<DocumentEntity> toOptionalEntity(Optional<ResumeModel> model) {
        return model.map(ResumeEntityMapper::toEntity);
    }

    public static Optional<ResumeModel> toOptionalModel(Optional<DocumentEntity> entity) {
        return entity.map(ResumeEntityMapper::toModel);
    }

    public static DocumentEntity toEntity(ResumeModel model) {
        return DocumentEntity.builder()
                .id(model.id())
                .year(model.year())
                .status(ResumeElementMapper.toStatusEntity(model.status()))
                .writer(ResumeElementMapper.toWriterEntity(model.writer()))
                .introduce(ResumeElementMapper.toIntroduceEntity(model.introduce()))
                .projectList(ResumeElementMapper.toProjectEntityList(model.projectList()))
                .achievementList(ResumeElementMapper.toAchievementEntityList(model.achievementList()))
                .activityList(ResumeElementMapper.toActivityEntityList(model.activityList()))
                .build();
    }

    public static ResumeModel toModel(DocumentEntity entity) {
        return ResumeModel.builder()
                .id(entity.getId())
                .year(entity.getYear())
                .status(ResumeElementMapper.toStatusDto(entity.getStatus()))
                .writer(ResumeElementMapper.toWriterDto(entity.getWriter()))
                .introduce(ResumeElementMapper.toIntroduceDto(entity.getIntroduce()))
                .projectList(ResumeElementMapper.toProjectDtoList(entity.getProjectList()))
                .achievementList(ResumeElementMapper.toAchievementDtoList(entity.getAchievementList()))
                .activityList(ResumeElementMapper.toActivityDtoList(entity.getActivityList()))
                .build();
    }

}