package com.abhi.app.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.abhi.app.model.Message;
import com.abhi.app.service.MessageDaoService;
import com.abhi.app.service.MyUserDetailsService;
import com.abhi.app.service.Utils;
@Controller
public class AjaxController  {
	@Autowired
	private Utils utils;
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	@Autowired
	private MessageDaoService messageDaoService;
	
	@RequestMapping(value="/sendmessage",method=RequestMethod.POST,consumes = {"multipart/form-data"})
	@ResponseBody
	public String sendMessage(@RequestPart("message") Message message,
	        @RequestPart("files[]") MultipartFile[] files) throws ParseException
	{
		message.setFiles((utils.fileUpload(files)));
		message.setSenderId(myUserDetailsService.getLoggedInUserId());
		message.setDateSent(utils.currentDate("yyyy-MM-dd"));
		boolean sent= messageDaoService.saveMessage(message);
		return sent==true?"message sent":"message not sent";
	}
	@RequestMapping(value="/updatestatus",method=RequestMethod.POST)
	@ResponseBody
	public String updateStatus(@RequestParam(value="myArray[]") List<String> myArray, @RequestParam("status") String status ){
		boolean res=false;
		if(status.equals("READ")||status.equals("UNREAD"))
			res=messageDaoService.readUpdate(myUserDetailsService.getLoggedInUserId(), myArray, status);
		else
			res=messageDaoService.statusUpdate(myUserDetailsService.getLoggedInUserId(), myArray, status);
		return res==false?"no changes were made":"updated succesfully";
	}
	
	
	@RequestMapping(value="/saveimage",method=RequestMethod.POST,consumes = {"multipart/form-data"})
	@ResponseBody
	public String saveImage(@RequestParam("files[]") MultipartFile[] files) throws ParseException
	{
		
		return(utils.fileUpload(files));
		
	}
	
	
	
	
	
	
	
}
