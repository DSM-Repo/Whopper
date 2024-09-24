package com.repo.whopper.common.swagger.library;

import com.repo.whopper.common.error.ErrorResponse;
import com.repo.whopper.common.http.dto.DataResponseInfo;
import com.repo.whopper.interfaces.library.dto.response.LibraryDetailResponse;
import com.repo.whopper.interfaces.library.dto.response.LibraryResponse;
import com.repo.whopper.interfaces.student.dto.CurrentStudentInfoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;

@Tag(name = "Find_library", description = "도서관 조회 API")
public interface FindLibraryApiDocumentation {
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK, 성공!",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = LibraryDetailResponse.class
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "요청하신 라이브러리를 찾지 못했습니다.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ErrorResponse.class
                            )
                    )
            )
    })
    @Operation(
            summary = "도서관 문서 상세 조회 API (비공개 문서 조회용)",
            description = "클라이언트에서 받은 id로 문서를 검색합니다."
    )
    LibraryDetailResponse getLibraryDetailResponse(String libraryId);

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK, 성공!",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = LibraryResponse.class
                            )
                    )
            )
    })
    @Operation(
            summary = "도서관 문서 목록 조회 API",
            description = "클라이언트에서 받은 년도로 문서 목록을 검색합니다."
    )
    DataResponseInfo<LibraryResponse> findLibrary(Integer year);

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK, 성공!",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = CurrentStudentInfoResponse.class
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "요청하신 라이브러리를 찾지 못했습니다.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ErrorResponse.class
                            )
                    )
            )
    })
    @Operation(
            summary = "도서관 문서 목록 조회 API (공개된 문서 조회용)",
            description = "클라이언트에서 받은 년도로 문서 목록을 검색합니다."
    )
    LibraryDetailResponse findLibraryDetail(String libraryId);
}
