package com.adi.FirstSpringBoot.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.adi.FirstSpringBoot.model.Product;
import com.adi.FirstSpringBoot.service.ProductService;

@Controller
public class ProdControllerFE {

	@Autowired
	ProductService productService;
	 
	
	@RequestMapping("/")
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
		return "redirect:/";
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
	return "redirect:/";
	}
	
	@RequestMapping("/updateProductFormFE/{pID}")
	public String updateProductFormFE(@ModelAttribute Product product,@PathVariable int pID) {	
		productService.updateProduct(pID, product);
		return "redirect:/";
	}
	
	@RequestMapping("/searchProductsFE")
	public String SearchProductsFE(@RequestParam ("title") String title ,Model model)
	{
		List<Product> list = productService.getProductsByTitle(title);
		model.addAttribute("products",list);
		return "AllProducts";	
	}
	@RequestMapping("/filterProductsFE")
	public String filterProductsFE(@RequestParam ("category") String category,Model model)
	{
		List<Product> list = productService.getProductsByCategory(category);
		model.addAttribute("products",list);
		return "AllProducts";
	}
	@RequestMapping("/403")
	public String error403(Model model,Principal user)
	{
		if(user!=null)
		{
			model.addAttribute("msg", user.getName()+", You do not have permission to view this page!");
		} else {
			model.addAttribute("msg", "You do not have permission to view this page!");
		}
		return "403";
	}
}
	
	


