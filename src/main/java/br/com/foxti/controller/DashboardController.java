package br.com.foxti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashboardController {
	
	@GetMapping("/")
	public ModelAndView dashboard() {
		return new ModelAndView("Dashboard");
	}

}
