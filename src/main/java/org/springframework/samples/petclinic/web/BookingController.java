
package org.springframework.samples.petclinic.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Booking;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookingController {

	private final ClinicService clinicService;


	@Autowired
	public BookingController(final ClinicService clinicService) {
		this.clinicService = clinicService;
	}

	@ModelAttribute("booking")
	public Booking loadPetWithBooking(@PathVariable("petId") final int petId) {
		Pet pet = this.clinicService.findPetById(petId);
		Booking booking = new Booking();
		pet.addBooking(booking);
		return booking;
	}

	// Spring MVC calls method loadPetWithBooking(...) before initNewBookingForm is called
	@GetMapping(value = "/owners/*/pets/{petId}/booking/new")
	public String initNewBookingForm(@PathVariable("petId") final int petId, final Map<String, Object> model) {
		return "pets/createOrUpdateBookingForm";
	}

	// Spring MVC calls method loadPetWithBooking(...) before processNewBookingForm is called
	@PostMapping(value = "/owners/{ownerId}/pets/{petId}/booking/new")
	public String processNewBookingForm(@Valid final Booking booking, final BindingResult result) {
		if (result.hasErrors()) {
			return "pets/createOrUpdateBookingForm";
		} else {
			this.clinicService.saveBooking(booking);
			return "redirect:/owners/{ownerId}";
		}
	}

	@GetMapping(value = "/owners/*/pets/{petId}/booking")
	public String showBooking(@PathVariable final int petId, final Map<String, Object> model) {
		model.put("booking", this.clinicService.findPetById(petId).getBooking());
		return "bookingList";
	}
}
