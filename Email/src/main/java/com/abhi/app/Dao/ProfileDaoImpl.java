package com.abhi.app.Dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.abhi.app.model.Profile;

public class ProfileDaoImpl implements ProfileDao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	
	public void save(Profile p) {
		p.setPassword(passwordEncoder.encode(p.getPassword()));
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(p);
		tx.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	public Profile findProfileByMail(String mail) {

		List<Profile> profiles = new ArrayList<Profile>();
		Session session=sessionFactory.openSession();
		profiles = session.createQuery("from Profile where email=:mail")
			.setParameter("mail", mail).getResultList();
		session.close();
		return (profiles.size()>0)?profiles.get(0):null;

	}
	
	public Profile getProfileById(String id)
	{
		Session session=sessionFactory.openSession();
		Profile profile=(Profile)session.createQuery("from Profile p where p.id=:id")
		.setParameter("id", id)
		.getResultList().get(0);
		session.close();
		return profile;
	}
	public int updateProfile(Profile profile, String loggedInUserId,boolean isNewPasswordSet)
	{
		if(isNewPasswordSet)
		profile.setPassword(passwordEncoder.encode(profile.getPassword()));
		Session session=sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		@SuppressWarnings("rawtypes")
		Query query= session.createQuery("update Profile p set p.firstname=:firstname, p.lastname=:lastname, "
				+ " p.password=:password, p.company=:company, p.profilePic=:pic,  p.dob=:dob where p.id=:id" );
		query.setParameter("id", loggedInUserId);
		query.setParameter("firstname", profile.getFirstname());
		query.setParameter("lastname", profile.getLastname());
		query.setParameter("dob", profile.getDob());
		query.setParameter("company", profile.getCompany());
		query.setParameter("password", profile.getPassword());
		query.setParameter("pic", profile.getProfilePic());
		int res = query.executeUpdate();
		tx.commit();
		session.close();
		return res;
		
		 
	}
}
