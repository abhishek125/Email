package com.abhi.app.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="profile")
public class Profile {
private String id;
@NotNull
@Size(min=5,max=60)
private String email;
@NotNull
@Size(min=2,max=45)
private String firstname;
@NotNull
@Size(min=2,max=45)
private String lastname;
@NotNull
@Size(min=8, max=65)
private String password;
@NotNull
private String role;
private String company;
@DateTimeFormat(pattern = "yyyy-MM-dd")
private Date dob;
private String profilePic;

public Profile(){
	
}

@Id
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getProfilePic() {
	return profilePic;
}

public void setProfilePic(String profilePic) {
	this.profilePic = profilePic;
}

public String getCompany() {
	return company;
}

public void setCompany(String company) {
	this.company = company;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}

public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getFirstname() {
	return firstname;
}
public void setFirstname(String firstname) {
	this.firstname = firstname;
}
public String getLastname() {
	return lastname;
}
public void setLastname(String lastname) {
	this.lastname = lastname;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public Date getDob() {
	return dob;
}

public void setDob(Date dob) {
	this.dob = dob;
}


}
