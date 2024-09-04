package com.example.product.service.impl;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.product.constant.ResponseMessage;
import com.example.product.domain.ProductEntity;
import com.example.product.exception.CustomException;
import com.example.product.repository.ProductRepository;
import com.example.product.service.ProductService;

/**
 * Service Implementaion Class for Product
 * 
 * @author SAMYAK JAIN
 *
 */
@Component
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	static final Logger LOGGER = LogManager.getLogger(ProductServiceImpl.class);

	@Override
	public String deleteById(Long id) throws CustomException {
		LOGGER.info("Deleting Excel File of id " + id);
		if (!productRepository.existsById(id)) {
			throw new CustomException(ResponseMessage.Delete);
		}
		productRepository.deleteById(id);
		LOGGER.info("Excel File deleted successfully ");
		return "Deleted SuccessFully";
	}

	@Override
	public List<ProductEntity> getAllRecords() {
		LOGGER.info("Fetching all the product records");
		return productRepository.findAll();
	}

	@Override
	public ProductEntity findById(Long id) throws CustomException {
		LOGGER.info("Fetching Excel File Details of id " + id);
		ProductEntity excelFile = productRepository.findById(id)
				.orElseThrow(() -> new CustomException(ResponseMessage.Null));
		return excelFile;
	}

	@Override
	public ProductEntity createProduct(@Valid ProductEntity product) {
		LOGGER.info("Creating Product");
		return productRepository.save(product);
	}

	@Override
	public ProductEntity updateProduct(Long id, @Valid ProductEntity productDetails) throws CustomException {
		LOGGER.info("Updating Product based on id " + id);
		return productRepository.findById(id).map(product -> {
			product.setName(productDetails.getName());
			product.setDescription(productDetails.getDescription());
			product.setPrice(productDetails.getPrice());
			product.setQuantity(productDetails.getQuantity());
			return productRepository.save(product);
		}).orElseThrow(() -> new CustomException(ResponseMessage.Update));
	}

}
