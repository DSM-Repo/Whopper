package com.example.whopper.infra.feign;

import com.example.whopper.domain.auth.dto.request.StudentLoginRequest;
import com.example.whopper.infra.feign.dto.response.XquareUserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "xquare-client", url = "${key.login-api-url}")
public interface XquareClient {

    @PostMapping("/user-data")
    XquareUserResponse xquareUser(@RequestBody StudentLoginRequest request);

    @PatchMapping("/modify-profile")
    void modifyProfile(@RequestParam("account_id") String accountId, @RequestParam("profile_img_url") String profileImgUrl);
}