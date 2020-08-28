package com.events.controllers;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.events.models.Location;
import com.events.models.User;
import com.events.services.LocationService;
import com.events.services.UserService;
import com.events.validators.UserValidator;

@Controller
public class UserController {
	@Autowired
	private  UserService userService;
	@Autowired
	private  UserValidator userValidator;
	@Autowired
	private LocationService locationService;


//	Index
	@RequestMapping("/")
	public String newUser(@ModelAttribute("user") User user,Model viewModel) {
		viewModel.addAttribute("locations", locationService.allLocations());
		return "index.jsp";
	}
//Register New User
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String createUser(@Valid @ModelAttribute("user") User user, 
			BindingResult result, HttpSession session,Model viewModel) {
		
		userValidator.validate(user, result);
		if (result.hasErrors()) {
			viewModel.addAttribute("locations", locationService.allLocations());
			return "index.jsp";
		}
		user = userService.registerUser(user);
		session.setAttribute("userId", user.getId());
		return "redirect:/events";

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("email") String email, @RequestParam("password") String password,
			Model model,
			HttpSession session,RedirectAttributes redirect) {

		User user = userService.autheticateUser(email, password);
		if (user != null) {
			// if authenticated save user in session
			session.setAttribute("userId", user.getId());
			return "redirect:/events";
		} else {
			redirect.addFlashAttribute("error", "Invalid Email/Password");
			return "redirect:/";
		}

	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

}
