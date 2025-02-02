package com.atguigu.ssyx.home.api;

import com.atguigu.ssyx.common.result.Result;
import com.atguigu.ssyx.product.client.ProductFeignClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: CategoryApiController
 * Package: com.atguigu.ssyx.home.api
 * Description:
 *
 * @Author liuux
 * @Create 2024/3/12 9:29
 * @Version 1.0
 */

@Api(tags = "商品分类")
@RestController
@RequestMapping("api/home")
public class CategoryApiController {

    @Autowired
    private ProductFeignClient productFeignClient;

    @ApiOperation(value = "获取分类信息")
    @GetMapping("category")
    public Result index() {
        return Result.ok(productFeignClient.findAllCategoryList());
    }

}
