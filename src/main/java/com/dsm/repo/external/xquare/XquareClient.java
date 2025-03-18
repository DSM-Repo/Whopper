package com.dsm.repo.external.xquare;

import com.dsm.repo.external.web.rest.auth.dto.request.LoginRequest;
import com.dsm.repo.external.xquare.dto.XquareUserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "xquare-client", url = "${key.login-api-url}", configuration = XquareRetryConfiguration.class)
public interface XquareClient {
    @PostMapping("/user-data")
    XquareUserResponse xquareUser(@RequestBody LoginRequest request); // TODO: 3/18/25 200 -> login 성공

    @PatchMapping("/modify-profile")
    void modifyProfile(@RequestParam("account_id") String account_id, @RequestParam("profile_img_url") String profileImgUrl);
}