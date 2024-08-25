package com.example.whopper.domain.resume;

import com.example.whopper.interfaces.resume.dto.ResumeElementDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ResumeElementMapper {
    ResumeElementDto.Status toStatusDto(ResumeEntity.Status status);
    ResumeEntity.Status toStatusEntity(ResumeElementDto.Status status);

    @Mapping(target = "schoolInfo", source = "schoolInfo")
    @Mapping(target = "major", source = "major")
    ResumeElementDto.Writer toWriterDto(ResumeEntity.Writer writer);

    @Mapping(target = "schoolInfo", source = "schoolInfo")
    @Mapping(target = "major", source = "major")
    ResumeEntity.Writer toWriterEntity(ResumeElementDto.Writer writer);

    ResumeElementDto.Writer.SchoolInfo toSchoolInfoDto(ResumeEntity.Writer.SchoolInfo schoolInfo);
    ResumeEntity.Writer.SchoolInfo toSchoolInfoEntity(ResumeElementDto.Writer.SchoolInfo schoolInfo);

    ResumeElementDto.Writer.Major toMajorDto(ResumeEntity.Writer.Major major);
    ResumeEntity.Writer.Major toMajorEntity(ResumeElementDto.Writer.Major major);

    ResumeElementDto.Introduce toIntroduceDto(ResumeEntity.Introduce introduce);
    ResumeEntity.Introduce toIntroduceEntity(ResumeElementDto.Introduce introduce);

    @Mapping(target = "logo", source = "logo")
    @Mapping(target = "sections", source = "sections")
    @Mapping(target = "date", source = "date")
    ResumeElementDto.Project toProjectDto(ResumeEntity.Project project);

    @Mapping(target = "logo", source = "logo")
    @Mapping(target = "sections", source = "sections")
    @Mapping(target = "date", source = "date")
    ResumeEntity.Project toProjectEntity(ResumeElementDto.Project project);

    ResumeEntity.Project.Logo toLogoEntity(ResumeElementDto.Project.Logo image);
    ResumeElementDto.Project.Logo toLogoDto(ResumeEntity.Project.Logo logo);

    ResumeEntity.Project.Section toSectionEntity(ResumeElementDto.Project.Section section);
    ResumeElementDto.Project.Section toSectionDto(ResumeEntity.Project.Section section);

    @Mapping(target = "date", source = "date")
    ResumeElementDto.Achievement toAchievementDto(ResumeEntity.Achievement achievement);
    @Mapping(target = "date", source = "date")
    ResumeEntity.Achievement toAchievementEntity(ResumeElementDto.Achievement achievement);

    @Mapping(target = "date", source = "date")
    ResumeElementDto.Activity toActivityDto(ResumeEntity.Activity activity);
    @Mapping(target = "date", source = "date")
    ResumeEntity.Activity toActivityEntity(ResumeElementDto.Activity activity);

    ResumeEntity.Date toDateEntity(ResumeElementDto.Date date);
    ResumeElementDto.Date toDateDto(ResumeEntity.Date date);

    @Mapping(target = "date", source = "date")
    List<ResumeElementDto.Project> toProjectDtoList(List<ResumeEntity.Project> projectList);
    @Mapping(target = "date", source = "date")
    List<ResumeEntity.Project> toProjectEntityList(List<ResumeElementDto.Project> projectList);

    @Mapping(target = "date", source = "date")
    List<ResumeElementDto.Achievement> toAchievementDtoList(List<ResumeEntity.Achievement> achievementList);
    @Mapping(target = "date", source = "date")
    List<ResumeEntity.Achievement> toAchievementEntityList(List<ResumeElementDto.Achievement> achievementList);
    @Mapping(target = "date", source = "date")
    List<ResumeElementDto.Activity> toActivityDtoList(List<ResumeEntity.Activity> activityList);
    @Mapping(target = "date", source = "date")
    List<ResumeEntity.Activity> toActivityEntityList(List<ResumeElementDto.Activity> activityList);
}