package com.abhi.app.Dao;

import java.util.ArrayList;
import java.util.List;

import com.abhi.app.model.Message;
import com.abhi.app.model.Message.Status;

public interface MessageDao {
public boolean saveMessage(Message message);
public ArrayList<Object[]> getInbox(String loggedInUserId);
public ArrayList<Object[]> getOutbox(String loggedInUserId);
public ArrayList<Object[]> getTrash(String loggedInUserId);
public ArrayList<Object[]> getFlagged(String loggedInUserId);
public ArrayList<Object[]> getSpam(String loggedInUserId);
public int statusUpdateForReceiver(String loggedInUserId,List<String> messageIdList, Message.Status status );
public int statusUpdateForSender(String loggedInUserId,List<String> messageIdList, Message.Status status );
public boolean readUpdate(String loggedInUserId,List<String> messageIdList, Message.Read status );
public int getInboxCount(Status status,String loggedInUserId);
public int getOutboxCount(Status status,String loggedInUserId);
public int getOtherCount(Status status,String loggedInUserId);

}
