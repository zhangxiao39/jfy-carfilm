package zx.spring.controller;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import zx.spring.model.ProductModel;
import zx.spring.model.QainfoModel;
import zx.spring.model.UserModel;
import zx.spring.service.ProductService;
import zx.spring.service.QainfoService;
import zx.spring.service.UserService;

@Controller
public class AdminController {

	@Autowired
	private UserService service;
	@Autowired
	private ProductService p_service;
	@Autowired
	private QainfoService qa_service;

	@RequestMapping(value = "/admin/login", method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("navIndex", 1);
		return "/admin/login";
	}

	@RequestMapping(value = "/admin/login", method = RequestMethod.POST)
	public String login(Model model, UserModel _user, HttpSession session) {
		UserModel user = service.login(_user.getUser_id(), _user.getPassword());
		if (user != null) {
			session.setAttribute("u_id", user.getUser_id());
			return "redirect:/admin/index";
		} else {
			model.addAttribute("erorrMsg", "用户名或者密码不正确");
			return "/admin/login";
		}
	}

	@RequestMapping(value = "/admin/index", method = RequestMethod.GET)
	public String home(Model model) {
		List<ProductModel> _product = p_service.findAll();
		model.addAttribute("item_product", _product);
		model.addAttribute("nav_active1", 1);
		return "/admin/index";
	}

	@GetMapping("/admin/loginout")
	public String loginout(HttpSession session) {
		session.setAttribute("u_login", null);
		return "redirect:/admin/login";
	}

	@RequestMapping(value = "/admin/quality", method = RequestMethod.GET)
	public String quality(Model model) {
		List<QainfoModel> _qa = qa_service.findAll();
		model.addAttribute("item_qa", _qa);
		model.addAttribute("nav_active1", 2);
		return "/admin/quality";
	}

	@RequestMapping(value = "/admin/quality/{id}", method = RequestMethod.GET)
	@ResponseBody
	public QainfoModel showQa(@PathVariable(required = true) String id) {
		QainfoModel _qa = new QainfoModel();
		_qa = qa_service.findById(id);
		return _qa;
	}

	@RequestMapping(value = "/admin/quality", method = RequestMethod.PUT)
	@ResponseBody
	public String updateQa(String code, String addtime, String year, String id) throws ParseException {
		QainfoModel _qa = new QainfoModel();
		_qa.setId(Integer.parseInt(id));
		_qa.setCode(code);
		_qa.setYear(Integer.parseInt(year));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		_qa.setAddtime(sdf.parse(addtime));
		if (_qa.getId() > 0) {
			int ok = qa_service.updateQaById(_qa);
			if (ok > 0) {
				return "ok";
			} else {
				return "fail";
			}
		} else {
			return "fail";
		}
	}
	
	@RequestMapping(value = "/admin/quality", method = RequestMethod.POST)
	@ResponseBody
	public String addQa(String code, String addtime, String year, String id) throws ParseException {
		QainfoModel _qa = new QainfoModel();
		_qa.setId(Integer.parseInt(id));
		_qa.setCode(code);
		_qa.setYear(Integer.parseInt(year));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		_qa.setAddtime(sdf.parse(addtime));
		if (_qa.getId() == 0) {
			int ok = qa_service.insert(_qa);
			if (ok > 0) {
				return "ok";
			} else {
				return "fail";
			}
		} else {
			return "fail";
		}
	}
	
	@RequestMapping(value = "/admin/quality/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteQa(@PathVariable(required = true) int id) {
		int ok = qa_service.deleteById(id);
		if (ok > 0) {
			return "ok";
		} else {
			return "fail";
		}
	}
	
	@RequestMapping(value = "/admin/product/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ProductModel showProduct(@PathVariable(required = true) String id) {
		ProductModel _product = new ProductModel();
		_product = p_service.findById(id);
		return _product;
	}
	
	@RequestMapping(value = "/admin/product", method = RequestMethod.POST)
	@ResponseBody
	public String addProduct(ProductModel _product) {
		if (_product.getId() == 0) {
			int ok = p_service.insert(_product);
			if (ok > 0) {
				return "ok";
			} else {
				return "fail";
			}
		} else {
			return "fail";
		}
	}
	
	@RequestMapping(value = "/admin/product", method = RequestMethod.PUT)
	@ResponseBody
	public String updateProduct(ProductModel _product) throws ParseException {
		if (_product.getId() > 0) {
			int ok = p_service.update(_product);
			if (ok > 0) {
				return "ok";
			} else {
				return "fail";
			}
		} else {
			return "fail";
		}
	}
	
	@RequestMapping(value = "/admin/product/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteProduct(@PathVariable(required = true) int id) {
		int ok = p_service.deleteById(id);
		if (ok > 0) {
			return "ok";
		} else {
			return "fail";
		}
	}
}
