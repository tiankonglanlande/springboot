package com.yuanfenge.vlid.exception;

import lombok.Data;

/**
 * @author lqm
 * @description
 * @createTime 2019 - 3 - 07 15:39
 */
@Data
public class ParamException extends Exception {
    public ParamException(String msg) {
        super(msg);
    }

}
