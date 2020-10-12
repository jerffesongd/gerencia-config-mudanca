package com.imd.config.mudanca.banco.command;

import java.math.BigDecimal;
import java.util.Date;

import com.imd.config.mudanca.banco.domain.Conta;

public class OperacaoSaldo implements OperacaoCommand{
	
	@Override
	public BigDecimal executar(Conta contaOrigem, Conta contaDestino, BigDecimal valor, Date dataOperacao) {
		// TODO Auto-generated method stub
		return null;
	}

}
