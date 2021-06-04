package com.yuanfenge.vlid.bean;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lqm
 * @description
 * @createTime 2019 -  - 07 11:54
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bean {

    @NotNull(message = "不能为空")
    private String name;

    @Min(value = 1, message = "大于0")
    @NotNull(message = "不能为空")
    private Integer age;
}
