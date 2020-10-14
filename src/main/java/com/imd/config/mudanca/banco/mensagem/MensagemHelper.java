package com.imd.config.mudanca.banco.mensagem;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Component
public class MensagemHelper {

	private final String  LAYOUT_ATRIBUTO_MENSAGEM = "mensagemSitema";
	
	public void addMensagem(RedirectAttributes ra, String message, String tipoMensagem) {
		ra.addFlashAttribute(LAYOUT_ATRIBUTO_MENSAGEM, new Mensagem(message, tipoMensagem));
	}

	public void addMensagem(Model model, String message, String tipoMensagem) {
		model.addAttribute(LAYOUT_ATRIBUTO_MENSAGEM, new Mensagem(message, tipoMensagem));
	}
	
}
