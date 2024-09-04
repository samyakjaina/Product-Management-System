package com.example.product.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.example.product.domain.ProductEntity;
import com.example.product.exception.CustomException;

/**
 * Service Interface for Product
 * 
 * @author SAMYAK JAIN
 *
 */
@Service
public interface ProductService {
	/**
	 * It will delete the Product by Id
	 * 
	 * @param id
	 * @return
	 * @throws CustomException
	 */
	String deleteById(Long id) throws CustomException;

	/**
	 * It will fetch all the Product Record
	 * 
	 * @return
	 */
	List<ProductEntity> getAllRecords();

	/**
	 * It will find the Product by Id
	 * 
	 * @param id
	 * @param request
	 * @return
	 * @throws CustomException
	 */
	ProductEntity findById(Long id) throws CustomException;

	/**
	 * It will create product
	 * 
	 * @param product
	 * @return
	 */
	ProductEntity createProduct(@Valid ProductEntity product);

	/**
	 * It will update the product by id
	 * 
	 * @param id
	 * @param productDetails
	 * @return
	 * @throws CustomException
	 */
	ProductEntity updateProduct(Long id, @Valid ProductEntity productDetails) throws CustomException;

}
