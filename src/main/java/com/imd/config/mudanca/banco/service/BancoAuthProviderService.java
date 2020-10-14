package com.imd.config.mudanca.banco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.imd.config.mudanca.banco.domain.Conta;

@Component
public class BancoAuthProviderService implements AuthenticationProvider {

	@Autowired
	private BancoService bancoService;

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {

		String login = auth.getName();
		String senha = auth.getCredentials().toString();

		if (bancoService.contaExiste(login)) {
			Conta conta = bancoService.getConta(login);
			if (conta.getSenha().equals(senha)) {
				return new UsernamePasswordAuthenticationToken(conta, senha, null);
			} else {
				throw new BadCredentialsException("Login e/ou Senha inválidos..");
			}
		} else {
			throw new UsernameNotFoundException("Login e/ou Senha inválidos.");
		}
	}

	@Override
	public boolean supports(Class<?> auth) {
		return auth.equals(UsernamePasswordAuthenticationToken.class);
	}

}
