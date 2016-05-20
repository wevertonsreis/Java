package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.contantes.AtributosDeSessao;

/**
 * 
 * @author Weverton Reis
 *
 */
@Named
@SessionScoped
public class MenuBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private FacesContext facesContext;
	
	/**
	 * 
	 * @param url
	 * @return
	 */
	public String redirect(String url) {
		Map<String, Object> sessionMap = facesContext.getExternalContext().getSessionMap();
		
		AtributosDeSessao[] values = AtributosDeSessao.values();
		
		for (AtributosDeSessao atributosDeSessao : values) {
			Object remove = sessionMap.remove(atributosDeSessao);
			if (remove != null) {
				System.out.println("REMOVEL O ABRIBUTO DA SESSAO = " + remove);
			}
		}
		
		return url + "?faces-redirect=true";
	}
	
}
