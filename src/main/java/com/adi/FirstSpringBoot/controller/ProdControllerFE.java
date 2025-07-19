package com.adi.FirstSpringBoot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.adi.FirstSpringBoot.model.Product;
import com.adi.FirstSpringBoot.service.ProductService;

@Controller
public class ProdControllerFE {

	@Autowired
	ProductService productService;
	 
	
	@RequestMapping("/getAllProductsFE")
	public String getAllProductsFE(Model model) {
	List<Product> prods= productService.getProducts();
	model.addAttribute("products",prods);
	return "AllProducts";
	}
	
	@RequestMapping("/addProductFE")
	public String addProdctFe(Model model)
	{
	 Product product = new Product();	
	 model.addAttribute("prod",product);
	 return "AddProductForm";
	}
	
	@PostMapping("/addProductReqBodyFE")
	public void addProductReqBodyFE(@RequestBody Product product)
	{
		productService.addProduct(product);
	}
}
	
	


