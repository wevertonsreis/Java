package br.com.caelum.livraria.produces;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

/**
 * 
 * @author Weverton Reis
 *
 */
public class ProdutorFacesContext {
	
	@Produces
	@RequestScoped
	public FacesContext criarFacesContext() {
		return FacesContext.getCurrentInstance();
	}
	
}
