package com.imd.config.mudanca.banco.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.imd.config.mudanca.banco.domain.Conta;


@Controller
@RequestMapping("/operacao")
public class OperacoesController {

	@GetMapping("/saldo")
	public ResponseEntity getSaldoConta() {
		
		Conta c = ((Conta) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		return new ResponseEntity(c.getSaldoFormatado(), HttpStatus.OK);
		
	}
	
	
}
