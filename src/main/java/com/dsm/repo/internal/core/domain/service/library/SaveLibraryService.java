package com.dsm.repo.internal.core.domain.service.library;

import com.dsm.repo.internal.core.usecase.library.SaveLibraryUseCase;
import com.dsm.repo.internal.core.domain.model.library.LibraryModel;
import com.dsm.repo.internal.data.repository.library.LibraryRepository;
import com.dsm.repo.external.web.rest.common.DataResponseInfo;
import com.dsm.repo.external.web.rest.library.dto.LibraryElementDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SaveLibraryService implements SaveLibraryUseCase {
    private final LibraryRepository libraryRepository;

    @Override
    @Transactional
    public void saveLibrary(Integer grade, String filePath, DataResponseInfo<LibraryElementDto.ResumeIndex> index) {
        LocalDateTime now = LocalDateTime.now();

        libraryRepository.save(new LibraryModel(null, now.getYear(), grade, filePath, now, LibraryElementDto.AccessRight.PRIVATE, index.data()));
    }
}