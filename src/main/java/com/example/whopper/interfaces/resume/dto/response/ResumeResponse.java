package com.example.whopper.interfaces.resume.dto.response;

import com.example.whopper.domain.library.LibraryModel;
import com.example.whopper.domain.resume.ResumeModel;
import com.example.whopper.interfaces.resume.dto.ResumeElementDto;

import java.util.List;

public record ResumeResponse(
        ResumeElementDto.Introduce introduce,
        List<ShardLibrary> recentlyShared
) {
    public static ResumeResponse of(ResumeModel model, List<ShardLibrary> recentlyShared) {
        return new ResumeResponse(
                model.introduce(),
                recentlyShared
        );
    }

    public record ShardLibrary(
            String id,
            Integer year,
            Integer grade,
            Integer generation
    ) {
        public static ResumeResponse.ShardLibrary toShardLibrary(LibraryModel library) {
            return new ResumeResponse.ShardLibrary(
                    library.id(),
                    library.year(),
                    library.grade(),
                    library.getGeneration()
            );
        }
    }
}