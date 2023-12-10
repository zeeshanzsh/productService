package com.zeeshan.productService.exception;

/**
 * @author zeeshan
 */


public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
