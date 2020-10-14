package com.imd.config.mudanca.banco.command;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.jupiter.api.Test;

import com.imd.config.mudanca.banco.service.BancoService;


class TestOperacaoCredito{
	
	BancoService bs = new BancoService ();
		
	@Test
	public void creditarSucesso1() {
		bs.carregarContas();
		bs.executarOperacao(bs.getConta("001"), null, new BigDecimal(50), new Date() , new OperacaoCredito());		
		assertEquals(new BigDecimal(1050), bs.getConta("001").getSaldo());
	}
	
	@Test
	public void creditarSucesso2() {
		bs.executarOperacao(bs.getConta("001"), null, new BigDecimal(2), new Date() , new OperacaoCredito());		
		assertEquals(new BigDecimal(1052), bs.getConta("001").getSaldo());
	}
	
	@Test
	public void creditarComZeroInsucesso() {
		bs.executarOperacao(bs.getConta("002"), null, new BigDecimal(0), new Date() , new OperacaoCredito());		
		assertEquals(new BigDecimal(1000), bs.getConta("002").getSaldo());
	}
	
	@Test
	public void creditarComNegativoInsucesso() {
		bs.executarOperacao(bs.getConta("002"), null, new BigDecimal(-50), new Date() , new OperacaoCredito());		
		assertEquals(new BigDecimal(950), bs.getConta("002").getSaldo());
	}
	
	@Test
	public void creditarComContaInexistenteInsucesso() {
		bs.executarOperacao(bs.getConta("003"), null, new BigDecimal(-50), new Date() , new OperacaoCredito());		
		assertEquals(new BigDecimal(950), bs.getConta("003").getSaldo());
	}

}
