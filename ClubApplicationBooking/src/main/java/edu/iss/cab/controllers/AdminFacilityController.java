package edu.iss.cab.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.iss.cab.exception.FacilityNotFoundException;
import edu.iss.cab.model.Booking;
import edu.iss.cab.model.Facility;
import edu.iss.cab.service.BookingService;
import edu.iss.cab.service.FacilityService;
import edu.iss.cab.validator.FacilityValidator;

@RequestMapping(value = "/admin/facility")
@Controller
public class AdminFacilityController {
	@Autowired
	private FacilityService fService;

	@Autowired
	private BookingService bService;

	@Autowired
	private FacilityValidator fValidator;

	@InitBinder("facility")
	private void initFacilityBinder(WebDataBinder binder) {
		binder.addValidators(fValidator);
	}

	// Navigating facility-new Page
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView newFacilityPage() {
		ModelAndView mav = new ModelAndView("facility-new", "facility", new Facility());
		return mav;
	}

	// Creating a new Facility
	@RequestMapping(params = "create", value = "/create", method = RequestMethod.POST)
	public ModelAndView createNewFacility(@ModelAttribute @Valid Facility facility, BindingResult result,
			final RedirectAttributes redirectAttributes) {

		if (result.hasErrors())
			return new ModelAndView("facility-new");

		ModelAndView mav = new ModelAndView();
		String message = "New Facility \"" + facility.getFacilityName() + "\" was successfully created.";

		fService.createFacility(facility);
		mav.setViewName("redirect:/admin/facility/list");

		redirectAttributes.addFlashAttribute("message", message);
		return mav;
	}

	// Canceling creation of new Facility
	@RequestMapping(params = "cancel", value = "/create", method = RequestMethod.POST)
	public String cancelNewFacility() {
		return "redirect:/admin/facility/list";
	}

	// Showing list of facilities
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView facilityListPage() {
		ModelAndView mav = new ModelAndView("facility-list");
		List<Facility> facilityList = fService.findAllFacilitiesWithStatus();
		mav.addObject("facilityList", facilityList);
		return mav;
	}

	// Editing a facility
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editFacilityPage(@PathVariable String id) {
		ModelAndView mav = new ModelAndView("facility-edit");
		Integer fId = Integer.valueOf(id);
		Facility facility = fService.findFacility(fId);
		mav.addObject("facility", facility);
		return mav;
	}

	// Updating a facility
	@RequestMapping(params = "update", value = "/edit/{id}", method = RequestMethod.POST)
	public ModelAndView editFacility(@ModelAttribute @Valid Facility facility, BindingResult result,
			@PathVariable String id, final RedirectAttributes redirectAttributes) throws FacilityNotFoundException {

		if (result.hasErrors())
			return new ModelAndView("facility-edit");

		ModelAndView mav = new ModelAndView("redirect:/admin/facility/list");
		String message = "Facility was successfully updated.";

		Integer fId = Integer.valueOf(id);
		facility.setFacilityId(fId);
		fService.updateFacility(facility);

		redirectAttributes.addFlashAttribute("message", message);
		return mav;
	}

	@RequestMapping(params = "cancel", value = "/edit/{id}", method = RequestMethod.POST)
	public String cancelEditFacility() {
		return "redirect:/admin/facility/list";
	}

	// Deleting a facility
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteFacility(@PathVariable String id, final RedirectAttributes redirectAttributes)
			throws FacilityNotFoundException {

		ModelAndView mav = new ModelAndView("redirect:/admin/facility/list");
		Integer fId = Integer.valueOf(id);

		// Check Booking with this Facility ID

		List<Booking> bookings = bService.searchByFacilityId(fId);
		String message = "WARNING!!! This facility has already booked, so cannot be deleted!";
		if (bookings.size() == 0) {
			Facility facility = fService.findFacility(fId);
			fService.removeFacility(facility);
			message = "The facility " + facility.getFacilityName() + " was successfully deleted.";
		}
		redirectAttributes.addFlashAttribute("message", message);
		return mav;
	}
}
