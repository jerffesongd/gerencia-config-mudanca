package com.imd.config.mudanca.banco.command;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.imd.config.mudanca.banco.domain.Conta;
import com.imd.config.mudanca.banco.service.BancoService;

@Component
public class OperacaoDeposito implements OperacaoCommand{

	private BancoService bancoService = new BancoService();
	
	@Override
	public BigDecimal executar(Conta contaOrigem, Conta contaDestino, BigDecimal valor, Date dataOperacao) {
				
		contaOrigem.setSaldo(contaOrigem.getSaldo().add(valor));
		contaOrigem.setBonus(contaOrigem.getBonus().add(valor.multiply(new BigDecimal (0.01))));
		contaOrigem.setSaldo(contaOrigem.getSaldo().add(valor.multiply(new BigDecimal (0.01))));						
		return contaOrigem.getSaldo();
		
	}

}
