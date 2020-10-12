package com.imd.config.mudanca.banco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class LoginController {

	@GetMapping(value= {"login", ""})
	public ModelAndView login(Model model) {
		
		ModelAndView modelAndView = new ModelAndView("/login");

		return modelAndView;
	}

	
	@GetMapping(value= {"operacoes"})
	public ModelAndView operacoes(Model model) {
		
		ModelAndView modelAndView = new ModelAndView("/funcionalidades/index");
		return modelAndView;
	}
	
	
}
