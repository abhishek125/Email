package com.abhi.app.controller;


import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.abhi.app.model.Profile;
import com.abhi.app.service.ProfileDaoService;

@Controller
public class Login  {
	
	@Autowired
	ProfileDaoService profileDaoService;
	

/*@InitBinder
public void initBinder(WebDataBinder webDataBinder)
{
	webDataBinder.setDisallowedFields(new String[]{"confirm"});
}
*/	
	
@RequestMapping(value={"/"},method=RequestMethod.GET)
public String loginPage()
{
	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	if(!(authentication instanceof AnonymousAuthenticationToken))
		return "redirect:/inbox";
	return "login";
}

@RequestMapping(value="/signup", method=RequestMethod.POST)
public ModelAndView signUpPage(@ModelAttribute("profile") Profile profile , @RequestParam("confirm") String confirm  ) throws ParseException 
{
	profile.setEmail(profile.getEmail().toLowerCase());
	ModelAndView modelAndView=new ModelAndView("login");
	if(profile.getPassword().length()<8)
	{
		modelAndView.addObject("message","pasword length too short");
		return modelAndView;
	}
	boolean created=false;
	created=profileDaoService.saveProfile(profile,confirm);
	String result=created==true?"account created you can now login":"account creation error please try again";
	modelAndView.addObject("message",result);
	return modelAndView;
}
@RequestMapping(value="/register",method=RequestMethod.GET)
public String registerPage()
{
	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	if(!(authentication instanceof AnonymousAuthenticationToken))
		return "redirect:/inbox";
	return "register";
}
@RequestMapping(value="/forgot",method=RequestMethod.GET)
public String forgotPage()
{
	return "forgot";
}

/* redirctions */

@RequestMapping(value="/signup", method=RequestMethod.GET)
public String redirect(){
return "redirect:/";	
}

}
