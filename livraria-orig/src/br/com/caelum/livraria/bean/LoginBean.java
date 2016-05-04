package br.com.caelum.livraria.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.caelum.livraria.dao.UsuarioDao;
import br.com.caelum.livraria.modelo.Usuario;

@ManagedBean
@ViewScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Usuario usuario = new Usuario();
	
	public String efetuaLogin() {
		System.out.println("Fazendo login com o usuário = " + usuario);
		
		boolean existe = new UsuarioDao().existe(this.usuario);

	    if (existe) {
	        return "livro?faces-redirect=true";
	    }
	    return null;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
}
