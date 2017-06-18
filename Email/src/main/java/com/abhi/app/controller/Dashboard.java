package com.abhi.app.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.abhi.app.model.Message;
import com.abhi.app.model.Profile;
import com.abhi.app.service.MessageDaoService;
import com.abhi.app.service.MyUserDetailsService;
import com.abhi.app.service.ProfileDaoService;
import com.abhi.app.service.Utils;
@Controller
public class Dashboard {
	
	@Autowired 
	MessageDaoService messageDaoService;
	@Autowired
	MyUserDetailsService myUserDetailsService;
	@Autowired
	ProfileDaoService profileDaoService;
	@Autowired
	Utils utils;
	ArrayList<Profile> profiles=new ArrayList<Profile>();
	ArrayList<Message> messages=new ArrayList<Message>();
	
	
	@ModelAttribute
	public void addMessageCommand(Model model) throws ParseException
	{
		
		Profile profile=profileDaoService.getLoggedInUserProfile(myUserDetailsService.getLoggedInUserId());
		model.addAttribute("profile",profile);
		model.addAttribute("inboxCount",messageDaoService.getInboxCount(Message.Status.INBOX,myUserDetailsService.getLoggedInUserId()));
		model.addAttribute("outboxCount",messageDaoService.getOutboxCount(Message.Status.OUTBOX,myUserDetailsService.getLoggedInUserId()));
		model.addAttribute("spamCount",messageDaoService.getOtherCount(Message.Status.SPAM, myUserDetailsService.getLoggedInUserId()));
		model.addAttribute("trashCount",messageDaoService.getOtherCount(Message.Status.DELETED, myUserDetailsService.getLoggedInUserId()));
		model.addAttribute("flaggedCount",messageDaoService.getOtherCount(Message.Status.FLAGGED, myUserDetailsService.getLoggedInUserId()));
		
	}
	
	
	@RequestMapping(value="/inbox",method=RequestMethod.GET)
	public ModelAndView inboxPage()
	{
		List<Object[]> list= messageDaoService.getInbox(myUserDetailsService.getLoggedInUserId());
		return getSeparatedList(list,"inbox");
	}

	@RequestMapping(value="/outbox",method=RequestMethod.GET)
	public ModelAndView outboxPage()
	{
		List<Object[]> list= messageDaoService.getOutbox(myUserDetailsService.getLoggedInUserId());
		return getSeparatedList(list,"outbox");
	}
	@RequestMapping(value="/trash",method=RequestMethod.GET)
	public ModelAndView trashPage()
	{
		List<Object[]> list= messageDaoService.getTrash(myUserDetailsService.getLoggedInUserId());
		return getSeparatedList(list,"trash");
	}
	@RequestMapping(value="/flagged",method=RequestMethod.GET)
	public ModelAndView flaggedPage()
	{
		List<Object[]> list= messageDaoService.getFlagged(myUserDetailsService.getLoggedInUserId());
		return getSeparatedList(list,"flagged");
	}
	@RequestMapping(value="/spam",method=RequestMethod.GET)
	public ModelAndView spamPage()
	{
		List<Object[]> list= messageDaoService.getSpam(myUserDetailsService.getLoggedInUserId());
		return getSeparatedList(list,"spam");
	}
	@RequestMapping(value="/profileupdate",method=RequestMethod.POST)
	public String profileUpdatePage(@ModelAttribute("profile") Profile profile,@RequestParam("confirm") String confirm, Model model,
			@RequestParam("file") MultipartFile file,RedirectAttributes redir)
	{
		
		profile.setProfilePic(file.getOriginalFilename());
		if(!file.isEmpty())
		{
			
			MultipartFile[] files=new MultipartFile[1];
			files[0]=file;
			profile.setProfilePic((utils.fileUpload(files)));
		}
		String updated=profileDaoService.updateProfile(profile,confirm,myUserDetailsService.getLoggedInUserId())==true?"profile updated successfully":"no changes made to your profile";
		redir.addFlashAttribute("updated",updated);
		return "redirect:profile";
	}

	@RequestMapping(value="/profile",method=RequestMethod.GET)
	public String profilePage(Model model) throws ParseException
	{
		return "profile";
	}
	
	private ModelAndView getSeparatedList(List<Object[]> list, String modelName) {
		ArrayList<Profile> profiles=new ArrayList<Profile>();
		ArrayList<Message> messages=new ArrayList<Message>();
		messageDaoService.process(list,messages,profiles);
		ModelAndView model=new ModelAndView(modelName);
		model.addObject("messages",messages );
		model.addObject("profiles",profiles);
		return model;
	}
	
	/*rediredction*/
	@RequestMapping(value="/profileupdate",method=RequestMethod.GET)
	public String redirect(){
		return "redirect:/profile";
	}
}
