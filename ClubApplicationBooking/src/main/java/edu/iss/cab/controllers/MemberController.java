package edu.iss.cab.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import edu.iss.cab.model.User;
import edu.iss.cab.service.BookingService;
import edu.iss.cab.service.FacilityService;
import edu.iss.cab.service.UserService;
import edu.iss.cab.validator.BookingValidator;
import edu.iss.cab.validator.UserValidator;

@RequestMapping(value = "/member")
@Controller
public class MemberController {
	@Autowired
	private FacilityService fService;

	@Autowired
	private BookingService bService;

	@Autowired
	private UserService uService;

	@Autowired
	private BookingValidator bValidator;

	@Autowired
	private UserValidator uValidator;

	@InitBinder("booking")
	private void initBookingBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		binder.addValidators(bValidator);
	}

	@InitBinder("user")
	private void initMemberBinder(WebDataBinder binder) {
		binder.addValidators(uValidator);
	}

	@RequestMapping(value = "/booking/{id}", method = RequestMethod.GET)
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
	@RequestMapping(params = "book", value = "booking/maintenance/book", method = RequestMethod.POST)
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
			String successMessage = "Your Facility with " + booking.getBookingId() + " was successfully booked.";
			redirectAttributes.addFlashAttribute("message", successMessage);
		}
		mav.setViewName("redirect:/facilities");
		return mav;
	}

	@RequestMapping(params = "cancel", value = "/booking/maintenance/book", method = RequestMethod.POST)
	public String cancelNewBooking() {
		return "redirect:/facilities";
	}

	// View Current Booking
	@RequestMapping(value = "/booking/current", method = RequestMethod.GET)
	public ModelAndView memberCurrentBooking(HttpSession session) {
		UserSession us = (UserSession) session.getAttribute("USERSESSION");
		ModelAndView mav = new ModelAndView("/member-booking-current");
		if (us.getSessionId() != null) {
			Integer userId = us.getUser().getUserId();
			List<Booking> bookingList = new ArrayList<Booking>();
			bookingList = bService.currentBookingbyMem(userId);
			mav.addObject("bookingList", bookingList);
			if (bookingList.size() == 0)
				mav.addObject("noRecord", "No Booking Record");
		}
		return mav;
	}

	// Delete Current Booking
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteMemberBooking(@PathVariable String id, final RedirectAttributes redirectattributes) {
		ModelAndView mav = new ModelAndView("redirect:/member/booking/current");
		Integer bid = Integer.parseInt(id);
		Booking booking = bService.findBooking(bid);

		// bService.removebooking(booking);
		bService.removeBooking(booking);

		String message = "Booking Id: " + booking.getBookingId() + " Facility "
				+ booking.getFacility().getFacilityName() + " was successfully cancelled";
		redirectattributes.addFlashAttribute("message", message);
		return mav;
	}

	// View Booking History
	@RequestMapping(value = "/booking/history", method = RequestMethod.GET)
	public ModelAndView memberBookingHistory(HttpSession session) {
		UserSession us = (UserSession) session.getAttribute("USERSESSION");
		ModelAndView mav = new ModelAndView("member-booking-history");
		if (us.getSessionId() != null) {
			Integer userId = us.getUser().getUserId();
			List<Booking> bookingList = bService.historyBookingbyMem(userId);
			mav.addObject("bookingList", bookingList);
			if (bookingList.size() == 0)
				mav.addObject("noRecord", "No Booking Record");
		}
		return mav;
	}

	// Edit Current Booking
	@RequestMapping(value = "/booking/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editMemberBookingPage(@PathVariable String id) {
		ModelAndView mav = new ModelAndView("member-booking-edit");
		Integer bid = Integer.parseInt(id);
		Booking booking = bService.findBooking(bid);
		mav.addObject("booking", booking);
		return mav;
	}

	@RequestMapping(params = "update", value = "/booking/edit/{id}", method = RequestMethod.POST)
	public ModelAndView editMemberBooking(@ModelAttribute @Valid Booking booking, BindingResult result,
			final RedirectAttributes redirectattributes) {
		if (result.hasErrors()) {
			return new ModelAndView("member-booking-edit");
		}

		ModelAndView mav = new ModelAndView();

		Booking bookingToCheck = bService.findBookingByDate(booking.getFacility().getFacilityId(), booking.getDate());

		if (bookingToCheck != null) {
			String noSuccessMessage = "Sorry! The date you want is already booked.";
			redirectattributes.addFlashAttribute("noSuccessMessage", noSuccessMessage);
		}

		else {
			bService.updateBooking(booking);
			String successMessage = "Your booking date has been updated.";
			redirectattributes.addFlashAttribute("successMessage", successMessage);
		}

		mav.setViewName("redirect:/member/booking/current");

		return mav;
	}

	@RequestMapping(params = "cancel", value = "/booking/edit/{id}", method = RequestMethod.POST)
	public String cancelEditMember() {
		return "redirect:/member/booking/current";
	}

	// View Profile
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView profilePage(HttpSession session) {
		UserSession us = (UserSession) session.getAttribute("USERSESSION");
		User user = uService.findUser(us.getUser().getUserId());
		ModelAndView mav = new ModelAndView("member-profile", "user", user);
		return mav;
	}

	// Edit Profile
	@RequestMapping(value = "/profile/edit/{id}", method = RequestMethod.POST)
	public ModelAndView editProfilePage(@PathVariable int id) {
		ModelAndView mav = new ModelAndView("member-profile-edit");
		User user = uService.findUser(id);
		mav.addObject("user", user);
		return mav;
	}

	// Actual Edit Profile
	@RequestMapping(params = "update", value = "/profile/edit/{id}", method = RequestMethod.POST)
	public ModelAndView updateProfile(@ModelAttribute @Valid User user, BindingResult result, @PathVariable int id,
			final RedirectAttributes redirectAttributes) {

		if (result.hasErrors())
			return new ModelAndView("member-profile-edit");

		ModelAndView mav = new ModelAndView();
		String message="";

		// Checking DB whether updated userName already exists or not
		String newName = user.getUserName();
		String oldName = uService.findUser(id).getUserName();

		if (!newName.equals(oldName)) {
			User newUser = uService.findUserByName(user.getUserName());
			if (newUser != null) {
				message = "Sorry!!! You cannot update with this user name! Try again!";
				redirectAttributes.addFlashAttribute("message", message);
				mav.setViewName("redirect:/member/profile");
				return mav;
			}
			message= user.getUserName()+" personal information was successfully updated";
			redirectAttributes.addFlashAttribute("message", message);
		}

		user.setUserId(id);
		uService.changeUser(user);
		mav.setViewName("redirect:/member/profile");
		redirectAttributes.addFlashAttribute("message", message);
		return mav;
	}

	// Cancel Edit Profile
	@RequestMapping(params = "cancel", value = "/profile/edit/{id}", method = RequestMethod.POST)
	public String cancelProfileEdit() {
		return "redirect:/member/profile";
	}

	// Member Register Request
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView registerMemberPage() {
		return new ModelAndView("member-register", "user", new User());
	}

	// Member Register
	@RequestMapping(params = "register", value = "/register", method = RequestMethod.POST)
	public ModelAndView registerMember(@ModelAttribute @Valid User user, BindingResult result, HttpSession session,
			final RedirectAttributes redirectAttributes) {

		if (result.hasErrors())
			return new ModelAndView("member-register");

		ModelAndView mav = new ModelAndView();
		user.setRole("member");

		String message = "";
		User currentUser = uService.findUserByName(user.getUserName());
		if (currentUser != null) {
			message = "Sorry!!! You cannot create with this user name!";
			redirectAttributes.addFlashAttribute("message", message);
			mav.setViewName("redirect:/member/register");
			return mav;
		}

		uService.createUser(user);
		User newUser = uService.findUserByName(user.getUserName());
		message = "Member Id: " + newUser.getUserId() + " and Member Name: " + newUser.getUserName()
				+ " was signed up.";
		redirectAttributes.addFlashAttribute("message", message);

		UserSession us = new UserSession();
		us.setUser(user);
		us.setSessionId(session.getId());
		session.setAttribute("USERSESSION", us);
		mav.setViewName("redirect:/facilities");
		return mav;
	}

	// Cancel Member Register
	@RequestMapping(params = "cancel", value = "/register", method = RequestMethod.POST)
	public String cancelRegister() {
		return "redirect:/";
	}

	/* Search Function - For maintenance bookings history - admin */
	@RequestMapping(params = "search", value = "/booking/history", method = RequestMethod.GET)
	public ModelAndView showMaintenanceHistory(HttpServletRequest request, final RedirectAttributes redirectAttributes,
			HttpSession session) throws ParseException {
		ModelAndView mav = new ModelAndView("member-booking-history");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = request.getParameter("startdate");
		String endDate = request.getParameter("enddate");

		if (startDate.equals("") && endDate.equals("")) {
			mav.setViewName("redirect:/member/booking/history");
			return mav;
		} else if (startDate.equals("") || endDate.equals("")) {
			String message = "Please key in both StartDate & EndDate!";
			redirectAttributes.addFlashAttribute("message", message);
			mav.setViewName("redirect:/member/booking/history");
			return mav;
		}

		Date StartDate = formatter.parse(startDate);
		Date EndDate = formatter.parse(endDate);
		if (StartDate.after(EndDate)) {
			String message = "The StartDate should before EndDate!";
			redirectAttributes.addFlashAttribute("message", message);
			mav.setViewName("redirect:/member/booking/history");
		} else if (EndDate.after(new Date())) {
			String message = "The EndDate should before today!";
			redirectAttributes.addFlashAttribute("message", message);
			mav.setViewName("redirect:/member/booking/history");
		} else {
			UserSession us = (UserSession) session.getAttribute("USERSESSION");
			Integer id = us.getUser().getUserId();
			ArrayList<Booking> bookingList = bService.findMemberBookingByPeriod(id, StartDate, EndDate);
			mav.addObject("bookingList", bookingList);
			if (bookingList.size() == 0)
				mav.addObject("noRecord", "No Booking Record");
		}
		return mav;
	}
}
