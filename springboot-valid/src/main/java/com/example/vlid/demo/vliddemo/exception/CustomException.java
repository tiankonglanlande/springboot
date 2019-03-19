package com.example.vlid.demo.vliddemo.exception;

import lombok.Data;


@Data
public class CustomException extends Exception{
      public CustomException(String msg){
        super(msg);
     }

}
