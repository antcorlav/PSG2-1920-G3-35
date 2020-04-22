
package org.springframework.samples.petclinic.web;

import org.springframework.samples.petclinic.model.Causa;
import org.springframework.samples.petclinic.model.Donacion;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CausaValidator implements Validator {

	private static final String REQUIRED = "required";


	@Override
	public boolean supports(final Class<?> clazz) {
		return Donacion.class.isAssignableFrom(clazz);
	}

	public void validateObjetivo(final Object target) throws RecaudadoYObjetivoException {

		Causa causa = (Causa) target;
		Integer objetivo = causa.getObjetivo();
		Integer dineroRecaudado = causa.getDineroRecaudado();
		// name validation
		if (objetivo <= dineroRecaudado) {
			throw new RecaudadoYObjetivoException();
		}

	}

	@Override
	public void validate(final Object target, final Errors errors) {

	}

}
