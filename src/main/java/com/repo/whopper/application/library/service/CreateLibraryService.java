package com.repo.whopper.application.library.service;

import com.repo.whopper.application.library.usecase.CreateLibraryUseCase;
import com.repo.whopper.domain.library.LibraryModel;
import com.repo.whopper.domain.library.LibraryRepository;
import com.repo.whopper.common.http.response.DataResponseInfo;
import com.repo.whopper.interfaces.library.dto.LibraryElementDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateLibraryService implements CreateLibraryUseCase {
    private final LibraryRepository libraryRepository;

    @Override
    @Transactional
    public void createLibrary(Integer grade, String filePath, DataResponseInfo<LibraryElementDto.ResumeIndex> index) {
        LocalDateTime now = LocalDateTime.now();

        libraryRepository.save(new LibraryModel(null, now.getYear(), grade, filePath, now, LibraryElementDto.AccessRight.PRIVATE, index.data()));
    }
}