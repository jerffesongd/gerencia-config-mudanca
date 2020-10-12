package com.imd.config.mudanca.banco.service;

import java.math.BigDecimal;
import java.security.InvalidParameterException;

import org.springframework.stereotype.Service;

import com.imd.config.mudanca.banco.domain.Conta;
import com.imd.config.mudanca.banco.helper.Mensagens;

@Service
public class BancoService {

	
	public void realizarSaque(Conta conta, BigDecimal valor) {
		
		validarOperacaoSaque(conta, valor);
		
	}

	private void validarOperacaoSaque(Conta conta, BigDecimal valor) {
		
		if (conta == null) {
			throw new InvalidParameterException(Mensagens.CONTA_NAO_EXISTE);
		}
		
		if (valor == null) {
			throw new InvalidParameterException(Mensagens.VALOR_TRANSACAO_INVALIDO);
		}
		
		
		if (valor.compareTo(new BigDecimal("0")) == 0 || valor.compareTo(new BigDecimal("0"))  == -1) {
			throw new InvalidParameterException(Mensagens.VALOR_TRANSACAO_INVALIDO);
		}
		
		if (!contaExiste(conta)) {
			throw new InvalidParameterException(Mensagens.CONTA_NAO_EXISTE);
		}
		
		if (conta.getSaldo().compareTo(valor) == -1) {
			throw new InvalidParameterException(Mensagens.SALDO_INSUFICIENTE);
		}
		
	}

	private boolean contaExiste(Conta conta) {
		
		return false;
	}
	
	
}
