package com.example.whopper.domain.major.api;

import com.example.whopper.domain.major.application.usecase.AddMajorUseCase;
import com.example.whopper.domain.major.application.usecase.DeleteMajorUseCase;
import com.example.whopper.domain.major.application.usecase.FindMajorUseCase;
import com.example.whopper.domain.major.domain.MajorEntity;
import com.example.whopper.global.annotation.OnlyTeacher;
import com.example.whopper.global.utils.DataResponseInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/major")
public class MajorController {
    private final AddMajorUseCase addMajorUseCase;
    private final FindMajorUseCase findMajorUseCase;
    private final DeleteMajorUseCase deleteMajorUseCase;

    @OnlyTeacher
    @PostMapping
    public void add(@RequestBody AddMajorRequest request) {
        addMajorUseCase.add(request.majors());
    }

    @GetMapping
    public DataResponseInfo<MajorEntity> findAll() {
        return findMajorUseCase.findAll();
    }

    @OnlyTeacher
    @DeleteMapping("/{majorId}")
    public void delete(@PathVariable String majorId) {
        deleteMajorUseCase.delete(majorId);
    }

    private record AddMajorRequest(List<String> majors) {}
}
