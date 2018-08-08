package edu.iss.cab.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
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

import edu.iss.cab.exception.UserNotFoundException;
import edu.iss.cab.model.User;
import edu.iss.cab.service.UserService;
import edu.iss.cab.validator.UserValidator;

@RequestMapping(value = "/admin/user")
@Controller
public class AdminUserController {
	@Autowired
	private UserService uService;

	@Autowired
	private UserValidator uValidator;

	@InitBinder("user")
	private void initAdminBinder(WebDataBinder binder) {
		binder.addValidators(uValidator);
	}

	// Navigating Admin-new Page
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView newAdminPage() {
		ModelAndView mav = new ModelAndView("admin-new", "user", new User());
		mav.addObject("fidlist", uService.findAllAdmin());
		return mav;
	}

	// Creating a new user
	@RequestMapping(params = "create", value = "/create", method = RequestMethod.POST)
	public ModelAndView createNewAdmin(@ModelAttribute @Valid User admin, BindingResult result,
			final RedirectAttributes redirectAttributes) {

		if (result.hasErrors())
			return new ModelAndView("admin-new");

		ModelAndView mav = new ModelAndView();
		String message = "New Admin " + admin.getUserName() + " was successfully created.";

		// Checking DB whether created userName already exists or not
		User user = uService.findUserByName(admin.getUserName());
		if (user != null) {
			message = "Sorry!!! You cannot create with this user name!";
			redirectAttributes.addFlashAttribute("message", message);
			mav.setViewName("redirect:/admin/user/create");
			return mav;
		}
		uService.createUser(admin);
		mav.setViewName("redirect:/admin/user/list");

		redirectAttributes.addFlashAttribute("message", message);
		return mav;
	}

	// Cancel for creating a new admin
	@RequestMapping(params = "cancel", value = "/create", method = RequestMethod.POST)
	public String cancelNewAdmin() {
		return "redirect:/admin/user/list";
	}

	// Displaying the list page
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView adminListPage() {
		ModelAndView mav = new ModelAndView("admin-list");
		List<User> adminList = uService.findAllAdmin();
		mav.addObject("adminList", adminList);
		return mav;
	}

	// Navigating edit page
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editAdminPage(@PathVariable int id) {
		ModelAndView mav = new ModelAndView("admin-edit");
		User admin = uService.findUser(id);
		mav.addObject("user", admin);
		return mav;
	}

	// Canceling edit
	@RequestMapping(params = "cancel", value = "/edit/{id}", method = RequestMethod.POST)
	public String cancelEditAdmin() {
		return "redirect:/admin/user/list";
	}

	// Actual Editing
	@RequestMapping(params = "update", value = "/edit/{id}", method = RequestMethod.POST)
	public ModelAndView editAdmin(@ModelAttribute @Valid User admin, BindingResult result, @PathVariable int id,
			final RedirectAttributes redirectAttributes) throws UserNotFoundException {

		if (result.hasErrors())
			return new ModelAndView("admin-edit");

		ModelAndView mav = new ModelAndView();
		String message = "Admin was successfully updated.";

		// Checking DB whether updated userName already exists or not
		String newName = admin.getUserName();
		String oldName = uService.findUser(id).getUserName();

		if (!newName.equals(oldName)) {

			User user = uService.findUserByName(admin.getUserName());
			if (user != null) {
				message = "Sorry!!! You cannot update with this user name!";
				redirectAttributes.addFlashAttribute("message", message);
				mav.setViewName("redirect:/admin/user/edit/" + id + ".html");
				return mav;
			}
		}

		admin.setUserId(id);
		uService.changeUser(admin);
		mav.setViewName("redirect:/admin/user/list");
		redirectAttributes.addFlashAttribute("message", message);
		return mav;
	}

	// Deleting an admin
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteAdmin(@PathVariable int id, final RedirectAttributes redirectAttributes,
			HttpSession session) throws UserNotFoundException {

		ModelAndView mav = new ModelAndView("redirect:/admin/user/list");
		User admin = uService.findUser(id);

		String message = "The admin " + admin.getUserName() + " was successfully deleted.";
		UserSession us = (UserSession) session.getAttribute("USERSESSION");
		if (admin.getUserName().equals(us.getUser().getUserName())) {
			message = "Deletion for current user cannot be done!";
		} else {
			uService.removeUser(admin);
		}
		redirectAttributes.addFlashAttribute("message", message);
		return mav;
	}

	// View Profile
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView profilePage(HttpSession session) {
		UserSession us = (UserSession) session.getAttribute("USERSESSION");
		User user = uService.findUser(us.getUser().getUserId());

		ModelAndView mav = new ModelAndView("admin-profile", "user", user);
		return mav;
	}

	// Edit Profile
	@RequestMapping(value = "/profile/edit/{id}", method = RequestMethod.POST)
	public ModelAndView editProfilePage(@PathVariable int id) {
		ModelAndView mav = new ModelAndView("admin-profile-edit");
		User user = uService.findUser(id);
		mav.addObject("user", user);
		return mav;
	}

	// Actual Edit Profile
	@RequestMapping(params = "update", value = "/profile/edit/{id}", method = RequestMethod.POST)
	public ModelAndView updateProfile(@ModelAttribute @Valid User user, BindingResult result, @PathVariable int id,
			final RedirectAttributes redirectAttributes) throws UserNotFoundException {

		if (result.hasErrors())
			return new ModelAndView("admin-profile-edit");

		ModelAndView mav = new ModelAndView();
		String message = "Admin was successfully updated.";

		// Checking DB whether updated userName already exists or not
		String newName = user.getUserName();
		String oldName = uService.findUser(id).getUserName();

		if (!newName.equals(oldName)) {
			User newUser = uService.findUserByName(user.getUserName());
			if (newUser != null) {
				message = "Sorry!!! You cannot update with this user name! Try again!";
				redirectAttributes.addFlashAttribute("message", message);
				mav.setViewName("redirect:/admin/user/profile");
				return mav;
			}
		}

		user.setUserId(id);
		uService.changeUser(user);
		mav.setViewName("redirect:/admin/user/profile");
		redirectAttributes.addFlashAttribute("message", message);
		return mav;
	}

	// Cancel Edit Profile
	@RequestMapping(params = "cancel", value = "/profile/edit/{id}", method = RequestMethod.POST)
	public String cancelProfileEdit() {
		return "redirect:/admin/user/profile";
	}
}
