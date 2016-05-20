package br.com.caelum.livraria.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.caelum.livraria.modelo.Usuario;

/**
 * 
 * @author Weverton Reis
 *
 */
public class UsuarioDao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	/**
	 * 
	 * @param usuario
	 * @return
	 */
	public boolean existe(Usuario usuario) {
		
        TypedQuery<Usuario> query = manager
            .createQuery("SELECT u FROM Usuario u WHERE u.email = :pEmail AND u.senha = :pSenha", Usuario.class);

        query.setParameter("pEmail", usuario.getEmail());
        query.setParameter("pSenha", usuario.getSenha());

        try {
        	query.getSingleResult();
        } catch (NoResultException ex) {
            return false;
        }

        return true;
	}
	
}
