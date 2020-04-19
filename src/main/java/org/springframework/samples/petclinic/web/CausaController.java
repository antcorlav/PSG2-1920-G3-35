
package org.springframework.samples.petclinic.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Causa;
import org.springframework.samples.petclinic.service.CausaService;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/causa")
public class CausaController {

	private ClinicService	clinicService;

	private CausaValidator	causaValidator	= new CausaValidator();

	private CausaService	causaService;


	@Autowired
	public CausaController(final CausaService causaService, final ClinicService clinicService) {
		this.causaService = causaService;
		this.clinicService = clinicService;
	}

	@GetMapping(value = "/new")
	public String initCreationForm(final Map<String, Object> model) {
		Causa causa = new Causa();
		model.put("causa", causa);
		return "causas/createOrUpdateCausaForm";
	}

	@PostMapping(value = "/new")
	public String processCreationForm(@Valid final Causa causa, final BindingResult result, final Map<String, Object> model) {
		if (result.hasErrors()) {
			return "causas/createOrUpdateCausaForm";
		} else {
			String mensaje = "";
			try {
				this.causaValidator.validateObjetivo(causa);
			} catch (RecaudadoYObjetivoException e) {
				mensaje = mensaje.concat("El dinero recaudado debe ser menor al objetivo");
				ObjectError errorFecha = new ObjectError("ErrorDineroRecaudadoYObjetivo", "El dinero recaudado debe ser menor al objetivo");
				result.addError(errorFecha);
			}
			if (mensaje != "") {
				model.put("mensaje", mensaje);
				return "causas/createOrUpdateCausaForm";
			} else {
				causa.setValido(true);
			}
			this.causaService.saveCausa(causa);

			return "redirect:/welcome";
		}

	}

	@GetMapping("/{ownerId}/{causaId}")
	public ModelAndView showCausa(@PathVariable("ownerId") final int ownerId, @PathVariable("causaId") final int id, final Model model) {
		ModelAndView mav = new ModelAndView("causas/causaDetails");
		mav.addObject(this.causaService.findCausaById(id));
		mav.addObject(this.clinicService.findOwnerById(ownerId));
		return mav;
	}

}
