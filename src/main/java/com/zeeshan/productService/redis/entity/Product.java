package com.zeeshan.productService.redis.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

/**
 * @author zeeshan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("Product")
@ApiModel(description = "Details about the Product")
public class Product implements Serializable {
    @ApiModelProperty(notes = "The unique identifier of the product")
    @Id
    private int Id;
    @ApiModelProperty(notes = "The name of the product")
    private String name;
    @ApiModelProperty(notes = "The quantity of the product available")
    private int qty;
    @ApiModelProperty(notes = "The category of the product")
    private Category category;
    @ApiModelProperty(notes = "The price of the product")
    private long price;

}
