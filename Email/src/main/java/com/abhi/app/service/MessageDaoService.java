package com.abhi.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.abhi.app.Dao.MessageDao;
import com.abhi.app.Dao.ProfileDao;
import com.abhi.app.model.Message;
import com.abhi.app.model.Message.Read;
import com.abhi.app.model.Profile;

public class MessageDaoService {

	@Autowired
	private MessageDao messageDao;
	@Autowired
	private ProfileDao profileDao;
	@Autowired
	private Utils utils;
	
	public int getInboxCount(Message.Status status,String loggedInUserId)
	{
		return messageDao.getInboxCount(status,loggedInUserId);
	}
	public int getOutboxCount(Message.Status status,String loggedInUserId)
	{
		return messageDao.getOutboxCount(status,loggedInUserId);
	}
	public int getOtherCount(Message.Status status,String loggedInUserId)
	{
		return messageDao.getOtherCount(status,loggedInUserId);
	}
	public boolean saveMessage(Message message)
	{
		String email=message.getReceiverId();//fetch receiver email of message object
		Profile id=profileDao.findProfileByMail(email);
		if(id==null || id.getId().equals(message.getSenderId()))//either receiver mail is wrong or sender is same as areceiver 
			return false;
		message.setReceiverId(id.getId());
		message.setMessageId(utils.nextUniqueMessageId());
		message.setSenderStatus(Message.Status.OUTBOX);
		message.setReceiverStatus(Message.Status.INBOX);
		message.setReceiverRead(Read.UNREAD);
		message.setSenderRead(Read.READ);
		messageDao.saveMessage(message);
		return true;
	}

	public boolean statusUpdate(String loggedInUserId, List<String> messageIdList, String action)
	{
		int res1=0,res2=0;
		for (Message.Status status : Message.Status.values()) {
	        if (status.name().equals(action)) {
	            res1= messageDao.statusUpdateForSender(loggedInUserId, messageIdList, status);
	            res2=messageDao.statusUpdateForReceiver(loggedInUserId, messageIdList, status);
	        }
	    }
		if(action.equals("UNDELETE") || action.equals("UNSPAM") || action.equals("UNFLAGGED"))
		{
			res1= messageDao.statusUpdateForReceiver(loggedInUserId, messageIdList, Message.Status.INBOX) ;
			res2=messageDao.statusUpdateForSender(loggedInUserId, messageIdList, Message.Status.OUTBOX);
		}
		return (res1>0||res2>0)?true:false;
	}
	
	public boolean readUpdate(String loggedInUserId, List<String> messageIdList, String action)
	{
		for (Message.Read read : Message.Read.values()) {
	        if (read.name().equals(action)) 
	            return messageDao.readUpdate(loggedInUserId, messageIdList, read);
	    }
		return false;	
	}
	public ArrayList<Object[]> getInbox(String loggedInUserId) {
		return  messageDao.getInbox(loggedInUserId);
	}
	public ArrayList<Object[]> getOutbox(String loggedInUserId) {
		return messageDao.getOutbox(loggedInUserId);
	}
	public ArrayList<Object[]> getTrash(String loggedInUserId) {
		return messageDao.getTrash(loggedInUserId);
	}
	public ArrayList<Object[]> getSpam(String loggedInUserId) {
		return messageDao.getSpam(loggedInUserId);
	}
	public ArrayList<Object[]> getFlagged(String loggedInUserId) {
		return messageDao.getFlagged(loggedInUserId);
	}
	public void process(List<Object[]> list, ArrayList<Message> messages,ArrayList<Profile> profiles){
		for(int i=0;i<list.size();i++){
			   Object obj[]=list.get(i);
			   for(int j=0;j<obj.length;j++){
				   if(j%2==0)
				   profiles.add((Profile)obj[j]);
				   else
				   messages.add((Message)obj[j]);
			   }    
			}
		
	}
	
}
