package edu.iss.cab.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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

import edu.iss.cab.model.Booking;
import edu.iss.cab.model.Facility;
import edu.iss.cab.service.BookingService;
import edu.iss.cab.service.FacilityService;
import edu.iss.cab.validator.BookingValidator;

@RequestMapping(value = "/admin/booking")
@Controller
public class AdminBookingController {
	@Autowired
	private FacilityService fService;

	@Autowired
	private BookingService bService;

	@Autowired
	private BookingValidator bValidator;

	@InitBinder("booking")
	private void initBookingBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		binder.addValidators(bValidator);
	}

	// Navigating Booking page for Facility
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView bookingFacilityPage(@PathVariable String id) {
		ModelAndView mav = new ModelAndView("book-make");
		Booking booking = new Booking();

		Integer fId = Integer.valueOf(id);
		Facility facility = fService.findFacility(fId);
		booking.setFacility(facility);

		List<Booking> oldBookingList = bService.searchByFacilityId(fId);
		if (oldBookingList.size() > 0) {
			List<Booking> newBookingList = new ArrayList<Booking>();
			Date currentDay = new Date();
			for (Booking b : oldBookingList) {
				Date d = b.getDate();
				if (d.after(currentDay)) {
					newBookingList.add(b);
				}
			}
			mav.addObject("bookingList", newBookingList);
		}

		mav.addObject("booking", booking);

		return mav;
	}

	// Book Facility
	@RequestMapping(params = "book", value = "/maintenance/book", method = RequestMethod.POST)
	public ModelAndView createNewBooking(@ModelAttribute @Valid Booking booking, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		if (result.hasErrors())
			return new ModelAndView("book-make");

		ModelAndView mav = new ModelAndView();

		Booking bookingWithDate = bService.findBookingByDate(booking.getFacility().getFacilityId(), booking.getDate());
		if (bookingWithDate != null) {
			String noSuccessMessage = "Sorry! Your selected facility is already booked.";
			redirectAttributes.addFlashAttribute("message", noSuccessMessage);
		} else {
			bService.createBooking(booking);
			String successMessage = "Your Facility with " + booking.getFacility().getFacilityName()
					+ " was successfully booked.";
			redirectAttributes.addFlashAttribute("message", successMessage);
		}
		mav.setViewName("redirect:/facilities");
		return mav;
	}

	// Canceling booking
	@RequestMapping(params = "cancel", value = "/maintenance/book", method = RequestMethod.POST)
	public String cancelNewBooking() {
		return "redirect:/facilities";
	}

	// Admin maintenance history
	@RequestMapping(value = "/history", method = RequestMethod.GET)
	public ModelAndView showHistoryMaintenance() {

		ModelAndView mav = new ModelAndView("maintenance-history");
		List<Booking> bookingList = bService.historyBookingbyAdmin();
		mav.addObject("bookingList", bookingList);
		if (bookingList.size() == 0)
			mav.addObject("noRecord", "No Booking Record");
		return mav;
	}

	// Admin maintenance current - loading page display table
	@RequestMapping(value = "/current", method = RequestMethod.GET)
	public ModelAndView showCurrentMaintenance() {

		ModelAndView mav = new ModelAndView("maintenance-current");
		List<Booking> bookingList = bService.currentBookingbyAdmin();
		mav.addObject("bookingList", bookingList);
		if (bookingList.size() == 0)
			mav.addObject("noRecord", "No Booking Record");
		return mav;
	}

	// Admin maintenance current - delete
	@RequestMapping(value = "current/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteBooking(@PathVariable int id, final RedirectAttributes redirectAttributes)
			throws Exception {

		ModelAndView mav = new ModelAndView("redirect:/admin/booking/current");
		Booking booking = bService.searchByBookingId(id);
		bService.removeBooking(booking);

		// removeBooking(booking);
		String message = "The booking " + booking.getBookingId() + " was successfully deleted.";

		redirectAttributes.addFlashAttribute("message", message);
		return mav;
	}

	// Admin maintenance current - edit
	@RequestMapping(value = "/current/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editBookingPage(@PathVariable String id) {
		ModelAndView mav = new ModelAndView("maintenance-edit");
		Integer bId = Integer.valueOf(id);
		Booking booking = bService.searchByBookingId(bId);
		mav.addObject("booking", booking);
		return mav;
	}

	// Editing current maintenance
	@RequestMapping(params = "update", value = "/current/edit/{id}", method = RequestMethod.POST)
	public ModelAndView editBooking(@ModelAttribute @Valid Booking booking, BindingResult result,
			final RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return new ModelAndView("maintenance-edit");
		}

		ModelAndView mav = new ModelAndView();

		Booking bookingToCheck = bService.findBookingByDate(booking.getFacility().getFacilityId(), booking.getDate());

		if (bookingToCheck != null) {
			String noSuccessMessage = "Sorry! The date you want is already booked.";
			redirectAttributes.addFlashAttribute("noSuccessMessage", noSuccessMessage);
		}

		else {
			bService.updateBooking(booking);
			String successMessage = "Your booking date has been updated.";
			redirectAttributes.addFlashAttribute("successMessage", successMessage);
		}

		mav.setViewName("redirect:/admin/booking/current");

		return mav;
	}

	// Canceling current booking edit
	@RequestMapping(params = "cancel", value = "/current/edit/{id}", method = RequestMethod.POST)
	public ModelAndView cancelEditBooking() {
		ModelAndView mav = new ModelAndView("redirect:/admin/booking/current");
		return mav;
	}

	/* Search Function - For maintenance bookings history - admin */
	@RequestMapping(params = "search", value = "/history", method = RequestMethod.GET)
	public ModelAndView showMaintenanceHistory(HttpServletRequest request, final RedirectAttributes redirectAttributes)
			throws ParseException {
		ModelAndView mav = new ModelAndView("maintenance-history");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = request.getParameter("startdate");
		String endDate = request.getParameter("enddate");

		if (startDate.equals("") && endDate.equals("")) {
			mav.setViewName("redirect:/admin/booking/history");
			return mav;
		} else if (startDate.equals("") || endDate.equals("")) {
			String message = "Please key in both StartDate & EndDate!";
			redirectAttributes.addFlashAttribute("message", message);
			mav.setViewName("redirect:/admin/booking/history");
			return mav;
		}

		Date StartDate = formatter.parse(startDate);
		Date EndDate = formatter.parse(endDate);
		if (StartDate.after(EndDate)) {
			String message = "The StartDate should before EndDate!";
			redirectAttributes.addFlashAttribute("message", message);
			mav.setViewName("redirect:/admin/booking/history");
		} else if (EndDate.after(new Date())) {
			String message = "The EndDate should before today!";
			redirectAttributes.addFlashAttribute("message", message);
			mav.setViewName("redirect:/admin/booking/history");
		} else {
			ArrayList<Booking> bookingList = bService.searchBookingsByPeriod("A", StartDate, EndDate);
			mav.addObject("bookingList", bookingList);
			if (bookingList.size() == 0)
				mav.addObject("noRecord", "No Booking Record");
		}
		return mav;
	}
}
