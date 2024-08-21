package com.example.whopper.infrastructure.xquare.dto.response;

import com.example.whopper.lagacy.student.domain.ClassInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class XquareUserResponse {

    private UUID id;

    private String accountId;

    private String password;

    private String name;

    private Integer grade;

    private Integer classNum;

    private Integer num;

    private String userRole;

    private String profileImgUrl;

    private String clubName;

    public ClassInfo toClassInfo() {
        return ClassInfo.of(grade, classNum, num);
    }
}
