package com.abhi.app.Dao;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.abhi.app.model.Message;
import com.abhi.app.model.Message.Status;

public class MessageDaoImpl implements MessageDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public boolean saveMessage(Message message ) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(message);
		tx.commit();
		session.close();
		return true;
	}

	public ArrayList<Object[]> getInbox(String loggedInUserId) {
		Session session=sessionFactory.openSession();
		@SuppressWarnings("rawtypes")
		Query query=session.
				createQuery("select p, m from Message m, Profile p where m.receiverId=:receiverId and m.receiverStatus=:receiverStatus"
						+ " and p.id=m.senderId");
		query.setParameter("receiverId", loggedInUserId);
		query.setParameter("receiverStatus", Message.Status.INBOX);
		@SuppressWarnings("unchecked")
		ArrayList<Object[]> list=(ArrayList<Object[]>) query.getResultList();
		session.close();
		return list;
	}

	public ArrayList<Object[]> getOutbox(String loggedInUserId) {
		Session session=sessionFactory.openSession();
		@SuppressWarnings("rawtypes")
		Query query=session.
				createQuery("select p, m from Message m, Profile p where m.senderId=:senderId and m.senderStatus=:senderStatus"
						+ " and p.id=m.receiverId");
		query.setParameter("senderId", loggedInUserId);
		query.setParameter("senderStatus", Message.Status.OUTBOX);
		@SuppressWarnings("unchecked")
		ArrayList<Object[]> list=(ArrayList<Object[]>) query.getResultList();
		session.close();
		return list;
	}

	public ArrayList<Object[]> getTrash(String loggedInUserId) {
		return getMyMessages(loggedInUserId, Message.Status.DELETED);
	}

	public ArrayList<Object[]> getFlagged(String loggedInUserId) {
			return getMyMessages(loggedInUserId, Message.Status.FLAGGED);
	}

	public ArrayList<Object[]> getSpam(String loggedInUserId) {
		return getMyMessages(loggedInUserId, Message.Status.SPAM);
	}

	public ArrayList<Object[]> getMyMessages(String loggedInUserId, Message.Status Status)
	{
		Session session=sessionFactory.openSession();
		@SuppressWarnings("rawtypes")
		Query query=session.
				createQuery("select p, m from Message m, Profile p where ((m.senderId=:Id and m.senderStatus=:Status and p.id=m.receiverId) or "
						+ " (m.receiverId=:Id and m.receiverStatus=:Status and p.id=m.senderId))");
		query.setParameter("Id", loggedInUserId);
		query.setParameter("Status", Status);
		@SuppressWarnings("unchecked")
		ArrayList<Object[]> list=(ArrayList<Object[]>) query.getResultList();
		session.close();
		return list;

	}

	public int statusUpdateForReceiver(String loggedInUserId, List<String> messageIdList , Message.Status status  ) {
		Session session=sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		@SuppressWarnings("rawtypes")
		Query query=session.createQuery("update Message m set m.receiverStatus=:status where m.receiverId=:id and m.id IN (:names)");
		setParam(loggedInUserId, messageIdList, status, query);
		int res1=query.executeUpdate();
		tx.commit();
		session.close();
		return res1;
	}
	
	public int statusUpdateForSender(String loggedInUserId, List<String> messageIdList , Message.Status status  ) {
		Session session=sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		@SuppressWarnings("rawtypes")
		Query query=session.createQuery("update Message m set m.senderStatus=:status where m.senderId=:id and m.id IN (:names)");
		setParam(loggedInUserId, messageIdList, status, query);
		int res1=query.executeUpdate();
		tx.commit();
		session.close();
		return res1;
	}
	
	
	

	private void setParam(String loggedInUserId, List<String> messageIdList, Message.Status status, @SuppressWarnings("rawtypes") Query query) {
		query.setParameter("id", loggedInUserId);
		query.setParameter("status", status);
		query.setParameter("names", messageIdList);
	}
	public boolean readUpdate(String loggedInUserId, List<String> messageIdList , Message.Read status  ) {
		Session session=sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		@SuppressWarnings("rawtypes")
		Query firstQuery=session.createQuery("update Message m set m.receiverRead=:status where m.receiverId=:id and m.id IN (:names)");
		@SuppressWarnings("rawtypes")
		Query secondQuery=session.createQuery("update Message m set m.senderRead=:status where m.senderId=:id and m.id IN (:names)");
		setParamForRead(loggedInUserId, messageIdList, status, firstQuery);
		setParamForRead(loggedInUserId, messageIdList, status, secondQuery);
		int res1=firstQuery.executeUpdate();
		int res2=secondQuery.executeUpdate();
		tx.commit();
		session.close();
		return (res1>0 || res2>0)?true:false;
	}
	private void setParamForRead(String loggedInUserId, List<String> messageIdList, Message.Read status, @SuppressWarnings("rawtypes") Query query) {
		query.setParameter("id", loggedInUserId);
		query.setParameter("status", status);
		query.setParameter("names", messageIdList);
	}

	public int getInboxCount(Status status,String loggedInUserId) {
		String myQuery="select count(m) from Message m where m.receiverId=:Id and m.receiverStatus=:status";
		return universalCounter(status, loggedInUserId, myQuery);
	}
	public int getOutboxCount(Status status,String loggedInUserId) {
		String myQuery="select count(m) from Message m where m.senderId=:Id and m.senderStatus=:status";
		return universalCounter(status, loggedInUserId, myQuery);
	}
	public int getOtherCount(Status status,String loggedInUserId) {
		String myQuery="select count(m) from Message m where ((m.senderId=:Id and m.senderStatus=:status) or "
				+ " (m.receiverId=:Id and m.receiverStatus=:status))";
		return universalCounter(status, loggedInUserId, myQuery);
	}
	public int universalCounter(Status status,String loggedInUserId,String myQuery)
	{
		Session session=sessionFactory.openSession();
		@SuppressWarnings("rawtypes")
		Query query=session.createQuery(myQuery);
		query.setParameter("Id", loggedInUserId);
		query.setParameter("status", status);
		int count=((Long)query.getSingleResult()).intValue();
		session.close();
		return count;
	}

}
