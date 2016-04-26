package br.com.caelum.livraria.validator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "nomeDoValidador")
public class ExemploValidator implements Validator {

	@Override
	public void validate(FacesContext facesContext, UIComponent uiComponent, Object object) 
			throws ValidatorException {
		
		// Processo de validacao

	}

}
