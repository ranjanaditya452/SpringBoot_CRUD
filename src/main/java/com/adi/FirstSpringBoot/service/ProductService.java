package com.adi.FirstSpringBoot.service;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.adi.FirstSpringBoot.exceptions.ProductNotFoundException;
import com.adi.FirstSpringBoot.model.Product;
import com.adi.FirstSpringBoot.repository.ProductRepository;

@Service
public class ProductService {
			
	@Autowired
	ProductRepository productRepository;
	
	private static final Logger logger = Logger.getLogger(ProductService.class);
 	public Product addProduct(Product product){ 
		logger.info("Service method for adding product API Request for Product " + product.getProductId());
		return productRepository.save(product);
	}

	public Product getProduct(int id) { 
		Optional<Product> prod= productRepository.findById(id);
	
		if (prod.isPresent())
		{
			return prod.get();
		}
		else throw new ProductNotFoundException("The Product doesn't exist"); 
	}
	
	
	public List<Product> addMultipleProducts(List<Product> products){
		return productRepository.saveAll(products);
	}
	public List<Product> getProducts() {
		return productRepository.findAll();
	}

	public Product updateProduct(int pId, Product newValue) {
	 	Product prodFromDB=getProduct(pId);
	 	prodFromDB.setProductCategory(newValue.getProductCategory());
	 	prodFromDB.setProductDescription(newValue.getProductDescription());
	 	prodFromDB.setProductPrice(newValue.getProductPrice());
	 	prodFromDB.setProductTitle(newValue.getProductTitle());
	 	return productRepository.save(prodFromDB);
	}

	public String deleteProduct(int pId) {
		productRepository.deleteById(pId);
		return "Product Deleted by ID:"+ pId;
	}

	public List<String> getCategories() {
		return productRepository.getCategories();
	}

	public List<Product> getProductsByCategoryAndTitle(String category, String title) {
	
			// return productRepository.getProductsByCategoryAndTitle(category, title);
		return productRepository.findByProductCategoryAndProductTitle(category, title);
	}
	
	public List<Product> getProductsByTitle(String title)
	{
		return productRepository.findByProductTitleContaining(title);
	}
	
	public List<Product> searchProducts(String str) {
		return productRepository.findByProductDescriptionContaining(str);
	}

	public Page<Product> getProductByPages(int pageNumber, int pageSize) {
		return productRepository.findAll(PageRequest.of(pageNumber, pageSize));
	}

	public Page<Product> getProductByPagesSorted(int pageNumber, int pageSize, String field) {
		return productRepository.findAll(PageRequest.of(pageNumber, pageSize).withSort(Sort.Direction.ASC,field));		
	}
	
	public List<Product> getProductsByCategory(String str)
	{
		return productRepository.findByProductCategory(str);
	}
}




