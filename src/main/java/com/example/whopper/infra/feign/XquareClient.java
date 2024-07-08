package com.example.whopper.infra.feign;

import com.example.whopper.domain.auth.dto.request.StudentLoginRequest;
import com.example.whopper.infra.feign.dto.response.XquareUserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "xquare-client", url = "${key.login-api-url}")
    public interface XquareClient {

    @RequestMapping(method = RequestMethod.POST, value = "/user-data")
    XquareUserResponse xquareUser(@RequestBody StudentLoginRequest request);

    @RequestMapping(method = RequestMethod.PATCH, value = "/modify-profile")
    void modifyProfile(@RequestParam("account_id") String accountId, @RequestParam("profile_img_url") String profileImgUrl);
}