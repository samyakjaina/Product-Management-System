package com.example.product.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.product.domain.ProductEntity;
import com.example.product.exception.CustomException;
import com.example.product.service.ProductService;

/**
 * Controller class for Products
 * 
 * @author SAMYAK JAIN
 *
 */
@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;

	/**
	 * Method to fetch all the Product
	 * 
	 * @return
	 */
	@GetMapping("/get/all")
	@PreAuthorize("hasAuthority('USER')  or hasAuthority('ADMIN') ")
	public ResponseEntity<List<ProductEntity>> getAllRecords() {
		return ResponseEntity.ok(productService.getAllRecords());
	}

	/**
	 * Method to delete the Product by Id
	 * 
	 * @param id
	 * @return
	 * @throws CustomException
	 */
	@DeleteMapping("/delete-by-id/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> deleteById(@PathVariable Long id) throws CustomException {
		return ResponseEntity.ok(productService.deleteById(id));
	}

	/**
	 * Method to find the Product by Id
	 * 
	 * @param id
	 * @param request
	 * @return
	 * @throws CustomException
	 */
	@GetMapping("/find-by-id/{id}")
	@PreAuthorize("hasAuthority('USER')  or hasAuthority('ADMIN') ")
	public ResponseEntity<ProductEntity> findById(@PathVariable Long id) throws CustomException {
		ProductEntity product = productService.findById(id);
		return ResponseEntity.ok(product);
	}

	/**
	 * Method to insert Product
	 * 
	 * @param product
	 * @return
	 */
	@PostMapping("/insert")
	@PreAuthorize("hasAuthority('USER')  or hasAuthority('ADMIN') ")
	public ResponseEntity<ProductEntity> createProduct(@Valid @RequestBody ProductEntity product) {
		ProductEntity savedProduct = productService.createProduct(product);
		return ResponseEntity.ok(savedProduct);
	}

	/**
	 * Method to update Product by id
	 * 
	 * @param id
	 * @param productDetails
	 * @return
	 * @throws CustomException
	 */
	@PutMapping("/udpate-by-id/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<ProductEntity> updateProduct(@PathVariable Long id,
			@Valid @RequestBody ProductEntity productDetails) throws CustomException {
		ProductEntity updatedProduct = productService.updateProduct(id, productDetails);
		return ResponseEntity.ok(updatedProduct);
	}
}
