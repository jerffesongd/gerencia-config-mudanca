package com.imd.config.mudanca.banco.command;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.Date;

import org.junit.jupiter.api.Test;

import com.imd.config.mudanca.banco.domain.Conta;
import com.imd.config.mudanca.banco.domain.Pessoa;
import com.imd.config.mudanca.banco.service.BancoService;


class TestOperacaoSaldo{
	
	BancoService bs = new BancoService ();
		
	@Test
	public void saldoSucesso() {
		bs.carregarContas();
		bs.executarOperacao(bs.getConta("001"), null, null, new Date() , new OperacaoSaldo());		
		assertEquals(new BigDecimal(1000), bs.getConta("001").getSaldo());
	}
	
	@Test
	public void saldoInsucesso() {
		bs.executarOperacao(bs.getConta("003"), null, new BigDecimal(2), new Date() , new OperacaoSaldo());		
		assertEquals(new BigDecimal(1000), bs.getConta("003").getSaldo());
	}
	
	

}
