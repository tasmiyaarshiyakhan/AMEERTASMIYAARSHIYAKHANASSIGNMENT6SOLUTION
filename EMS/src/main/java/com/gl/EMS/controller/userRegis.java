package com.gl.EMS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gl.EMS.service.UserRegistrationDto;
import com.gl.EMS.service.UserService;




	@Controller
	@RequestMapping("/registration")
	public class userRegis {

		private UserService userService;

		public userRegis(UserService userService) {
			super();
			this.userService = userService;
		}
		
		@ModelAttribute("user")
	    public UserRegistrationDto userRegistrationDto() {
	        return new UserRegistrationDto();
	    }
		
		@GetMapping
		public String showRegistrationForm() {
			return "registration";
		}
		
		@PostMapping
		public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
			userService.save(registrationDto);
			return "redirect:/registration?success";
		}
}
