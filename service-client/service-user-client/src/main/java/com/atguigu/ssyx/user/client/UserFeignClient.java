package com.atguigu.ssyx.user.client;


import com.atguigu.ssyx.vo.user.LeaderAddressVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-user")
public interface UserFeignClient {

    @GetMapping("/api/user/leader/inner/getLeaderAddressVoByUserId/{userId}")
    public LeaderAddressVo getLeaderAddressVoByUserId(@PathVariable(value = "userId") Long userId);
}