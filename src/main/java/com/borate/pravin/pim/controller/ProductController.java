package com.borate.pravin.pim.controller;

import com.borate.pravin.pim.entities.Product;
import com.borate.pravin.pim.helper.Constants;
import com.borate.pravin.pim.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.util.List;

/**
 * @author Pravin Borate
 * 11/03/21
 */
@RestController
@RequestMapping(Constants.SEPARATOR + Constants.API_BASE + Constants.SEPARATOR + Constants.PRODUCT)
public class ProductController implements Constants {

    @Autowired
    ProductService productService;

    @GetMapping
    public List<Product> getAllProducts(@QueryParam(BRAND) String brand, @QueryParam(ACTIVE) Boolean active) {
        return productService.getAll(brand, active);
    }

    @GetMapping(OPEN_BRACKET + ID + CLOSE_BRACKET)
    public Product getProductById(@PathVariable(ID) Long id) {
        return productService.getOne(id);
    }

    @PutMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.create(product);
    }

    @PostMapping(OPEN_BRACKET + ID + CLOSE_BRACKET)
    public void deleteProduct(@PathVariable(ID) Long id, @RequestBody Product product) {
        productService.update(id, product);
    }


    @DeleteMapping(OPEN_BRACKET + ID + CLOSE_BRACKET)
    public void deleteProduct(@PathVariable(ID) Long id) {
        productService.delete(id);
    }
}
