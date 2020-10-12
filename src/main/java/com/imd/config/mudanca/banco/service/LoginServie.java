package com.imd.config.mudanca.banco.service;

import javax.naming.directory.InvalidAttributeIdentifierException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.imd.config.mudanca.banco.domain.Conta;
import com.imd.config.mudanca.banco.mensagem.Mensagens;

@Service
public class LoginServie {

	@Autowired
	private BancoService bancoService;
	
	
	public void realizarLogin(String numero, String senha) throws InvalidAttributeIdentifierException {
		
		if (!bancoService.contaExiste(numero)) {
			throw new InvalidAttributeIdentifierException(Mensagens.CONTA_OU_SENHA_NAO_EXISTEM);
		}
		
		Conta conta = bancoService.getConta(numero);
		
		if (!conta.getSenha().equals(senha)) {
			throw new InvalidAttributeIdentifierException(Mensagens.CONTA_OU_SENHA_NAO_EXISTEM);
		}
		
		new UsernamePasswordAuthenticationToken(conta, senha, null);
		
	}
	
}
