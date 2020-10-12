package com.imd.config.mudanca.banco.service;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.imd.config.mudanca.banco.domain.Conta;
import com.imd.config.mudanca.banco.domain.Pessoa;
import com.imd.config.mudanca.banco.mensagem.Mensagens;

@Service
public class BancoService {

	public static List<Conta> contas = new ArrayList<Conta>();
	
	public BigDecimal visualizarSaldo(Conta conta) {
		
		validarConta(conta);
		
		return conta.getSaldo();
		
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
	
	private void validarOperacaoTransferencia(Conta contaOrigem, Conta contaDestino, BigDecimal valor) {
		
		validarConta(contaOrigem);
		validarOperacaoSaque(contaOrigem, valor);
		validarConta(contaDestino);
		
	}
	@PostConstruct
	public static void carregarContas() {
		
		if (contas.isEmpty()) {
			Pessoa p1 = new Pessoa("Jerffeson Gomes", "111.111.111-11");
			Pessoa p2 = new Pessoa("Joicy Daliane", "222.222.222-22");
			Conta c1 = new Conta("0001", "001", "123", new BigDecimal(1000), p1);
			Conta c2 = new Conta("0001", "002", "123", new BigDecimal(2000), p2);
			
			contas.add(c1);
			contas.add(c2);
		}
		
	}
	
	private void validarConta(Conta conta) {
		
		if (conta == null) {
			throw new InvalidParameterException(Mensagens.CONTA_NAO_EXISTE);
		}
		
		if (!contaExiste(conta.getNumero())) {
			throw new InvalidParameterException(Mensagens.CONTA_NAO_EXISTE);
		}
	}

	public boolean contaExiste(String numero) {
		
		for (Conta c : contas) {
			if(numero.equalsIgnoreCase(c.getNumero())) {
				return true;
			}
		}
		
		return false;
	}
	
	public Conta getConta(String numero) {
		for (Conta c : contas) {
			if(numero.equalsIgnoreCase(c.getNumero())) {
				return c;
			}
		}
		
		return null;
	}
	
	
}
