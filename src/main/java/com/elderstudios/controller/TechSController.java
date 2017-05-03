package com.elderstudios.controller;

import com.elderstudios.domain.TechSEntry;
import com.elderstudios.service.TechSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class TechSController {

	@Autowired
	protected TechSService techSService;

	private static final String TECHS_FORM = "techs";
	private static final String ENTRIES_KEY = "entries";
	private static final String FORM_KEY = "form";

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String displayTechS( Model model ) {

		model.addAttribute(ENTRIES_KEY, techSService.findAll());
		model.addAttribute(FORM_KEY, new TechSEntry());

		return TECHS_FORM;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String changeTechS(
			Model model,
			@Valid @ModelAttribute(FORM_KEY) TechSEntry form,
			BindingResult bindingResult ) {

		if ( bindingResult.hasErrors() ) {
			model.addAttribute(ENTRIES_KEY, techSService.findAll());
			return TECHS_FORM;
		}

		techSService.save(form);

		return "redirect:/";
	}

	@RequestMapping(value="/delete/{id}", method=RequestMethod.POST)
	public String deleteEntry (Model model, @PathVariable Long id) {

		techSService.delete (id);

		return "redirect:/";
	}

	@RequestMapping (value="/edit/{id}", method = RequestMethod.GET)
	public String editEntry (Model model, @PathVariable Long id) {
		model.addAttribute (FORM_KEY, techSService.findOne (id));
		return TECHS_FORM;
	}

	@RequestMapping (value="/edit/{id}", method = RequestMethod.POST)
	public String editSaveTechS (Model model,
									 @PathVariable Long id,
									 @Valid @ModelAttribute(FORM_KEY) TechSEntry form,
									 BindingResult bindingResult ) {

		if ( bindingResult.hasErrors() ) {
			model.addAttribute(ENTRIES_KEY, techSService.findAll());
			return TECHS_FORM;
		}

		TechSEntry existing = techSService.findOne (id);
		existing.setName (form.getName());
		existing.setComment(form.getComment());
		techSService.save (existing);

		return "redirect:/";
	}


}