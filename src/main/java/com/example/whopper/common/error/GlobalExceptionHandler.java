package com.example.whopper.common.error;

import com.example.whopper.common.error.exception.WhopperException;
import com.example.whopper.common.error.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    //비즈니스 로직에서의 에러
    @ExceptionHandler(WhopperException.class)
    public ResponseEntity<ErrorResponse> handlePluckException(WhopperException e) {

        ErrorCode errorCode = e.getErrorCode();
        ErrorResponse response = ErrorResponse.of(errorCode, errorCode.getMessage());
        e.printStackTrace();
        log.error(e.toString());

        return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatusCode()));
    }

    // validation 에러
//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException e) {
//
//        ErrorCode errorCode = ErrorCode.BAD_REQUEST;
//        ErrorResponse response = ErrorResponse.of(errorCode, errorCode.getMessage());
//        e.printStackTrace();
//
//        return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatusCode()));
//    }

    //그 외 에러들
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {

        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        ErrorResponse response = ErrorResponse.of(errorCode, e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
