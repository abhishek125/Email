package com.abhi.app.Dao;

import com.abhi.app.model.Profile;

public interface ProfileDao {
	public void save(Profile p);
	public Profile findProfileByMail(String mail);
	public Profile getProfileById(String id);
	public int updateProfile(Profile profile, String loggedInUserId,boolean isNewPasswordSet);
}
