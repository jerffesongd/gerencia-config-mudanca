package com.imd.config.mudanca.banco.command;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.imd.config.mudanca.banco.domain.Conta;
import com.imd.config.mudanca.banco.service.BancoService;

@Component
public class OperacaoTransferencia implements OperacaoCommand{

	private BancoService bancoService = new BancoService();
	
	@Override
	public BigDecimal executar(Conta contaOrigem, Conta contaDestino, BigDecimal valor, Date dataOperacao) {
		
		
		bancoService.validarConta(contaOrigem);
		bancoService.validarConta(contaDestino);
		bancoService.validarOperacaoSaque(contaOrigem, valor);
		
		contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(valor));
		contaDestino.setSaldo(contaDestino.getSaldo().add(valor));
		
		return valor;
	}

	
}
