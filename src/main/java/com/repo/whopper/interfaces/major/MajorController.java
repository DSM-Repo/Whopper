package com.repo.whopper.interfaces.major;

import com.repo.whopper.application.major.usecase.AddMajorUseCase;
import com.repo.whopper.application.major.usecase.DeleteMajorUseCase;
import com.repo.whopper.application.major.usecase.FindMajorUseCase;
import com.repo.whopper.common.annotation.OnlyTeacher;
import com.repo.whopper.domain.major.MajorModel;
import com.repo.whopper.common.http.response.DataResponseInfo;
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
    public DataResponseInfo<MajorModel> findAll() {
        return findMajorUseCase.findAll();
    }

    @OnlyTeacher
    @PutMapping("/del")
    public void delete(@RequestBody IdRequest request) {
        deleteMajorUseCase.delete(request.id);
    }

    private record AddMajorRequest(List<String> majors) {}
    private record IdRequest(String id) {}
}
