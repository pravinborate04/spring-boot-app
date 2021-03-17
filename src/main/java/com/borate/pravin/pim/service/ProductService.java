package com.borate.pravin.pim.service;

import com.borate.pravin.pim.entities.Product;
import com.borate.pravin.pim.helper.Constants;
import com.borate.pravin.pim.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Pravin Borate
 * 11/03/21
 */
@Service
public class ProductService implements Constants {

    @Autowired
    ProductRepository productRepository;

    @PersistenceContext
    EntityManager em;


    /**
     * Get all product filter by brand and active
     *
     * @param brand
     * @param isActive
     * @return List<Product>
     */
    public List<Product> getAll(String brand, Boolean isActive) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> product = cq.from(Product.class);
        List<Predicate> predicates = new ArrayList<>();
        cq.where(predicates.toArray(new Predicate[0]));
        if (brand != null) {
            predicates.add(cb.equal(product.get("brand"), brand));
        }
        if (isActive != null) {
            predicates.add(cb.equal(product.get("isActive"), isActive));
        }
        predicates.add(cb.equal(product.get("deleted"), false));
        cq.where(predicates.toArray(new Predicate[0]));
        return em.createQuery(cq.select(product)).getResultList();
    }

    /**
     * Get Product by Id
     *
     * @param id
     * @return Product
     */
    public Product getOne(Long id) {
        return productRepository.getOne(id);
    }

    /**
     * Create Product
     *
     * @param product
     * @return CreatedProduct
     */
    public Product create(Product product) {
        return productRepository.save(product);
    }

    /**
     * Update Product by Id
     *
     * @param id
     * @param product updatedProduct
     * @return Updated Product
     */
    public Product update(Long id, Product product) {
        if (id != null) {
            Product oldProduct = getOne(id);
            if (oldProduct != null) {
                oldProduct.setAmount(product.getAmount());
                oldProduct.setBrand(product.getBrand());
                oldProduct.setImages(product.getImages());
                oldProduct.setName(product.getName());
                oldProduct.setQuantity(product.getQuantity());
                oldProduct.setActive(product.isActive());
                productRepository.save(product);
            }
        }
        return getOne(id);
    }

    /**
     * Soft Delete Product by id
     *
     * @param id
     */
    public void delete(Long id) {
        if (id != null) {
            Product product = getOne(id);
            if (product != null) {
                product.setDeleted(true);
                productRepository.save(product);
            }
        }
    }
}
