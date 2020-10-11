package com.imd.config.mudanca.banco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class LoginController {

	
	@GetMapping(value= {"login", ""})
	public ModelAndView login() {
		
		ModelAndView modelAndView = new ModelAndView("/login/login");
		
		return modelAndView;
	}
	
	@PostMapping("login")
	public ModelAndView realizarLogin(@RequestParam(name = "numero", required = true) String numero, @RequestParam(name = "senha", required = true) String senha) {
		
		ModelAndView modelAndView = new ModelAndView("/funcionalidades/index");

		return modelAndView;
	}
	
	
}
