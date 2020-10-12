package com.imd.config.mudanca.banco.mensagem;

import java.io.Serializable;

public class Mensagem  {

	
	String message;
	String tipoMensagem;
	
	public Mensagem(String message, String tipoMensagem) {
		this.message = message;
		this.tipoMensagem = tipoMensagem;
	}
	
	public Mensagem() {
		
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTipoMensagem() {
		return tipoMensagem;
	}

	public void setTipoMensagem(String tipoMensagem) {
		this.tipoMensagem = tipoMensagem;
	}

}
