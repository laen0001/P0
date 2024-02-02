package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("texto","Acceso a la URL raiz");
		model.addAttribute("texto2","Acceso a la URL raiz 2");
		return "home";
	}

}
