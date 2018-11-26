package zx.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {
	

	
	@RequestMapping("/")
	public String home(Model model) {
		model.addAttribute("navIndex", 1);
		return "/index";
	}

	@RequestMapping("/about")
	public String about(Model model) {
		model.addAttribute("navIndex", 2);
		return "/about";
	}

	@RequestMapping("/contact")
	public String contact(Model model) {
		model.addAttribute("navIndex", 5);
		return "/contact";
	}
	
	@RequestMapping("/aptitude")
	public String aptitude(Model model) {
		model.addAttribute("navIndex", 6);
		return "/aptitude";
	}

}
