package com.atguigu.ssyx.product.controller;


import com.atguigu.ssyx.common.result.Result;
import com.atguigu.ssyx.model.product.Category;
import com.atguigu.ssyx.product.service.CategoryService;
import com.atguigu.ssyx.vo.product.CategoryQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 商品三级分类 前端控制器
 * </p>
 *
 * @author liuxu
 * @since 2024-03-05
 */
@Api(value = "Category管理", tags = "商品分类管理")
@RestController
@RequestMapping(value="/admin/product/category")
 
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "获取商品分类分页列表")
    @GetMapping("{page}/{limit}")
    public Result index(@PathVariable Long page,
                       @PathVariable Long limit,
                       CategoryQueryVo categoryQueryVo) {
        Page<Category> pageParam = new Page<>(page, limit);
        IPage<Category> pageModel = categoryService.pageCategory(pageParam, categoryQueryVo);
        return Result.ok(pageModel);
    }

    @ApiOperation(value = "获取商品分类信息")
    @GetMapping("get/{id}")
    public Result getinfo(@PathVariable Long id) {
        return Result.ok(categoryService.getById(id));
    }

    @ApiOperation(value = "新增商品分类")
    @PostMapping("save")
    public Result saveinfo(@RequestBody Category category) {
        boolean iss_success = categoryService.save(category);
        if (iss_success) {
            return Result.ok(null);
        }
        return Result.fail(null);
    }

    @ApiOperation(value = "修改商品分类")
    @PutMapping("update")
    public Result updateinfo(@RequestBody Category category) {
        boolean iss_success = categoryService.updateById(category);
        if (iss_success) {
            return Result.ok(null);
        }
        return Result.fail(null);
    }

    @ApiOperation(value = "删除商品分类")
    @DeleteMapping("remove/{id}")
    public Result removeinfo(@PathVariable Long id) {
        boolean iss_success = categoryService.removeById(id);
        if (iss_success) {
            return Result.ok(null);
        }
        return Result.fail(null);
    }

    @ApiOperation(value = "根据id列表删除商品分类")
    @DeleteMapping("batchRemove")
    public Result batchRemoveinfo(@RequestBody List<Long> idList) {
        boolean iss_success = categoryService.removeByIds(idList);
        if (iss_success) {
            return Result.ok(null);
        }
        return Result.fail(null);
    }

    @ApiOperation(value = "获取全部商品分类")
    @GetMapping("findAllList")
    public Result findAllList() {
        List<Category> allCategoryList = categoryService.list();
        return Result.ok(allCategoryList);
    }
}

