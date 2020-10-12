package com.imd.config.mudanca.banco.command;

import java.math.BigDecimal;

import com.imd.config.mudanca.banco.domain.Conta;

public class Debito implements OperacaoCommand{

	@Override
	public BigDecimal executar(Conta contaOrigem, Conta contaDestino, BigDecimal valor) {
		// TODO Auto-generated method stub
		return null;
	}

}
