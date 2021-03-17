package com.borate.pravin.pim.repository;

import com.borate.pravin.pim.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Pravin Borate
 * 11/03/21
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product>, QueryByExampleExecutor<Product> {

    @Query("select p from Product p where p.deleted = false")
    public List<Product> findAll();

    @Query("select product from Product product where product.id =:id and product.deleted = false")
    public Product getOne(Long id);
}
