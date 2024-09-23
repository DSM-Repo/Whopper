package com.repo.whopper.interfaces.library;

import com.repo.whopper.application.library.usecase.ChangeLibraryAccessRightUseCase;
import com.repo.whopper.common.annotation.OnlyTeacher;
import com.repo.whopper.common.swagger.library.ChangeLibraryApiDocumentation;
import com.repo.whopper.interfaces.library.dto.LibraryElementDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/library")
public class ChangeLibraryController implements ChangeLibraryApiDocumentation {
    private final ChangeLibraryAccessRightUseCase changeLibraryAccessRightUseCase;

    @OnlyTeacher
    @PatchMapping("/{libraryId}")
    public void changeLibraryAccessRight(
            @PathVariable String libraryId,
            @RequestParam(name = "access-right") LibraryElementDto.AccessRight accessRight
    ) {
        changeLibraryAccessRightUseCase.changeLibraryAccessRight(libraryId, accessRight);
    }
}
