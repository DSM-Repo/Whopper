package com.dsm.repo.external.web.rest.major;

import com.dsm.repo.internal.core.usecase.major.AddMajorUseCase;
import com.dsm.repo.internal.core.usecase.major.DeleteMajorUseCase;
import com.dsm.repo.internal.core.usecase.major.FindMajorUseCase;
import com.dsm.repo.external.security.auth.annotation.OnlyTeacher;
import com.dsm.repo.internal.core.domain.model.major.MajorModel;
import com.dsm.repo.external.web.rest.common.DataResponseInfo;
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

    private record AddMajorRequest(
            List<String> majors
    ) {
    }

}
