package br.com.caelum.livraria.tx;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

/**
 * 
 * @author Weverton Reis
 *
 */
@Transacional
@Interceptor
public class GerenciadorDeTransacao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	/**
	 * 
	 * @param context
	 * @throws Exception
	 */
	@AroundInvoke
	public Object executaTX(InvocationContext context) throws Exception {
		manager.getTransaction().begin();
		Object result = context.proceed();
		manager.getTransaction().commit();
		return result;
	}
	
}