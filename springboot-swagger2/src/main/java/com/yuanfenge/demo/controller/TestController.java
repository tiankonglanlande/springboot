package com.yuanfenge.demo.controller;

import com.yuanfenge.demo.bean.User;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @auth 猿份哥
 * @description
 * @createTime 2020 - 8 - 2 14:436
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class TestController {

    @ApiOperation(value="根据id查询用户信息",notes = "id必需是数字")
    @ApiResponses({@ApiResponse(code=400,message="id不能为空")})
    @GetMapping("/info")
    public ResponseEntity info(@ApiParam(value = "ID编号",required = true) @RequestParam("id") Integer id){
        User user=User.builder()
                .id(id)
                .name("猿份哥")
                .age(18)
                .build();

        return  ResponseEntity.ok(user );
    }
    @ApiOperation("新增用户信息")
    @PostMapping("/add")
    public ResponseEntity user(User user){
        return ResponseEntity.ok(user);
    }

    @ApiOperation("根据id删除用户信息")
    @DeleteMapping("/del")
    public ResponseEntity<String> user(@ApiParam("ID编号") @RequestParam("id") Integer id){
        String message="删除id为"+id+"的用户";
        return ResponseEntity.ok(message);
    }

    @ApiOperation("根据id更新用户信息")
    @DeleteMapping("/update")
    public Object update(@ApiParam("ID编号") @RequestParam("id") Integer id){
        String message="删除id为"+id+"的用户";
        return ResponseEntity.ok(message);
    }

}
