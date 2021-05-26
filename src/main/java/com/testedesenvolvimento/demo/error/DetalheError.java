package com.testedesenvolvimento.demo.error;

import java.util.Date;

public class DetalheError {
	private Date dataerror;
	private String mensagem;
	
	public DetalheError(Date dataerror, String mensagem) {
		this.dataerror = dataerror;
		this.mensagem = mensagem;
	}
	public Date getDataerror() {
		return dataerror;
	}
	public void setDataerror(Date dataerror) {
		this.dataerror = dataerror;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	

}
