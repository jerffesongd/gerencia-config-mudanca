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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.imd.config.mudanca.banco.command.OperacaoDeposito;

import com.imd.config.mudanca.banco.command.OperacaoBonus;

import com.imd.config.mudanca.banco.command.OperacaoDebito;
import com.imd.config.mudanca.banco.command.OperacaoSaldo;
import com.imd.config.mudanca.banco.command.OperacaoTransferencia;
import com.imd.config.mudanca.banco.domain.Conta;
import com.imd.config.mudanca.banco.mensagem.MensagemHelper;
import com.imd.config.mudanca.banco.mensagem.Mensagens;
import com.imd.config.mudanca.banco.mensagem.TipoMensagem;
import com.imd.config.mudanca.banco.service.BancoService;


@Controller
@RequestMapping("/operacao")
public class OperacoesController {

	@Autowired
	private BancoService bancoService;
	
	@Autowired
	private MensagemHelper mensagemHelper;
	
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
	
	@GetMapping("/debito")
	public ModelAndView detitarConta() {
		
		ModelAndView modelAndView = new ModelAndView("/funcionalidades/debito");
		
		return modelAndView;
	}
	
	@PostMapping("/debito")
	public ModelAndView executarDetitoConta( @RequestParam("valor") String valor, RedirectAttributes ra) {
		
		ModelAndView modelAndView = new ModelAndView(new RedirectView("/operacoes", true));
		
		
		try {
			
			BigDecimal valorDebitar = new BigDecimal(valor);
			Conta c = ((Conta) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
			bancoService.executarOperacao(c, null, valorDebitar, new Date() , new OperacaoDebito());
			
			mensagemHelper.addMensagem(ra, Mensagens.OPERACAO_REALIZADA_COM_SUCESSO, TipoMensagem.SUCESSO);

		}catch (Exception e) {
			e.printStackTrace();
			mensagemHelper.addMensagem(ra, e.getMessage(), TipoMensagem.ERRO);
		}
		
		return modelAndView;
	}
	
	@GetMapping("/deposito")
	public ModelAndView depositarConta() {
		
		ModelAndView modelAndView = new ModelAndView("/funcionalidades/deposito");
		
		return modelAndView;
	}
	
	@PostMapping("/deposito")
	public ModelAndView executarDepositoConta( @RequestParam("valor") String valor, RedirectAttributes ra) {
		
		ModelAndView modelAndView = new ModelAndView(new RedirectView("/operacoes", true));
		
		
		try {
			
			BigDecimal valorDebitar = new BigDecimal(valor);
			Conta c = ((Conta) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
			bancoService.executarOperacao(c, null, valorDebitar, new Date() , new OperacaoDeposito());
			
			mensagemHelper.addMensagem(ra, Mensagens.OPERACAO_REALIZADA_COM_SUCESSO, TipoMensagem.SUCESSO);

		}catch (Exception e) {
			e.printStackTrace();
			mensagemHelper.addMensagem(ra, e.getMessage(), TipoMensagem.ERRO);
		}
		
		return modelAndView;
	}
	
	
	
	@GetMapping("/transferencia")
	public ModelAndView tranferirEntreConta() {
		
		ModelAndView modelAndView = new ModelAndView("/funcionalidades/transferencia");
		
		return modelAndView;
	}
	
	@PostMapping("/transferencia")
	public ModelAndView executarTransferenciaConta( @RequestParam("contaDestino") String conta, @RequestParam("valor") String valor, RedirectAttributes ra) {
		
		ModelAndView modelAndView = new ModelAndView(new RedirectView("/operacoes", true));
		
		
		try {
			
			BigDecimal valorDebitar = new BigDecimal(valor);
			Conta c = ((Conta) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
			Conta contaDestino = bancoService.getConta(conta);
			bancoService.executarOperacao(c, contaDestino, valorDebitar, new Date() , new OperacaoTransferencia());
			
			mensagemHelper.addMensagem(ra, Mensagens.OPERACAO_REALIZADA_COM_SUCESSO, TipoMensagem.SUCESSO);

		}catch (Exception e) {
			e.printStackTrace();
			mensagemHelper.addMensagem(ra, e.getMessage(), TipoMensagem.ERRO);
		}
		
		return modelAndView;
	}
	
	@GetMapping("/bonus")
	public ResponseEntity<String> getBonusConta() {
		
		try {
			
			Conta c = ((Conta) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
			BigDecimal bonus = bancoService.executarOperacao(c, null, null, new Date() , new OperacaoBonus());
			
			return new ResponseEntity<String>("R$ " + getSaldoFormatado(bonus), HttpStatus.OK);
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private String getSaldoFormatado(BigDecimal valor) {
		return new DecimalFormat("#,###,##0.00").format(valor);
	}
	
	
}
