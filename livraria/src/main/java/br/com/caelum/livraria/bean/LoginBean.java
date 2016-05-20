package br.com.caelum.livraria.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.dao.UsuarioDao;
import br.com.caelum.livraria.modelo.Usuario;

/**
 * 
 * @author Weverton Reis
 *
 */
@Named
@ViewScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Usuario usuario = new Usuario();
	
	@Inject
	private UsuarioDao usuarioDao;
	
	@Inject
	private FacesContext facesContext;
	
	/**
	 * 
	 * @return
	 */
	public String efetuaLogin() {
		boolean existe = usuarioDao.existe(this.usuario);

		if(!existe){
			facesContext.addMessage(null, new FacesMessage("Usuário não existe!"));
			facesContext.getExternalContext().getFlash().setKeepMessages(true);
			return "login?faces-redirect=true";
		}
		
		facesContext.getExternalContext().getSessionMap().put("usuarioLogado", usuario);
	    return "livro?faces-redirect=true";
	}
	
	/**
	 * 
	 * @return
	 */
	public String efetuarLogout() {
		facesContext.getExternalContext().getSessionMap().remove("usuarioLogado");
		return "login?faces-redirect=true";
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
}