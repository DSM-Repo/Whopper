package com.repo.whopper.common.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    // file
    PDF_UPLOAD_FAILED(500, "Pdf 저장에 실패하였습니다."),
    EXTENSION_NOT_MATCH(400, "지원하지 않는 확장자 타입입니다."),

    // xquare
    XQUARE(503, "DSM-login 서비스에서 에러가 발생했습니다."),

    FORBIDDEN(403, "접근 권한이 없는 유저입니다."),

    // auth
    PASSWORD_MISMATCH(401, "비밀번호가 일치하지 않습니다."),
    INVALID_USER(401, "유효하지 않은 사용자입니다."),

    // jwt
    EXPIRED_TOKEN(401, "만료된 토큰입니다."),
    INVALID_TOKEN(401, "검증되지 않은 토큰입니다."),

    // student
    STUDENT_NOT_FOUND(404, "일치하는 학생을 찾을 수 없습니다."),

    // teacher
    TEACHER_NOT_FOUND(404, "일치하는 선생님을 찾을 수 없습니다."),

    // major
    DUPLICATED_MAJOR(409, "중복되는 전공 이름이 존재합니다."),
    MAJOR_NOT_FOUND(404, "일치하는 전공을 찾지 못했습니다."),

    // resume
    RESUME_NOT_FOUND(404, "요청하신 문서를 찾지 못했습니다."),
    RESUME_ILLEGAL_STATUS(400, "해당 문서에 접근할 수 없는 상태입니다."),
    RESUME_MODIFICATION_NOT_ALLOWED(403, "제출된 문서는 수정이 불가능합니다."),

    // feedback
    FEEDBACK_NOT_FOUND(404, "요청하신 피드백을 찾지 못했습니다."),

    // library
    LIBRARY_NOT_FOUND(404, "요청하신 라이브러리를 찾지 못했습니다."),

    IMAGE_REQUEST_SIZE_MISMATCH(422, "프로젝트의 갯수와, 이미지 갯수가 맞지 않습니다."),

    // general
    BAD_REQUEST(400, "프론트 탓..."),
    INTERNAL_SERVER_ERROR(500, "서버 탓...");

    private final int statusCode;
    private final String message;
}
