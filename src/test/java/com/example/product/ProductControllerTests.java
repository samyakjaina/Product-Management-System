package com.example.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.product.constant.ResponseMessage;
import com.example.product.controller.ProductController;
import com.example.product.domain.ProductEntity;
import com.example.product.exception.CustomException;
import com.example.product.repository.ProductRepository;
import com.example.product.service.ProductService;

/**
 * @author SAMYAK JAIN
 *
 */
public class ProductControllerTests {

	@InjectMocks
	private ProductController productController;

	@Mock
	private ProductService productService;

	@Mock
	private ProductRepository productRepository;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testCreateProduct() {
		ProductEntity product = new ProductEntity("Laptop", "High-end gaming laptop", 1500.0, 10);
		when(productService.createProduct(any(ProductEntity.class))).thenReturn(product);

		ResponseEntity<ProductEntity> response = productController.createProduct(product);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(product, response.getBody());
	}

	@Test
	public void testGetAllRecords() {
		// Arrange
		ProductEntity product1 = new ProductEntity("Laptop", "High-end gaming laptop", 1500.0, 10);
		ProductEntity product2 = new ProductEntity("Phone", "Smartphone", 800.0, 20);
		List<ProductEntity> products = Arrays.asList(product1, product2);
		when(productService.getAllRecords()).thenReturn(products);

		// Act
		ResponseEntity<List<ProductEntity>> response = productController.getAllRecords();

		// Assert
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(products, response.getBody());
	}

	@Test
	public void testUpdateProduct() throws CustomException {
		ProductEntity updatedProduct = new ProductEntity("Updated Laptop", "Updated description", 1400.0, 8);
		when(productService.updateProduct(anyLong(), any(ProductEntity.class))).thenReturn(updatedProduct);

		ResponseEntity<ProductEntity> response = productController.updateProduct(1L, updatedProduct);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(updatedProduct, response.getBody());
	}

	@Test
	public void testUpdateProductWithCustomException() throws CustomException {
		ProductEntity updatedProduct = new ProductEntity("Updated Laptop", "Updated description", 1400.0, 8);
		when(productService.updateProduct(anyLong(), any(ProductEntity.class)))
				.thenThrow(new CustomException(ResponseMessage.Update));

		CustomException exception = org.junit.jupiter.api.Assertions.assertThrows(CustomException.class, () -> {
			productController.updateProduct(1L, updatedProduct);
		});
		assertEquals("No Data Found for udpate ", exception.getMessage());
	}

	@Test
	public void testFindProductById() throws CustomException {
		ProductEntity product = new ProductEntity("Laptop", "High-end gaming laptop", 1500.0, 10);
		when(productService.findById(anyLong())).thenReturn(product);

		ResponseEntity<ProductEntity> response = productController.findById(1L);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(product, response.getBody());
	}

	@Test
	public void testFindProductByIdWithCustomException() throws CustomException {
		when(productService.findById(anyLong())).thenThrow(new CustomException(ResponseMessage.Null));

		 CustomException exception = org.junit.jupiter.api.Assertions.assertThrows(CustomException.class, () -> {
	            productController.findById(1L);
	        });
		 assertEquals("No Data Found", exception.getMessage());
	}

	@Test
	public void testDeleteProductByIdWithCustomException() throws CustomException {
		when(productService.deleteById(anyLong())).thenThrow(new CustomException(ResponseMessage.Delete));

		CustomException exception = org.junit.jupiter.api.Assertions.assertThrows(CustomException.class, () -> {
           productController.deleteById(1L);
		});
		assertEquals("No Data Found for delete", exception.getMessage());
	 }

	@Test
	public void testDeleteProductById() throws CustomException {
		when(productService.deleteById(anyLong())).thenReturn("Deleted SuccessFully");

		ResponseEntity<String> response = productController.deleteById(1L);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Deleted SuccessFully", response.getBody());
	}
}
