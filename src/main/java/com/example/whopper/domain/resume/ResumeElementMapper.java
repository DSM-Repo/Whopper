package com.example.whopper.domain.resume;

import com.example.whopper.interfaces.resume.dto.ResumeElementDto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ResumeElementMapper {

    // DTO to Entity
    public static DocumentEntity.Status toStatusEntity(ResumeElementDto.Status dto) {
        return DocumentEntity.Status.valueOf(dto.name());
    }

    public static DocumentEntity.Introduce toIntroduceEntity(ResumeElementDto.Introduce dto) {
        return new DocumentEntity.Introduce(dto.heading(), dto.introduce());
    }

    public static DocumentEntity.Writer toWriterEntity(ResumeElementDto.Writer dto) {
        return new DocumentEntity.Writer(
                dto.name(),
                toSchoolInfoEntity(dto.schoolInfo()),
                toMajorEntity(dto.major()),
                dto.email(),
                dto.skillSet(),
                dto.url()
        );
    }

    public static DocumentEntity.Writer.SchoolInfo toSchoolInfoEntity(ResumeElementDto.Writer.SchoolInfo dto) {
        return new DocumentEntity.Writer.SchoolInfo(
                dto.grade(), dto.classNumber(), dto.schoolNumber(), dto.generation()
        );
    }

    public static DocumentEntity.Writer.Major toMajorEntity(ResumeElementDto.Writer.Major dto) {
        return new DocumentEntity.Writer.Major(dto.majorId(), dto.majorName());
    }

    public static DocumentEntity.Project toProjectEntity(ResumeElementDto.Project dto) {
        return new DocumentEntity.Project(
                dto.name(),
                toImageEntity(dto.projectLogo()),
                DocumentEntity.Project.Type.valueOf(dto.type().name()),
                toDateEntity(dto.date()),
                dto.skillSet(),
                mapList(dto.sections(), ResumeElementMapper::toSectionEntity),
                dto.url()
        );
    }

    public static DocumentEntity.Project.Image toImageEntity(ResumeElementDto.Project.Logo dto) {
        return new DocumentEntity.Project.Image(dto.imagePath(), dto.originalName());
    }

    public static DocumentEntity.Project.Section toSectionEntity(ResumeElementDto.Project.Section dto) {
        return new DocumentEntity.Project.Section(dto.title(), dto.description());
    }

    public static DocumentEntity.Date toDateEntity(ResumeElementDto.Date dto) {
        return new DocumentEntity.Date(dto.startDate(), dto.endDate());
    }

    public static DocumentEntity.Achievement toAchievementEntity(ResumeElementDto.Achievement dto) {
        return new DocumentEntity.Achievement(
                dto.name(),
                dto.institution(),
                dto.date(),
                DocumentEntity.Achievement.Type.valueOf(dto.type().name())
        );
    }

    public static DocumentEntity.Activity toActivityEntity(ResumeElementDto.Activity dto) {
        return new DocumentEntity.Activity(
                dto.name(),
                toDateEntity(dto.date()),
                dto.isPeriod(),
                dto.description()
        );
    }

    // Entity to DTO
    public static ResumeElementDto.Introduce toIntroduceDto(DocumentEntity.Introduce entity) {
        return new ResumeElementDto.Introduce(entity.heading(), entity.introduce());
    }

    public static ResumeElementDto.Writer toWriterDto(DocumentEntity.Writer entity) {
        return new ResumeElementDto.Writer(
                entity.name(),
                toSchoolInfoDto(entity.schoolInfo()),
                toMajorDto(entity.major()),
                entity.email(),
                entity.skillSet(),
                entity.url()
        );
    }

    public static ResumeElementDto.Writer.SchoolInfo toSchoolInfoDto(DocumentEntity.Writer.SchoolInfo entity) {
        return new ResumeElementDto.Writer.SchoolInfo(
                entity.grade(), entity.classNumber(), entity.schoolNumber(), entity.generation()
        );
    }

    public static ResumeElementDto.Writer.Major toMajorDto(DocumentEntity.Writer.Major entity) {
        return new ResumeElementDto.Writer.Major(entity.majorId(), entity.majorName());
    }

    public static ResumeElementDto.Project toProjectDto(DocumentEntity.Project entity) {
        return new ResumeElementDto.Project(
                entity.name(),
                toLogoDto(entity.projectLogo()),
                ResumeElementDto.Project.Type.valueOf(entity.type().name()),
                toDateDto(entity.date()),
                entity.skillSet(),
                mapList(entity.sections(), ResumeElementMapper::toSectionDto),
                entity.url()
        );
    }

    public static ResumeElementDto.Project.Logo toLogoDto(DocumentEntity.Project.Image entity) {
        return new ResumeElementDto.Project.Logo(entity.imagePath(), entity.originalName());
    }

    public static ResumeElementDto.Project.Section toSectionDto(DocumentEntity.Project.Section entity) {
        return new ResumeElementDto.Project.Section(entity.title(), entity.description());
    }

    public static ResumeElementDto.Date toDateDto(DocumentEntity.Date entity) {
        return new ResumeElementDto.Date(entity.startDate(), entity.endDate());
    }

    public static ResumeElementDto.Achievement toAchievementDto(DocumentEntity.Achievement entity) {
        return new ResumeElementDto.Achievement(
                entity.name(),
                entity.institution(),
                entity.date(),
                ResumeElementDto.Achievement.Type.valueOf(entity.type().name())
        );
    }

    public static ResumeElementDto.Activity toActivityDto(DocumentEntity.Activity entity) {
        return new ResumeElementDto.Activity(
                entity.name(),
                toDateDto(entity.date()),
                entity.isPeriod(),
                entity.description()
        );
    }

    public static ResumeElementDto.Status toStatusDto(DocumentEntity.Status entity) {
        return ResumeElementDto.Status.valueOf(entity.name());
    }

    // List mapping
    private static <T, R> List<R> mapList(List<T> list, Function<T, R> mapper) {
        return list.stream()
                .map(mapper)
                .toList();
    }

    public static List<DocumentEntity.Project> toProjectEntityList(List<ResumeElementDto.Project> dtos) {
        return mapList(dtos, ResumeElementMapper::toProjectEntity);
    }

    public static List<DocumentEntity.Achievement> toAchievementEntityList(List<ResumeElementDto.Achievement> dtos) {
        return mapList(dtos, ResumeElementMapper::toAchievementEntity);
    }

    public static List<DocumentEntity.Activity> toActivityEntityList(List<ResumeElementDto.Activity> dtos) {
        return mapList(dtos, ResumeElementMapper::toActivityEntity);
    }

    public static List<ResumeElementDto.Project> toProjectDtoList(List<DocumentEntity.Project> entities) {
        return mapList(entities, ResumeElementMapper::toProjectDto);
    }

    public static List<ResumeElementDto.Achievement> toAchievementDtoList(List<DocumentEntity.Achievement> entities) {
        return mapList(entities, ResumeElementMapper::toAchievementDto);
    }

    public static List<ResumeElementDto.Activity> toActivityDtoList(List<DocumentEntity.Activity> entities) {
        return mapList(entities, ResumeElementMapper::toActivityDto);
    }
}