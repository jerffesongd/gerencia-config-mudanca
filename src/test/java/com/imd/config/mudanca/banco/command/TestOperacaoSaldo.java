package com.imd.config.mudanca.banco.command;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.imd.config.mudanca.banco.mensagem.Mensagens;
import com.imd.config.mudanca.banco.service.BancoService;


class TestOperacaoSaldo{
	
	BancoService bs = new BancoService ();
		
	@BeforeAll
	public static void beforeAll() {
		BancoService.carregarContas();
	}
	
	@Test
	public void saldoSucesso() {
		bs.executarOperacao(bs.getConta("001"), null, null, new Date() , new OperacaoSaldo());		
		assertEquals(new BigDecimal(1000), bs.getConta("001").getSaldo());
	}
	
	@Test
	public void saldoInsucesso() {
		
		Exception exception = assertThrows(InvalidParameterException.class, () -> {
			bs.executarOperacao(bs.getConta("003"), null, new BigDecimal(2), new Date() , new OperacaoSaldo());	
	    });
	 
		String expectedMessage = Mensagens.CONTA_NAO_EXISTE;
	    String actualMessage = exception.getMessage();		
		
	    assertTrue(actualMessage.equals(expectedMessage));
	
	}
	
	

}
