package com.imd.config.mudanca.banco.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
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
	public ResponseEntity<String> getSaldoConta() {
		
		try {
			
			Conta c = ((Conta) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
			BigDecimal saldo = bancoService.executarOperacao(c, null, null, new Date() , new OperacaoSaldo());
			
			return new ResponseEntity<String>("R$ " + getSaldoFormatado(saldo), HttpStatus.OK);
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private String getSaldoFormatado(BigDecimal valor) {
		return new DecimalFormat("#,###,##0.00").format(valor);
	}
	
	
}
