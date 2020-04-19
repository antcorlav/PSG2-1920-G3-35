
package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Causa;
import org.springframework.samples.petclinic.model.Donacion;
import org.springframework.samples.petclinic.service.CausaService;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.samples.petclinic.service.DonacionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/donacion")
public class DonacionController {

	private static final String	VIEWS_DONACION_CREATE_FORM	= "causas/causaDetails";

	private static final String	VIEWS_DONACION_NEW_FORM		= "donaciones/createOrUpdateDonacion";

	private CausaService		causaService;

	private ClinicService		clinicService;

	private DonacionService		donacionService;


	@Autowired
	public DonacionController(final CausaService causaService, final DonacionService donacionService, final ClinicService clinicService) {
		this.donacionService = donacionService;
		this.causaService = causaService;
		this.clinicService = clinicService;
	}

	@GetMapping(value = "/{ownerId}/{causaId}/new")
	public String initCreationForm(final Map<String, Object> model, @PathVariable("ownerId") final int ownerId, @PathVariable("causaId") final int causaId) {
		Causa causa = this.causaService.findCausaById(causaId);
		if (!causa.isValido()) {
			return "exception";
		} else {
			Donacion donacion = new Donacion();
			donacion.setOwner(this.clinicService.findOwnerById(ownerId));
			donacion.setFecha(LocalDate.now());
			model.put("donacion", donacion);
			return DonacionController.VIEWS_DONACION_NEW_FORM;
		}
	}

	@PostMapping(value = "/{ownerId}/{causaId}/new")
	public String processCreationForm(@Valid final Donacion donacion, @PathVariable("ownerId") final int ownerId, final BindingResult result, @PathVariable("causaId") final int causaId, final Map<String, Object> model, final ModelMap modelMap) {
		Causa causa = this.causaService.findCausaById(causaId);
		if (result.hasErrors() || !causa.isValido()) {
			model.put("donacion", donacion);
			return DonacionController.VIEWS_DONACION_CREATE_FORM;
		} else {
			if (causa.getDineroRecaudado() > causa.getObjetivo()) {
				return DonacionController.VIEWS_DONACION_NEW_FORM;
			}
			causa.setDineroRecaudado(causa.getDineroRecaudado() + donacion.getCantidad());
			if (causa.getDineroRecaudado() + donacion.getCantidad() >= causa.getObjetivo()) {
				causa.setValido(false);

			}
			donacion.setOwner(this.clinicService.findOwnerById(ownerId));
			donacion.setFecha(LocalDate.now());
			donacion.setCausa(causa);
			this.donacionService.saveDonacion(donacion);
			this.causaService.saveCausa(causa);

			return "redirect:/causa/" + ownerId + "/" + causa.getId();
		}
	}

}
