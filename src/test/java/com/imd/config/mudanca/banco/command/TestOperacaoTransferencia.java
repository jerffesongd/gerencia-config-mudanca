package com.imd.config.mudanca.banco.command;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.imd.config.mudanca.banco.mensagem.Mensagens;
import com.imd.config.mudanca.banco.service.BancoService;

class TestOperacaoTransferencia{
	
	BancoService bs = new BancoService ();
	
	@BeforeAll
	public static void beforeAll() {
		BancoService.carregarContas();
	}
		
	@Test
	public void transferirSucesso1() {
		bs.executarOperacao(bs.getConta("001"), bs.getConta("002"), new BigDecimal(75), new Date() , new OperacaoTransferencia());		
		assertEquals(new BigDecimal(925), bs.getConta("001").getSaldo());
		assertEquals(new BigDecimal(2075), bs.getConta("002").getSaldo());
	}
	
	@Test
	public void transferirSucesso2() {
		bs.executarOperacao(bs.getConta("001"), bs.getConta("002"), new BigDecimal(925), new Date() , new OperacaoTransferencia());		
		assertEquals(new BigDecimal(0), bs.getConta("001").getSaldo());
		assertEquals(new BigDecimal(3000), bs.getConta("002").getSaldo());
	}
	
	@Test
	public void transferirComZeroInsucesso() {
		
		
		Exception exception = assertThrows(InvalidParameterException.class, () -> {
			bs.executarOperacao(bs.getConta("001"), bs.getConta("002"), new BigDecimal(0), new Date() , new OperacaoTransferencia());		
		});
	 
		String expectedMessage = Mensagens.VALOR_TRANSACAO_INVALIDO;
	    String actualMessage = exception.getMessage();		
		
	    assertTrue(actualMessage.equals(expectedMessage));
		
	}
	
	@Test
	public void transferirContaDestinoInexistenteInsucesso() {
		
		Exception exception = assertThrows(InvalidParameterException.class, () -> {
			bs.executarOperacao(bs.getConta("002"), bs.getConta("003"), new BigDecimal(50), new Date() , new OperacaoTransferencia());		
		});
	 
		String expectedMessage = Mensagens.CONTA_NAO_EXISTE;
	    String actualMessage = exception.getMessage();		
		
	    assertTrue(actualMessage.equals(expectedMessage));
		

	}
	
	@Test
	public void transferirContaOrigemInexistenteInsucesso() {
		
		
		Exception exception = assertThrows(InvalidParameterException.class, () -> {
			bs.executarOperacao(bs.getConta("003"), bs.getConta("002"), new BigDecimal(50), new Date() , new OperacaoTransferencia());		
		});
	 
		String expectedMessage = Mensagens.CONTA_NAO_EXISTE;
	    String actualMessage = exception.getMessage();		
		
	    assertTrue(actualMessage.equals(expectedMessage));
	    
	}
	@Test
	public void transferirComValorSuperiorSaldoInsucesso() {
		
		Exception exception = assertThrows(InvalidParameterException.class, () -> {
			bs.executarOperacao(bs.getConta("001"), bs.getConta("002"), new BigDecimal(2501), new Date() , new OperacaoTransferencia());		
			
		});
	 
		String expectedMessage = Mensagens.SALDO_INSUFICIENTE;
	    String actualMessage = exception.getMessage();		
		
	    assertTrue(actualMessage.equals(expectedMessage));
	
	}
	
	@Test
	public void transferirComValorNegativoInsucesso() {
		
		
		Exception exception = assertThrows(InvalidParameterException.class, () -> {
			bs.executarOperacao(bs.getConta("002"), bs.getConta("001"), new BigDecimal(-1), new Date() , new OperacaoTransferencia());				
		});
	 
		String expectedMessage = Mensagens.VALOR_TRANSACAO_INVALIDO;
	    String actualMessage = exception.getMessage();		
		
	    assertTrue(actualMessage.equals(expectedMessage));

	}

	
	
}
