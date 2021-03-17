package com.borate.pravin.pim.controller;

import com.borate.pravin.pim.entities.Product;
import com.borate.pravin.pim.helper.Constants;
import com.borate.pravin.pim.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.util.List;

/**
 * CRUD API for Product Entity
 *
 * @author Pravin Borate
 * 11/03/21
 */
@RestController
@RequestMapping(Constants.SEPARATOR + Constants.API_BASE + Constants.SEPARATOR + Constants.PRODUCT)
public class ProductController implements Constants {

    @Autowired
    ProductService productService;

    /**
     * The Product list API
     * GET /api/product
     *
     * @return List<Product>
     * @QueryParam brand [filter based on brand name]
     * @QueryParam active [filter based on product active]
     */
    @GetMapping
    public List<Product> getAllProducts(@QueryParam(BRAND) String brand, @QueryParam(ACTIVE) Boolean active) {
        return productService.getAll(brand, active);
    }

    /**
     * Get Product by Id
     * GET /api/product/{id}
     *
     * @return Product
     * @PathParam id Product id
     */
    @GetMapping(OPEN_BRACKET + ID + CLOSE_BRACKET)
    public Product getProductById(@PathVariable(ID) Long id) {
        return productService.getOne(id);
    }

    /**
     * API to create product
     * PUT /api/product
     *
     * @param product
     * @return Product
     */
    @PutMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.create(product);
    }

    /**
     * Update Product by Id
     * POST api/product/{id}
     *
     * @param id
     * @param product
     */
    @PostMapping(OPEN_BRACKET + ID + CLOSE_BRACKET)
    public void postProduct(@PathVariable(ID) Long id, @RequestBody Product product) {
        productService.update(id, product);
    }

    /**
     * DELETE product by id
     * DELETE api/product/{id}
     *
     * @param id
     */
    @DeleteMapping(OPEN_BRACKET + ID + CLOSE_BRACKET)
    public void deleteProduct(@PathVariable(ID) Long id) {
        productService.delete(id);
    }
}
