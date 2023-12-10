package com.zeeshan.productService.exception;

/**
 * @author zeeshan
 */


public class InvalidCategoryException extends Exception{

    public InvalidCategoryException(String message){
        super(message);
    }
}
