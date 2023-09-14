package com.yuanfenge.knife.controller;

import com.yuanfenge.commons.result.Result;
import com.yuanfenge.commons.result.ResultEnum;
import com.yuanfenge.commons.result.Results;
import com.yuanfenge.knife.bean.User;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @auth 猿份哥
 * @description
 * @createTime 2020 - 8 - 2 14:436
 */
@Api(tags = {"用户模块"}, description = "用户中心")
@Slf4j
@RestController
@RequestMapping("/user")
public class TestController {

    @ApiOperation(value = "根据id查询用户信息", notes = "id必需是数字")
    @ApiResponses({@ApiResponse(code = 400, message = "id不能为空")})
    @GetMapping("/info")
    public Result info(@ApiParam(value = "ID编号", required = true) @RequestParam("id") Integer id) {
        User user = User.builder()
                .id(id)
                .name("猿份哥")
                .age(18)
                .build();

        return Results.success(user);
    }

    @ApiOperation("新增用户信息")
    @PostMapping("/add")
    public Result user(User user) {
        return Results.success(user);
    }

    @ApiOperation("根据id删除用户信息")
    @DeleteMapping("/del")
    public Result user(@ApiParam("ID编号") @RequestParam("id") Integer id) {
        String message = "删除id为" + id + "的用户";
        log.info(message);
        return  Results.success(ResultEnum.SUCCESS);
    }

    @ApiOperation("根据id更新用户信息")
    @PostMapping("/update")
    public Result update(User user) {
        if (null == user || null == user.getId()) {
            return Results.fail(ResultEnum.PARAMS_EXCEPTION);
        }
        String message = "更新id为" + user.getId() + "的用户";
        log.info(message);
        return Results.success(user);
    }

}
