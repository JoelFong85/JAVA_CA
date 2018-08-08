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

import edu.iss.cab.exception.UserNotFoundException;
import edu.iss.cab.model.User;
import edu.iss.cab.service.UserService;
import edu.iss.cab.validator.UserValidator;

@RequestMapping(value = "/admin/member")
@Controller
public class AdminMemberController {
	@Autowired
	private UserService uService;

	@Autowired
	private UserValidator uValidator;

	@InitBinder("user")
	private void initMemberBinder(WebDataBinder binder) {
		binder.addValidators(uValidator);
	}

	// Navigating member-new Page
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView newMemberPage() {
		ModelAndView mav = new ModelAndView("member-new", "user", new User());
		mav.addObject("fidlist", uService.findAllMember());
		return mav;
	}

	@RequestMapping(params = "create", value = "/create", method = RequestMethod.POST)
	public ModelAndView createNewMember(@ModelAttribute @Valid User member, BindingResult result,
			final RedirectAttributes redirectAttributes) {

		if (result.hasErrors())
			return new ModelAndView("member-new");

		ModelAndView mav = new ModelAndView();
		String message = "New Member " + member.getUserName() + " was successfully created.";

		User user = uService.findUserByName(member.getUserName());
		if (user != null) {
			message = "Sorry!!! You cannot create with this user name!";
			redirectAttributes.addFlashAttribute("message", message);
			mav.setViewName("redirect:/admin/member/create");
			return mav;
		}

		uService.createUser(member);
		mav.setViewName("redirect:/admin/member/list");

		redirectAttributes.addFlashAttribute("message", message);
		return mav;
	}

	@RequestMapping(params = "cancel", value = "/create", method = RequestMethod.POST)
	public String cancelNewMember() {
		return "redirect:/admin/member/list";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView memberListPage() {
		ModelAndView mav = new ModelAndView("member-list");
		List<User> memberList = uService.findAllMember();
		mav.addObject("memberList", memberList);
		return mav;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editMemberPage(@PathVariable int id) {
		ModelAndView mav = new ModelAndView("member-edit");
		User member = uService.findUser(id);
		mav.addObject("user", member);
		return mav;
	}

	@RequestMapping(params = "cancel", value = "/edit/{id}", method = RequestMethod.POST)
	public String cancelEditMember() {
		return "redirect:/admin/member/list";
	}

	@RequestMapping(params = "update", value = "/edit/{id}", method = RequestMethod.POST)
	public ModelAndView editMember(@ModelAttribute @Valid User member, BindingResult result, @PathVariable int id,
			final RedirectAttributes redirectAttributes) throws UserNotFoundException {

		if (result.hasErrors())
			return new ModelAndView("member-edit");

		ModelAndView mav = new ModelAndView("redirect:/admin/member/list");
		String message = "Member was successfully updated.";

		String newName = member.getUserName();
		String oldName = uService.findUser(id).getUserName();

		if (!newName.equals(oldName)) {

			User user = uService.findUserByName(member.getUserName());
			if (user != null) {
				message = "Sorry!!! You cannot update with this user name!";
				redirectAttributes.addFlashAttribute("message", message);
				mav.setViewName("redirect:/admin/user/edit/" + id + ".html");
				return mav;
			}
		}

		member.setUserId(id);
		uService.changeUser(member);

		redirectAttributes.addFlashAttribute("message", message);
		return mav;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteMember(@PathVariable int id, final RedirectAttributes redirectAttributes)
			throws UserNotFoundException {

		ModelAndView mav = new ModelAndView("redirect:/admin/member/list");
		User member = uService.findUser(id);

		uService.removeUser(member);
		String message = "The member " + member.getUserName() + " was successfully deleted.";

		redirectAttributes.addFlashAttribute("message", message);
		return mav;
	}

}
