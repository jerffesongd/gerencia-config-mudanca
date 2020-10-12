package com.imd.config.mudanca.banco.domain;

import java.math.BigDecimal;

public class Conta {
	
	private String agencia;
	private String numero;
	private String senha;
	private BigDecimal saldo;
	private Pessoa dono;
	
	
	public Conta() {
		super();
	}
	public Conta(String agencia, String numero, String senha, BigDecimal saldo, Pessoa dono) {
		super();
		this.agencia = agencia;
		this.numero = numero;
		this.senha = senha;
		this.saldo = saldo;
		this.dono = dono;
	}
	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public BigDecimal getSaldo() {
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	public Pessoa getDono() {
		return dono;
	}
	public void setDono(Pessoa dono) {
		this.dono = dono;
	} 
	
	
	
}
