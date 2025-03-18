package com.dsm.repo.internal.data.repository.resume;

import com.dsm.repo.external.web.rest.resume.dto.ResumeElementDto;
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
    ResumeElementDto.Writer toWriterDto(ResumeEntity.Writer writer);

    @Mapping(target = "schoolInfo", source = "schoolInfo")
    ResumeEntity.Writer toWriterEntity(ResumeElementDto.Writer writer);

    ResumeElementDto.Writer.SchoolInfo toSchoolInfoDto(ResumeEntity.Writer.SchoolInfo schoolInfo);
    ResumeEntity.Writer.SchoolInfo toSchoolInfoEntity(ResumeElementDto.Writer.SchoolInfo schoolInfo);

    ResumeElementDto.Introduce toIntroduceDto(ResumeEntity.Introduce introduce);
    ResumeEntity.Introduce toIntroduceEntity(ResumeElementDto.Introduce introduce);

    @Mapping(target = "logo", source = "logo")
    @Mapping(target = "sections", source = "sections")
    ResumeElementDto.Project toProjectDto(ResumeEntity.Project project);

    @Mapping(target = "logo", source = "logo")
    @Mapping(target = "sections", source = "sections")
    ResumeEntity.Project toProjectEntity(ResumeElementDto.Project project);

    ResumeEntity.Project.Logo toLogoEntity(ResumeElementDto.Project.Logo image);
    ResumeElementDto.Project.Logo toLogoDto(ResumeEntity.Project.Logo logo);

    ResumeEntity.Project.Section toSectionEntity(ResumeElementDto.Project.Section section);
    ResumeElementDto.Project.Section toSectionDto(ResumeEntity.Project.Section section);

    ResumeElementDto.Achievement toAchievementDto(ResumeEntity.Achievement achievement);
    ResumeEntity.Achievement toAchievementEntity(ResumeElementDto.Achievement achievement);

    ResumeElementDto.Activity toActivityDto(ResumeEntity.Activity activity);
    ResumeEntity.Activity toActivityEntity(ResumeElementDto.Activity activity);

    ResumeEntity.Date toDateEntity(ResumeElementDto.Date date);
    ResumeElementDto.Date toDateDto(ResumeEntity.Date date);

    List<ResumeElementDto.Project> toProjectDtoList(List<ResumeEntity.Project> projectList);
    List<ResumeEntity.Project> toProjectEntityList(List<ResumeElementDto.Project> projectList);

    List<ResumeElementDto.Achievement> toAchievementDtoList(List<ResumeEntity.Achievement> achievementList);
    List<ResumeEntity.Achievement> toAchievementEntityList(List<ResumeElementDto.Achievement> achievementList);

    List<ResumeElementDto.Activity> toActivityDtoList(List<ResumeEntity.Activity> activityList);
    List<ResumeEntity.Activity> toActivityEntityList(List<ResumeElementDto.Activity> activityList);
}