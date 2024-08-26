package com.example.whopper.interfaces.resume.dto.response;

import com.example.whopper.domain.resume.ResumeModel;
import com.example.whopper.interfaces.resume.dto.ResumeElementDto;

import java.util.List;

public record ResumeResponse(
        ResumeElementDto.Introduce introduce,
        List<ResumeElementDto.ShardLibrary> recentlyShared
) {
    public static ResumeResponse of(ResumeModel model, List<ResumeElementDto.ShardLibrary> recentlyShared) {
        return new ResumeResponse(
                model.introduce(),
                recentlyShared
        );
    }
}
