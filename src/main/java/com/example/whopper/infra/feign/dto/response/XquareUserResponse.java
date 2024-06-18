package com.example.whopper.infra.feign.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class XquareUserResponse {

    private UUID id;

    private String account_id;

    private String password;

    private String name;

    private Integer grade;

    private Integer class_num;

    private Integer num;

    private String user_role;

    private String profileImgUrl;

    private String clubName;
}
