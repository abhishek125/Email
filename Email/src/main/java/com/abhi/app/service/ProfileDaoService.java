package com.abhi.app.service;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhi.app.Dao.ProfileDao;
import com.abhi.app.model.Profile;

@Service
public class ProfileDaoService {

	@Autowired
	private ProfileDao profileDao;
	@Autowired
	private Utils utils;
	public boolean saveProfile(Profile profile , String confirm) throws ParseException {
		Profile exist=profileDao.findProfileByMail(profile.getEmail());
		if (exist!=null  || !confirm.equals(profile.getPassword()))
			return false;
		profile.setId(utils.nextUniqueId());
		profile.setRole("ROLE_USER");
		profile.setProfilePic("/uploads/avatar.png");
		profile.setCompany("none");
		profileDao.save(profile);
		return true;
	}
	
	public boolean updateProfile(Profile profile, String confirm, String loggedInUserId)
	{
		boolean isNewPasswordSet=true;
		Profile oldProfile=profileDao.getProfileById(loggedInUserId);
		if((profile.getPassword()!="" || confirm!="") && !profile.getPassword().equals(confirm))
			return false;
		if(profile.getPassword().equals(""))
		{		isNewPasswordSet=false;
			profile.setPassword(oldProfile.getPassword());
		}
		if(profile.getProfilePic().equals(""))
		{
			profile.setProfilePic(oldProfile.getProfilePic());
		}
		
		return profileDao.updateProfile(profile, loggedInUserId,isNewPasswordSet)==1?true:false;
	}


	public Profile getLoggedInUserProfile(String loggedInUserId) throws ParseException {
		Profile profile=profileDao.getProfileById(loggedInUserId);
		return profile;
	}
	
}
