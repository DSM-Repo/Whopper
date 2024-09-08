package com.repo.whopper.interfaces.major;

import com.repo.whopper.application.major.usecase.AddMajorUseCase;
import com.repo.whopper.application.major.usecase.DeleteMajorUseCase;
import com.repo.whopper.application.major.usecase.FindMajorUseCase;
import com.repo.whopper.common.annotation.OnlyTeacher;
import com.repo.whopper.domain.major.MajorModel;
import com.repo.whopper.common.http.response.DataResponseInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/major")
class MajorController {
    private final AddMajorUseCase addMajorUseCase;
    private final FindMajorUseCase findMajorUseCase;
    private final DeleteMajorUseCase deleteMajorUseCase;

    @OnlyTeacher
    @PostMapping
    void add(@RequestBody AddMajorRequest request) {
        addMajorUseCase.add(request.majors());
    }

    @GetMapping
    DataResponseInfo<MajorModel> findAll() {
        return findMajorUseCase.findAll();
    }

    @OnlyTeacher
    @DeleteMapping("/{name}")
    void delete(@PathVariable String name)  {
        final var decodedName = URLDecoder.decode(name, StandardCharsets.UTF_8);
        final var replacedName = decodedName.replace("_", " ");

        deleteMajorUseCase.delete(replacedName);
    }

    private record AddMajorRequest(List<String> majors) {}
}
