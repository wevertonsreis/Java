package br.com.caelum.estoque.exception;

import java.util.Date;

import javax.xml.ws.WebFault;

@WebFault(name = "AutorizacaoFault", messageName = "AutorizacaoFault")
public class AutorizacaoException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param message
	 */
	public AutorizacaoException(String message) {
		super(message);
	}
	
	/**
	 * 
	 * @return
	 */
	public InfoFault getFaultInfo() {
        return new InfoFault(new Date(), "Token invalido") ;
    }

}
