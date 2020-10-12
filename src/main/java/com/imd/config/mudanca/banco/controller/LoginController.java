package com.imd.config.mudanca.banco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.imd.config.mudanca.banco.mensagem.MensagemHelper;
import com.imd.config.mudanca.banco.mensagem.Mensagens;
import com.imd.config.mudanca.banco.mensagem.TipoMensagem;

@Controller
@RequestMapping("/")
public class LoginController {

	@Autowired
	private MensagemHelper mensagemHelper;
	
	@GetMapping(value= {"login", ""})
	public ModelAndView login(Model model) {
		
		ModelAndView modelAndView = new ModelAndView("/login/login");
		
		mensagemHelper.addMensagem(model, Mensagens.CONTA_NAO_EXISTE, TipoMensagem.ERRO);
		
		return modelAndView;
	}
	
	@PostMapping("login")
	public ModelAndView realizarLogin(@RequestParam(name = "numero", required = true) String numero, @RequestParam(name = "senha", required = true) String senha) {
		
		ModelAndView modelAndView = new ModelAndView("/funcionalidades/index");

		return modelAndView;
	}
	
	
}
