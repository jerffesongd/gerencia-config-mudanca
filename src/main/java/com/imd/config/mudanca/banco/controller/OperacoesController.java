package com.imd.config.mudanca.banco.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.imd.config.mudanca.banco.command.OperacaoSaldo;
import com.imd.config.mudanca.banco.domain.Conta;
import com.imd.config.mudanca.banco.service.BancoService;


@Controller
@RequestMapping("/operacao")
public class OperacoesController {

	@Autowired
	private BancoService bancoService;
	
	@GetMapping("/saldo")
	public ResponseEntity getSaldoConta() {
		
		try {
			
			Conta c = ((Conta) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
			return new ResponseEntity(bancoService.executarOperacao(null, null, null, new Date() , new OperacaoSaldo()), HttpStatus.OK);
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}
