package com.imd.config.mudanca.banco.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class Conta implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String agencia;
	private String numero;
	private String senha;
	private BigDecimal saldo;
	private Pessoa dono;
	private BigDecimal bonus;
	
	
	public Conta() {
		super();
	}
	public Conta(String agencia, String numero, String senha, BigDecimal saldo, Pessoa dono, BigDecimal bonus) {
		super();
		this.agencia = agencia;
		this.numero = numero;
		this.senha = senha;
		this.saldo = saldo;
		this.dono = dono;
		this.bonus = bonus;
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
	public BigDecimal getBonus() {
		return bonus;
	}
	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
	} 
	
	
	
}
