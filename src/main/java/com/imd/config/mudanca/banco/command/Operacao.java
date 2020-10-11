package com.imd.config.mudanca.banco.command;

import com.imd.config.mudanca.banco.domain.Conta;

public interface Operacao {

		public double executar(Conta contaOrigem, Conta contaDestino, int valor);
}
