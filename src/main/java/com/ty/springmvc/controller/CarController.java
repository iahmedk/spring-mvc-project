package com.ty.springmvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.ty.springmvc.dto.Car;
import com.ty.springmvc.service.CarService;

@Controller
public class CarController {

	@Autowired
	private CarService carService;

	@GetMapping("/loadCar")
	public ModelAndView loadCar(HttpServletRequest req) {
		HttpSession httpSession = req.getSession();
		String name = (String) httpSession.getAttribute("user");
		if (name != null) {
			ModelAndView mv = new ModelAndView("createCar.jsp");
			mv.addObject("loadCar", new Car());
			return mv;
		} else {
			ModelAndView mv = new ModelAndView("invalidSession.jsp");
			mv.addObject("invalid", "not an authorized user");
			return mv;
		}
	}

	@GetMapping("/savecar")
	public ModelAndView saveCar(@ModelAttribute Car car) {
		Car c = carService.saveCar(car);

		if (c != null) {
			ModelAndView mv = new ModelAndView("createCar.jsp");
			mv.addObject("loadCar", new Car());
			return mv;
		}
		return null;
	}

	@GetMapping("/viewcar")
	public ModelAndView viewCar(HttpServletRequest req) {
		HttpSession httpSession = req.getSession();
		String name = (String) httpSession.getAttribute("user");
		if (name != null) {
			List<Car> cars = carService.getAllCars();
			ModelAndView mv = new ModelAndView("viewCar.jsp");
			mv.addObject("viewCar", cars);
			return mv;
		} else {
			ModelAndView mv = new ModelAndView("invalidSession.jsp");
			mv.addObject("invalid", "not an authorized user");
			return mv;
		}
	}

	@GetMapping("/editcar")
	public ModelAndView editCar(HttpServletRequest req) {
		HttpSession httpSession = req.getSession();
		String name = (String) httpSession.getAttribute("user");
		if (name != null) {
			List<Car> cars = carService.getAllCars();
			ModelAndView mv = new ModelAndView("editCar.jsp");
			mv.addObject("editCar", cars);
			return mv;
		} else {
			ModelAndView mv = new ModelAndView("invalidSession.jsp");
			mv.addObject("invalid", "not an authorized user");
			return mv;
		}
	}

	@GetMapping("/modifycar")
	public ModelAndView modifyCar(HttpServletRequest req) {
		HttpSession httpSession = req.getSession();
		String name = (String) httpSession.getAttribute("user");
		if (name != null) {
			String id = req.getParameter("id");
			Car c = carService.getCarById(Integer.parseInt(id));

			ModelAndView mv = new ModelAndView("updateCar.jsp");
			mv.addObject("updateCar", c);
			return mv;
		} else {
			ModelAndView mv = new ModelAndView("invalidSession.jsp");
			mv.addObject("invalid", "not an authorized user");
			return mv;
		}
	}

	@GetMapping("/updatecar")
	public ModelAndView updateCar(@ModelAttribute Car car) {
		Car c = carService.updateCar(car);

		ModelAndView mv = new ModelAndView("/viewcar");
		mv.addObject("viewCar", c);
		return mv;
	}

	@GetMapping("/deletecar")
	public ModelAndView deleteCar(HttpServletRequest req) {
		HttpSession httpSession = req.getSession();
		String name = (String) httpSession.getAttribute("user");
		if (name != null) {
			List<Car> cars = carService.getAllCars();
			ModelAndView mv = new ModelAndView("removeCar.jsp");
			mv.addObject("removeCar", cars);
			return mv;
		} else {
			ModelAndView mv = new ModelAndView("invalidSession.jsp");
			mv.addObject("invalid", "not an authorized user");
			return mv;
		}
	}

	@GetMapping("/removecar")
	public ModelAndView removeCar(HttpServletRequest req) {
		String[] id = req.getParameterValues("id");

		carService.removeCar(id);

		ModelAndView mv = new ModelAndView("/viewcar");
		mv.addObject("viewCar", "removed");
		return mv;
	}

	@GetMapping("/logout")
	public ModelAndView logoutSession(HttpServletRequest req) {
		HttpSession httpSession = req.getSession();
		httpSession.invalidate();
		ModelAndView mv = new ModelAndView("login.jsp");
		mv.addObject("welcome");
		return mv;
	}
}
