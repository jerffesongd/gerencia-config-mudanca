package com.imd.config.mudanca.banco.command;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.Date;

import org.junit.jupiter.api.Test;

import com.imd.config.mudanca.banco.domain.Conta;
import com.imd.config.mudanca.banco.domain.Pessoa;
import com.imd.config.mudanca.banco.service.BancoService;

class TestOperacaoTransferencia{
	
	BancoService bs = new BancoService ();
		
	@Test
	public void transferirSucesso1() {
		bs.carregarContas();
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
		bs.executarOperacao(bs.getConta("001"), bs.getConta("002"), new BigDecimal(0), new Date() , new OperacaoTransferencia());		
		assertEquals(new BigDecimal(0), bs.getConta("001").getSaldo());
		assertEquals(new BigDecimal(3000), bs.getConta("002").getSaldo());
	}
	
	@Test
	public void transferirContaDestinoInexistenteInsucesso() {
		bs.executarOperacao(bs.getConta("002"), bs.getConta("003"), new BigDecimal(50), new Date() , new OperacaoTransferencia());		
		assertEquals(new BigDecimal(2950), bs.getConta("002").getSaldo());
		assertEquals(new BigDecimal(50), bs.getConta("003").getSaldo());
	}
	
	@Test
	public void transferirContaOrigemInexistenteInsucesso() {
		bs.executarOperacao(bs.getConta("003"), bs.getConta("002"), new BigDecimal(50), new Date() , new OperacaoTransferencia());		
		assertEquals(new BigDecimal(50), bs.getConta("003").getSaldo());
		assertEquals(new BigDecimal(2950), bs.getConta("002").getSaldo());
	}
	@Test
	public void transferirComValorSuperiorSaldoInsucesso() {
		bs.executarOperacao(bs.getConta("001"), bs.getConta("002"), new BigDecimal(1), new Date() , new OperacaoTransferencia());		
		assertEquals(new BigDecimal(0), bs.getConta("001").getSaldo());
		assertEquals(new BigDecimal(2051), bs.getConta("002").getSaldo());
	}
	
	@Test
	public void transferirComValorNegativoInsucesso() {
		bs.executarOperacao(bs.getConta("002"), bs.getConta("001"), new BigDecimal(-1), new Date() , new OperacaoTransferencia());		
		assertEquals(new BigDecimal(2050), bs.getConta("002").getSaldo());
		assertEquals(new BigDecimal(1), bs.getConta("001").getSaldo());
	}

	
	
}
