package com.abhi.app.service;

import java.io.File;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class Utils {
	public Date newDate(String myDate, String oldFormat, String newFormat) throws ParseException {
		Date date = new SimpleDateFormat(oldFormat).parse(myDate);//changing string date to date object with same date
		DateFormat format = new SimpleDateFormat(newFormat);//our desired format of date
		String string = format.format(date);//getting the string of our date in desired date format
		Date newDate = format.parse(string);//changing our string to date object
		return newDate;
	}
	public String DateToString(Date date,String format )
	{
		 SimpleDateFormat sdf = new SimpleDateFormat(format);
		 String stringDate = sdf.format(date);
		 return stringDate;
	}
	public String nextUniqueId() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}
	public String nextUniqueMessageId() {
		return nextUniqueId();		
	}
	public Date currentDate(String dateFormat) throws ParseException
	{
		DateFormat df = new SimpleDateFormat(dateFormat);
		String string = df.format(new Date());
		Date currentDate = df.parse(string);
		return currentDate;

	}
	public String fileUpload(MultipartFile[] files)
	{
		String listOfFileNames="";
		try {
            String filePath="c:\\uploads\\";
            for (int i=0;i<files.length;i++)
            {	String fileName=files[i].getOriginalFilename();
            	String random=nextUniqueId();
            	String temp=filePath+random+fileName;
                files[i].transferTo(new File(temp));  // Transfer the content of each file to  the file Path (i.e. c:\temp\kk)
                listOfFileNames+="/uploads/"+random+fileName+";" ;
            }
        } catch (Exception e) {
            return "";
        }
		
		return   listOfFileNames.equals("")==true?listOfFileNames:listOfFileNames.substring(0, listOfFileNames.length()-1);//excluding last semicolon from names list if list is not empty
	}
}
