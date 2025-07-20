package com.adi.FirstSpringBoot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String addProductReqBodyFE(@ModelAttribute Product product)
	{
		productService.addProduct(product);
		return "redirect:/getAllProductsFE";
	}
	@RequestMapping("/updateProductFE/{pID}")
	public String updateProductFE( @PathVariable int pID,Model model)
	{ 
	Product product= productService.getProduct(pID);
	model.addAttribute("prod",product) ;
	return "UpdateProductForm";
	}
	
	@RequestMapping("/deleteProductFE/{pID}")
	public String deleteProductFe(@PathVariable int pID) {
	productService.deleteProduct(pID);
	return "redirect:/getAllProductsFE";
	}
	
	@RequestMapping("/updateProductFormFE/{pID}")
	public String updateProductFormFE(@ModelAttribute Product product,@PathVariable int pID) {	
		productService.updateProduct(pID, product);
		return "redirect:/getAllProductsFE";
	}
	
	@RequestMapping("/productsFE")
	public String productsFE(@RequestParam ("title") String title ,Model model)
	{
		List<Product> list = productService.getProductsByTitle(title);
		model.addAttribute("products",list);
		return "AllProducts";	
	}
}
	
	


