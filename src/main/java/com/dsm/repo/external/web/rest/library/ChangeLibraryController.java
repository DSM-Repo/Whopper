package com.dsm.repo.external.web.rest.library;

import com.dsm.repo.internal.core.usecase.library.ChangeLibraryAccessRightUseCase;
import com.dsm.repo.external.security.auth.annotation.OnlyTeacher;
import com.dsm.repo.external.web.documentation.library.ChangeLibraryApiDocumentation;
import com.dsm.repo.external.web.rest.library.dto.LibraryElementDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/library")
class ChangeLibraryController implements ChangeLibraryApiDocumentation {

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
