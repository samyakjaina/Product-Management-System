package com.example.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.product.domain.ProductEntity;

/**
 * Repository for Product Entity
 * 
 * @author SAMYAK JAIN
 *
 */
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}
