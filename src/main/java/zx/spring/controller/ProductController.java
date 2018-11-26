package zx.spring.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import zx.spring.model.ProductModel;
import zx.spring.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService service;

	@RequestMapping("/product")
	public String product(Model model) {
		List<ProductModel> _product = service.findAll();
		List<Map> _category = service.getCategory();
		model.addAttribute("navIndex", 3);
		model.addAttribute("item_product", _product);
		model.addAttribute("item_category", _category);
		return "/product";
	}

	@RequestMapping("/other")
	public String other(Model model) {
		List<Map> _category = service.getCategory();
		model.addAttribute("navIndex", 3);
		model.addAttribute("item_category", _category);
		return "/other";
	}
	
	@RequestMapping(value = "/showproduct/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ProductModel showProduct(@PathVariable(required = true) String id) {
		ProductModel _product = new ProductModel();
		_product = service.findById(id);
		return _product;
	}
}
