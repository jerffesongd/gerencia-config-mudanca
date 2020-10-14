package com.imd.config.mudanca.banco.command;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.imd.config.mudanca.banco.mensagem.Mensagens;

import com.imd.config.mudanca.banco.service.BancoService;

class TestOperacaoCredito{
	
	BancoService bs = new BancoService ();
	
	@BeforeAll
	public static void beforeAll() {
		BancoService.carregarContas();
	}
	
	@Test
	public void creditarSucesso1() {
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
		
		Exception exception = assertThrows(InvalidParameterException.class, () -> {
			bs.executarOperacao(bs.getConta("002"), null, new BigDecimal(0), new Date() , new OperacaoCredito());
	    });
	 
		String expectedMessage = Mensagens.VALOR_TRANSACAO_INVALIDO;
	    String actualMessage = exception.getMessage();		
		
	    assertTrue(actualMessage.equals(expectedMessage));
	}
	
	@Test
	public void creditarComNegativoInsucesso() {
		
		Exception exception = assertThrows(InvalidParameterException.class, () -> {
			bs.executarOperacao(bs.getConta("002"), null, new BigDecimal(-50), new Date() , new OperacaoCredito());	
	    });
	 
		String expectedMessage = Mensagens.VALOR_TRANSACAO_INVALIDO;
	    String actualMessage = exception.getMessage();		
		
	    assertTrue(actualMessage.equals(expectedMessage));
		
	}
	
	@Test
	public void creditarComContaInexistenteInsucesso() {
		
		
		Exception exception = assertThrows(InvalidParameterException.class, () -> {
			bs.executarOperacao(bs.getConta("003"), null, new BigDecimal(-50), new Date() , new OperacaoCredito());	
	    });
	 
		String expectedMessage = Mensagens.CONTA_NAO_EXISTE;
	    String actualMessage = exception.getMessage();		
		
	    assertTrue(actualMessage.equals(expectedMessage));
		
	}

}
