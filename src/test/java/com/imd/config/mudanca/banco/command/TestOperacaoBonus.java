package com.imd.config.mudanca.banco.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.imd.config.mudanca.banco.mensagem.Mensagens;
import com.imd.config.mudanca.banco.service.BancoService;

public class TestOperacaoBonus {
	
	BancoService bs = new BancoService ();
	
	@BeforeAll
	public static void beforeAll() {
		BancoService.carregarContas();
	}
	
	@Test
	public void BonusSucesso() {
		bs.executarOperacao(bs.getConta("001"), null, null, new Date() , new OperacaoBonus());		
		assertEquals(new BigDecimal(0), bs.getConta("001").getBonus());
	}
	
	@Test
	public void BonusInsucesso() {
		
		Exception exception = assertThrows(InvalidParameterException.class, () -> {
			bs.executarOperacao(bs.getConta("003"), null, new BigDecimal(2), new Date() , new OperacaoBonus());	
	    });
	 
		String expectedMessage = Mensagens.CONTA_NAO_EXISTE;
	    String actualMessage = exception.getMessage();		
		
	    assertTrue(actualMessage.equals(expectedMessage));
	
	}

}
