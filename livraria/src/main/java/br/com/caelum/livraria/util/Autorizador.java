package br.com.caelum.livraria.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.caelum.livraria.modelo.Usuario;

public class Autorizador implements PhaseListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext facesContext = event.getFacesContext();
		String nomeDaPagina = facesContext.getViewRoot().getViewId();
		
		if (nomeDaPagina.equals("/login.xhtml")) {
			return;
		}
		
		Usuario usuario = (Usuario) facesContext.getExternalContext().getSessionMap().get("usuarioLogado");
		
		if (usuario != null) {
			return;
		}
		
		NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();
		navigationHandler.handleNavigation(facesContext, null, "login?faces-redirect=true");
		
		facesContext.renderResponse();
	}

	@Override
	public void beforePhase(PhaseEvent event) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
