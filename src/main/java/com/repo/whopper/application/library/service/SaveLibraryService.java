package com.repo.whopper.application.library.service;

import com.repo.whopper.application.library.usecase.SaveLibraryUseCase;
import com.repo.whopper.domain.library.LibraryModel;
import com.repo.whopper.domain.library.LibraryRepository;
import com.repo.whopper.common.http.dto.DataResponseInfo;
import com.repo.whopper.interfaces.library.dto.LibraryElementDto;
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