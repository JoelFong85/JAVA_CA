package edu.iss.cab.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.iss.cab.model.Facility;
import edu.iss.cab.model.User;
import edu.iss.cab.service.FacilityService;
import edu.iss.cab.service.UserService;

@Controller
public class CommonController {

	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

	@Autowired
	private UserService uService;

	@Autowired
	private FacilityService fService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login(Model model) {
		logger.info("Home Page.");

		model.addAttribute("user", new User());
		return "login";
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ModelAndView authenticate(@ModelAttribute User user, HttpSession session, BindingResult result) {
		ModelAndView mav = new ModelAndView("login");
		if (result.hasErrors()) {
			return mav;
		}
		String errorNullMessage = "Invalid User Name and Password!";
		UserSession us = new UserSession();
		if (user.getUserName() != null && user.getPassword() != null) {
			User u = uService.authenticate(user.getUserName(), user.getPassword());
			// If user is not found, return login page
			if (u == null) {
				session.setAttribute("errorNullMessage", errorNullMessage);
				return mav;
			} else {
				us.setUser(u);
				// PUT CODE FOR SETTING SESSION ID
				us.setSessionId(session.getId());
				mav = new ModelAndView("redirect:/facilities");
			}
		} else {
			session.setAttribute("errorNullMessage", errorNullMessage);
			return mav;
		}
		session.setAttribute("USERSESSION", us);
		return mav;
	}

	@RequestMapping(value = "/facilities", method = RequestMethod.GET)
	public ModelAndView showFacilities() {
		ModelAndView mav = new ModelAndView("facility-book");
		List<Facility> facilityList = fService.findAllFacilitiesWithStatus();
		mav.addObject("facilityList", facilityList);
		return mav;
	}

	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	/* Search Function - For Searching to book facility */
	@RequestMapping(params = "search", value = "/facilities", method = RequestMethod.GET)
	public ModelAndView showSearchFacilities(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("facility-book");
		String content = request.getParameter("searchcontent");
		List<Facility> facilityList = fService.searchFacilitiesByName(content);
		mav.addObject("facilityList", facilityList);
		return mav;

	}
}
