package com.ty.springmvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ty.springmvc.dto.User;
import com.ty.springmvc.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService service;

	@RequestMapping("/login")
	public ModelAndView loginUser() {
		ModelAndView mv = new ModelAndView("login.jsp");
		mv.addObject("userLogin");
		return mv;
	}

	@RequestMapping("/register")
	public ModelAndView registerUser() {
		ModelAndView mv = new ModelAndView("register.jsp");
		mv.addObject("userRegister", new User());
		return mv;
	}

	@PostMapping("/saveuser")
	public ModelAndView saveUser(@ModelAttribute User user) {

		service.saveUser(user);

		ModelAndView mv = new ModelAndView("welcome.jsp");
		mv.addObject("welcome");
		return mv;
	}

	@PostMapping("/validateuser")
	public ModelAndView validateUser(HttpServletRequest req) {

		User u = service.validateUser(req.getParameter("email"), req.getParameter("psw"));

		if (u != null) {
			HttpSession httpSession = req.getSession();
			httpSession.setAttribute("user", u.getName());
			
			ModelAndView mv = new ModelAndView("menu.jsp");
			mv.addObject("welcome", u.getName() + " has logged in");
			return mv;
		} else {
			ModelAndView mv = new ModelAndView("welcome.jsp");
			mv.addObject("welcome");
			return mv;
		}

	}
}
