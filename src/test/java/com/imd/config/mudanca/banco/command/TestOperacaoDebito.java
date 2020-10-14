package com.imd.config.mudanca.banco.command;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.imd.config.mudanca.banco.mensagem.Mensagens;
import com.imd.config.mudanca.banco.service.BancoService;


class TestOperacaoDebito{
	
	BancoService bs = new BancoService ();
		
	@BeforeAll
	public static void beforeAll() {
		BancoService.carregarContas();
	}
	
	@Test
	public void debitarSucesso1(){
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
		
		Exception exception = assertThrows(InvalidParameterException.class, () -> {
			bs.executarOperacao(bs.getConta("002"), null, new BigDecimal(0), new Date() , new OperacaoDebito());		
	    });
	 
		String expectedMessage = Mensagens.VALOR_TRANSACAO_INVALIDO;
	    String actualMessage = exception.getMessage();		
		
	    assertTrue(actualMessage.equals(expectedMessage));
		
	}
	
	@Test
	public void debitarComNegativoInsucesso() {
		
		Exception exception = assertThrows(InvalidParameterException.class, () -> {
			bs.executarOperacao(bs.getConta("002"), null, new BigDecimal(-50), new Date() , new OperacaoDebito());		
		});
	 
		String expectedMessage = Mensagens.VALOR_TRANSACAO_INVALIDO;
	    String actualMessage = exception.getMessage();		
		
	    assertTrue(actualMessage.equals(expectedMessage));

	}
	
	@Test
	public void debitarComValorSuperiorSaldoInsucesso() {
		
		
		Exception exception = assertThrows(InvalidParameterException.class, () -> {
			bs.executarOperacao(bs.getConta("002"), null, new BigDecimal(2051), new Date() , new OperacaoDebito());		
		});
	 
		String expectedMessage = Mensagens.SALDO_INSUFICIENTE;
	    String actualMessage = exception.getMessage();		
		
	    assertTrue(actualMessage.equals(expectedMessage));
		
	}
	
	@Test
	public void debitarComContaInexistenteInsucesso() {
		
		
		Exception exception = assertThrows(InvalidParameterException.class, () -> {
			bs.executarOperacao(bs.getConta("004"), null, new BigDecimal(2051), new Date() , new OperacaoDebito());		
		});
	 
		String expectedMessage = Mensagens.CONTA_NAO_EXISTE;
	    String actualMessage = exception.getMessage();		
		
	    assertTrue(actualMessage.equals(expectedMessage));
		
	}

}
