package com.zeeshan.productService.redis.repository;

import com.zeeshan.productService.redis.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zeeshan
 */

@Repository
@Slf4j
public class ProductDAO {

    private static final String HASH_KEY = "Product";
    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate template;

    public Product save(Product product) {
        template.opsForHash().put(HASH_KEY, product.getId(), product);
        return product;
    }


    public List<Product> findAll() {
        log.debug("Getting all the product from DB");
        return template.opsForHash().values(HASH_KEY);
    }

    public Product findByProductId(int id) {
        log.info("Finding from DB");
        return (Product) template.opsForHash().get(HASH_KEY, id);
    }

    public String deleteProduct(int Id) {
        template.opsForHash().delete(HASH_KEY, Id);
        return "Product Removed !!";
    }
}
