package com.example.whopper.domain.resume;

import com.example.whopper.interfaces.resume.dto.ResumeElementDto;

import java.util.List;
import java.util.function.Function;

public class ResumeElementMapper {

    // DTO to Entity
    public static ResumeEntity.Status toStatusEntity(ResumeElementDto.Status dto) {
        return ResumeEntity.Status.valueOf(dto.name());
    }

    public static ResumeEntity.Introduce toIntroduceEntity(ResumeElementDto.Introduce dto) {
        return new ResumeEntity.Introduce(dto.heading(), dto.introduce());
    }

    public static ResumeEntity.Writer toWriterEntity(ResumeElementDto.Writer dto) {
        return new ResumeEntity.Writer(
                dto.id(),
                dto.name(),
                toSchoolInfoEntity(dto.schoolInfo()),
                toMajorEntity(dto.major()),
                dto.email(),
                dto.skillSet(),
                dto.url()
        );
    }

    public static ResumeEntity.Writer.SchoolInfo toSchoolInfoEntity(ResumeElementDto.Writer.SchoolInfo dto) {
        return new ResumeEntity.Writer.SchoolInfo(
                dto.grade(), dto.classNumber(), dto.schoolNumber(), dto.generation()
        );
    }

    public static ResumeEntity.Writer.Major toMajorEntity(ResumeElementDto.Writer.Major dto) {
        return new ResumeEntity.Writer.Major(dto.majorId(), dto.majorName());
    }

    public static ResumeEntity.Project toProjectEntity(ResumeElementDto.Project dto) {
        return new ResumeEntity.Project(
                dto.name(),
                toImageEntity(dto.projectLogo()),
                ResumeEntity.Project.Type.valueOf(dto.type().name()),
                toDateEntity(dto.date()),
                dto.skillSet(),
                mapList(dto.sections(), ResumeElementMapper::toSectionEntity),
                dto.url()
        );
    }

    public static ResumeEntity.Project.Image toImageEntity(ResumeElementDto.Project.Logo dto) {
        return new ResumeEntity.Project.Image(dto.imagePath(), dto.originalName());
    }

    public static ResumeEntity.Project.Section toSectionEntity(ResumeElementDto.Project.Section dto) {
        return new ResumeEntity.Project.Section(dto.title(), dto.description());
    }

    public static ResumeEntity.Date toDateEntity(ResumeElementDto.Date dto) {
        return new ResumeEntity.Date(dto.startDate(), dto.endDate());
    }

    public static ResumeEntity.Achievement toAchievementEntity(ResumeElementDto.Achievement dto) {
        return new ResumeEntity.Achievement(
                dto.name(),
                dto.institution(),
                dto.date(),
                ResumeEntity.Achievement.Type.valueOf(dto.type().name())
        );
    }

    public static ResumeEntity.Activity toActivityEntity(ResumeElementDto.Activity dto) {
        return new ResumeEntity.Activity(
                dto.name(),
                toDateEntity(dto.date()),
                dto.isPeriod(),
                dto.description()
        );
    }

    // Entity to DTO
    public static ResumeElementDto.Introduce toIntroduceDto(ResumeEntity.Introduce entity) {
        return new ResumeElementDto.Introduce(entity.heading(), entity.introduce());
    }

    public static ResumeElementDto.Writer toWriterDto(ResumeEntity.Writer entity) {
        return new ResumeElementDto.Writer(
                entity.id(),
                entity.name(),
                toSchoolInfoDto(entity.schoolInfo()),
                toMajorDto(entity.major()),
                entity.email(),
                entity.skillSet(),
                entity.url()
        );
    }

    public static ResumeElementDto.Writer.SchoolInfo toSchoolInfoDto(ResumeEntity.Writer.SchoolInfo entity) {
        return new ResumeElementDto.Writer.SchoolInfo(
                entity.grade(), entity.classNumber(), entity.schoolNumber(), entity.generation()
        );
    }

    public static ResumeElementDto.Writer.Major toMajorDto(ResumeEntity.Writer.Major entity) {
        return new ResumeElementDto.Writer.Major(entity.majorId(), entity.majorName());
    }

    public static ResumeElementDto.Project toProjectDto(ResumeEntity.Project entity) {
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

    public static ResumeElementDto.Project.Logo toLogoDto(ResumeEntity.Project.Image entity) {
        return new ResumeElementDto.Project.Logo(entity.imagePath(), entity.originalName());
    }

    public static ResumeElementDto.Project.Section toSectionDto(ResumeEntity.Project.Section entity) {
        return new ResumeElementDto.Project.Section(entity.title(), entity.description());
    }

    public static ResumeElementDto.Date toDateDto(ResumeEntity.Date entity) {
        return new ResumeElementDto.Date(entity.startDate(), entity.endDate());
    }

    public static ResumeElementDto.Achievement toAchievementDto(ResumeEntity.Achievement entity) {
        return new ResumeElementDto.Achievement(
                entity.name(),
                entity.institution(),
                entity.date(),
                ResumeElementDto.Achievement.Type.valueOf(entity.type().name())
        );
    }

    public static ResumeElementDto.Activity toActivityDto(ResumeEntity.Activity entity) {
        return new ResumeElementDto.Activity(
                entity.name(),
                toDateDto(entity.date()),
                entity.isPeriod(),
                entity.description()
        );
    }

    public static ResumeElementDto.Status toStatusDto(ResumeEntity.Status entity) {
        return ResumeElementDto.Status.valueOf(entity.name());
    }

    // List mapping
    private static <T, R> List<R> mapList(List<T> list, Function<T, R> mapper) {
        return list.stream()
                .map(mapper)
                .toList();
    }

    public static List<ResumeEntity.Project> toProjectEntityList(List<ResumeElementDto.Project> dtos) {
        return mapList(dtos, ResumeElementMapper::toProjectEntity);
    }

    public static List<ResumeEntity.Achievement> toAchievementEntityList(List<ResumeElementDto.Achievement> dtos) {
        return mapList(dtos, ResumeElementMapper::toAchievementEntity);
    }

    public static List<ResumeEntity.Activity> toActivityEntityList(List<ResumeElementDto.Activity> dtos) {
        return mapList(dtos, ResumeElementMapper::toActivityEntity);
    }

    public static List<ResumeElementDto.Project> toProjectDtoList(List<ResumeEntity.Project> entities) {
        return mapList(entities, ResumeElementMapper::toProjectDto);
    }

    public static List<ResumeElementDto.Achievement> toAchievementDtoList(List<ResumeEntity.Achievement> entities) {
        return mapList(entities, ResumeElementMapper::toAchievementDto);
    }

    public static List<ResumeElementDto.Activity> toActivityDtoList(List<ResumeEntity.Activity> entities) {
        return mapList(entities, ResumeElementMapper::toActivityDto);
    }
}