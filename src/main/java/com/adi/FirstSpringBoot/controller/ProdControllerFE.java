package com.adi.FirstSpringBoot.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

import com.adi.FirstSpringBoot.exceptions.ProductNotFoundException;
import com.adi.FirstSpringBoot.model.Product;
import com.adi.FirstSpringBoot.response.ErrorResponse;
import com.adi.FirstSpringBoot.service.ProductService;
import com.adi.FirstSpringBoot.util.APIError;

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
}
	
	


