package com.adi.FirstSpringBoot.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adi.FirstSpringBoot.exceptions.ProductNotFoundException;
import com.adi.FirstSpringBoot.model.Product;
import com.adi.FirstSpringBoot.response.ErrorResponse;
import com.adi.FirstSpringBoot.service.ProductService;
import com.adi.FirstSpringBoot.util.APIError;

@RestController
public class ProductController {

	@Autowired
	ProductService productService;
	
	
	@RequestMapping("/rctest")
	public String test(Model model)
	{  
		Product product = new Product();
		product.setProductId(1); 
		product.setProductCategory("Electric");
		product.setProductDescription("Dell Hathway 15-Laptop");
		product.setProductPrice(3000);
		product.setProductTitle("Laptop");
		model.addAttribute("product1",product);
		return "Test";
	}
	
	@GetMapping("/addProduct")
	public String addProduct()
	{  
		Product product = new Product();
		product.setProductCategory("Hardware");
		product.setProductDescription("Alektra 10bit Drill");
		product.setProductPrice(900);
		product.setProductTitle("Drill Machine");
		productService.addProduct(product);
		return "Product added successfully";
	}
	@GetMapping("/addProduct1")
	public String addProductBuilder()
	{  
		Product product = Product.builder()
				.productCategory("Furniture")
				.productPrice(4000)
				.productTitle("Chair").build();
		productService.addProduct(product);
		return "Product added successfully";
	}
	
	

	@PostMapping("/addProductbyParam")
	public String addProductbyParam(@RequestParam("a") String title,
									@RequestParam("b") String description,
									@RequestParam("c") int price,
									@RequestParam("d") String category)
	{  
		Product product = Product.builder()
				.productCategory(category)
				.productPrice(price)
				.productTitle(title).productDescription(description).build();
		productService.addProduct(product);
		return "Product added successfully";
	}
	@PostMapping("/addProductbyPath/{a}/{b}/{c}/{d}")
	public String addProductbyPath(@PathVariable("a") String title,
								 	@PathVariable("b") String description,
									@PathVariable("c") int price,
									@PathVariable("d") String category)
	{  
		Product product = Product.builder()
				.productCategory(category)
				.productPrice(price)
				.productTitle(title).productDescription(description).build();
		productService.addProduct(product);
		return "Product added successfully";
	}
	@PostMapping("/addProductbyPath1/{title}/{description}/{price}/{category}")
	public Product addProductbyPath1(@PathVariable String title,
								 	@PathVariable String description,
									@PathVariable int price,
									@PathVariable String category)
	{  
		Product product = Product.builder()
				.productCategory(category)
				.productPrice(price)
				.productTitle(title).productDescription(description).build();
		return productService.addProduct(product);
		
	}
	@PostMapping("/addProductbyRequestBody")
	public ResponseEntity<?> addProductbyRequestBody(@RequestBody @Valid Product product,BindingResult bindingResult)
	{  
		if(bindingResult.hasErrors())
		{
			List<APIError> errors = new ArrayList<APIError>();
			for(FieldError error: bindingResult.getFieldErrors())
			{
				APIError error2 = new APIError(error.getDefaultMessage(), error.getField(), error.getRejectedValue());
				errors.add(error2);
			}
			return new ResponseEntity<List<APIError>>(errors,HttpStatus.BAD_REQUEST);
		}
		Product prod1 = productService.addProduct(product);
		return new ResponseEntity<Product>(prod1,HttpStatus.CREATED);
		
	}
	
	@PostMapping("/addMultipleProducts") 
	public ResponseEntity<List<Product>> addMultipleProducts(@RequestBody List<Product> products){
		List<Product> list = productService.addMultipleProducts(products);
		return new ResponseEntity<List<Product>>(list,HttpStatus.CREATED);
	}
		
	@GetMapping("/getProducts")
	public ResponseEntity<List<Product>> getProducts()
	{  
		return new ResponseEntity<List<Product>>( productService.getProducts(),HttpStatus.OK);	
	}
	
	
	@PostMapping("/addMultipleProducts200")
	public List<Product> addMultProducts(@RequestBody List<Product> products)
	{
		return productService.addMultipleProducts(products);
	}
	
	@GetMapping("/GetProductbyID")
	public ResponseEntity<?> getProdbyID(@RequestBody int i){
		try {return new ResponseEntity<Product>(productService.getProduct(i),HttpStatus.OK);
			
		} catch (ProductNotFoundException e) {
			
			ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value(), false);
		 return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.BAD_REQUEST);
		}
	
	}
	
	@PutMapping("/updateProduct/{pId}")
	public ResponseEntity<Product> updateProduct(@PathVariable int pId,@RequestBody Product newValue)
	{
			return new ResponseEntity<Product>(productService.updateProduct(pId,newValue),HttpStatus.OK) ;
	}
	
	@DeleteMapping("/deleteProduct/{pId}")
	public ResponseEntity<String> deleteProduct(@PathVariable int pId)
	{
		return new ResponseEntity<String>(productService.deleteProduct(pId),HttpStatus.OK);
	}
	
	@GetMapping("/getCategories")
	public ResponseEntity<List<String>> getCategories(){
		return new ResponseEntity<List<String>>(productService.getCategories(),HttpStatus.OK);
	}
	
	@GetMapping("/getProductsByCategoryAndTitle/{category}/{title}")
	public ResponseEntity<List<Product>> getProductsByCategoryAndTitle(
																		@PathVariable String category,
																		@PathVariable String title){
		return new ResponseEntity<List<Product>>(productService.getProductsByCategoryAndTitle(category,title),HttpStatus.OK);	}
	
	@GetMapping("/searchProducts/{str}")
	public ResponseEntity<List<Product>> searchProducts(@PathVariable String str){
		return new ResponseEntity<List<Product>>(productService.searchProducts(str),HttpStatus.OK);
	}
	
	@GetMapping("/getProductByPages/{pageNumber}/{pageSize}")
	public Page<Product> getProductByPages(@PathVariable int pageNumber, @PathVariable int pageSize ){
		return productService.getProductByPages(pageNumber,pageSize);
	}
}

