package com.atguigu.ssyx.home.api;

import com.atguigu.ssyx.common.result.Result;
import com.atguigu.ssyx.common.security.AuthContextHolder;
import com.atguigu.ssyx.home.service.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Api(tags = "商品详情")
@RestController
@RequestMapping("api/home")
public class ItemApiController {

   @Resource
   private ItemService itemService;

   @ApiOperation(value = "获取sku详细信息")
   @GetMapping("item/{id}")
   public Result index(@PathVariable Long id, HttpServletRequest request) {
      Long userId = AuthContextHolder.getUserId();
      return Result.ok(itemService.item(id, userId));
   }

}