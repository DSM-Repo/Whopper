package com.example.whopper.domain.major.api;

import com.example.whopper.domain.major.application.usecase.AddMajorUseCase;
import com.example.whopper.domain.major.application.usecase.DeleteMajorUseCase;
import com.example.whopper.domain.major.application.usecase.FindMajorUseCase;
import com.example.whopper.domain.major.domain.MajorEntity;
import com.example.whopper.global.utils.DataResponseInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/major")
public class MajorController {
    private final AddMajorUseCase addMajorUseCase;
    private final FindMajorUseCase findMajorUseCase;
    private final DeleteMajorUseCase deleteMajorUseCase;

    @PostMapping
    public void add(@RequestParam String input) {
        addMajorUseCase.add(input);
    }

    @GetMapping
    public DataResponseInfo<MajorEntity> findAll() {
        return findMajorUseCase.findAll();
    }

    @DeleteMapping("/{majorId}")
    public void delete(@PathVariable String majorId) {
        deleteMajorUseCase.delete(majorId);
    }
}
