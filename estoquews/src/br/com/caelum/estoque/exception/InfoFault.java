package br.com.caelum.estoque.exception;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class InfoFault {
	
	private Date dataErro;
	private String mensagem;
	
	/**
	 * 
	 * @param dataErro
	 * @param mensagem
	 */
	public InfoFault(Date dataErro, String mensagem) {
		super();
		this.dataErro = dataErro;
		this.mensagem = mensagem;
	}
	
	/**
	 * 
	 */
	public InfoFault() {
		super();
	}

	public Date getDataErro() {
		return dataErro;
	}

	public void setDataErro(Date dataErro) {
		this.dataErro = dataErro;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
}
