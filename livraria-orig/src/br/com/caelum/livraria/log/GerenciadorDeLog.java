package br.com.caelum.livraria.log;

import java.io.Serializable;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * 
 * @author Weverton Reis
 *
 */
@Log
@Interceptor
public class GerenciadorDeLog implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@AroundInvoke
	public Object logar(InvocationContext context) throws Exception {
		long inicio = System.currentTimeMillis();
		Object result = context.proceed();
		long fim = System.currentTimeMillis();
		System.out.println("Tempo gasto no " + context.getMethod().getName() + " " + (fim - inicio));
		return result;
	}

}
