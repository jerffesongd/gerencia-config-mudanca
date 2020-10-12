package com.imd.config.mudanca.banco.command;

import java.math.BigDecimal;

import com.imd.config.mudanca.banco.domain.Conta;

public interface Operacao {

		public double executar(Conta contaOrigem, Conta contaDestino, BigDecimal valor);
}
