package com.imd.config.mudanca.banco.service;

import java.math.BigDecimal;
import java.security.InvalidParameterException;

import org.springframework.stereotype.Service;

import com.imd.config.mudanca.banco.domain.Conta;
import com.imd.config.mudanca.banco.helper.Mensagens;

@Service
public class BancoService {

	
	public void visualizarSaldo(Conta conta, BigDecimal valor) {
		
		validarConta(conta);
		
		
	}

	private void validarOperacaoSaque(Conta conta, BigDecimal valor) {
		
		validarConta(conta);
		
		if (valor == null) {
			throw new InvalidParameterException(Mensagens.VALOR_TRANSACAO_INVALIDO);
		}
		
		if (valor.compareTo(new BigDecimal("0")) == 0 || valor.compareTo(new BigDecimal("0"))  == -1) {
			throw new InvalidParameterException(Mensagens.VALOR_TRANSACAO_INVALIDO);
		}
		
		
		
		if (conta.getSaldo().compareTo(valor) == -1) {
			throw new InvalidParameterException(Mensagens.SALDO_INSUFICIENTE);
		}
		
	}

	private void validarConta(Conta conta) {
		if (conta == null) {
			throw new InvalidParameterException(Mensagens.CONTA_NAO_EXISTE);
		}
		
		if (!contaExiste(conta)) {
			throw new InvalidParameterException(Mensagens.CONTA_NAO_EXISTE);
		}
	}

	private boolean contaExiste(Conta conta) {
		
		return false;
	}
	
	
}
