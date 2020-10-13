package com.imd.config.mudanca.banco.command;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.Date;

import org.junit.jupiter.api.Test;

import com.imd.config.mudanca.banco.domain.Conta;
import com.imd.config.mudanca.banco.domain.Pessoa;
import com.imd.config.mudanca.banco.service.BancoService;

import junit.framework.TestCase;

class TestOperacaoDebito{
	
	BancoService bs = new BancoService ();
		
	@Test
	public void debitarSucesso1(){
		bs.carregarContas();
		bs.executarOperacao(bs.getConta("001"), null, new BigDecimal(75), new Date() , new OperacaoDebito());		
		assertEquals(new BigDecimal(925), bs.getConta("001").getSaldo());
	}
	
	@Test
	public void debitarSucesso2(){
		bs.executarOperacao(bs.getConta("001"), null, new BigDecimal(2), new Date() , new OperacaoDebito());		
		assertEquals(new BigDecimal(923), bs.getConta("001").getSaldo());
	}
	
	@Test
	public void debitarComZeroInsucesso() {
		bs.executarOperacao(bs.getConta("002"), null, new BigDecimal(0), new Date() , new OperacaoDebito());		
		assertEquals(new BigDecimal(2050), bs.getConta("002").getSaldo());
	}
	
	@Test
	public void debitarComNegativoInsucesso() {
		bs.executarOperacao(bs.getConta("002"), null, new BigDecimal(-50), new Date() , new OperacaoDebito());		
		assertEquals(new BigDecimal(2050), bs.getConta("002").getSaldo());
	}
	
	@Test
	public void debitarComValorSuperiorSaldoInsucesso() {
		bs.executarOperacao(bs.getConta("002"), null, new BigDecimal(2051), new Date() , new OperacaoDebito());		
		assertEquals(new BigDecimal(2050), bs.getConta("002").getSaldo());
	}
	
	@Test
	public void debitarComContaInexistenteInsucesso() {
		bs.executarOperacao(bs.getConta("004"), null, new BigDecimal(2051), new Date() , new OperacaoDebito());		
		assertEquals(new BigDecimal(2050), bs.getConta("004").getSaldo());
	}

}
