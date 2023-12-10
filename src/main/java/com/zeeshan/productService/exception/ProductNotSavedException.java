package com.zeeshan.productService.exception;

/**
 * @author zeeshan
 */


public class ProductNotSavedException  extends  Exception{
    public  ProductNotSavedException(String message){
        super(message);
    }
}
