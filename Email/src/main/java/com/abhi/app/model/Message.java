package com.abhi.app.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="inbox")
public class Message {
private String senderId,receiverId,messageBody,subject,messageId;
@DateTimeFormat(pattern = "yyyy-MM-dd")
private Date dateSent;
private String files; 
private Status receiverStatus;
private Status senderStatus;
private Read receiverRead;
private Read senderRead;
public static enum Status {
    OUTBOX,
    INBOX,
    FLAGGED,
    DELETED,
    SPAM,
    NULL;
	
}
public static enum Read{
	READ,
	UNREAD;
}

public Message(){
}

@Id
public String getMessageId() {
	return messageId;
}

public void setMessageId(String messageId) {
	this.messageId = messageId;
}

public String getReceiverId() {
	return receiverId;
}
public void setReceiverId(String receiverId) {
	this.receiverId = receiverId;
}
public String getSenderId() {
	return senderId;
}
public void setSenderId(String senderid) {
	this.senderId = senderid;
}
public String getSubject() {
	return subject;
}
public void setSubject(String subject) {
	this.subject = subject;
}
public String getMessageBody() {
	return messageBody;
}
public void setMessageBody(String messageBody) {
	this.messageBody = messageBody;
}
public Date getDateSent() {
	return dateSent;
}
public void setDateSent(Date dateSent) {
	this.dateSent = dateSent;
}
public String getFiles() {
	return files;
}

public void setFiles(String files) {
	this.files = files;
}
public Status getReceiverStatus() {
	return receiverStatus;
}

public void setReceiverStatus(Status receiverStatus) {
	this.receiverStatus = receiverStatus;
}

public Status getSenderStatus() {
	return senderStatus;
}

public void setSenderStatus(Status senderStatus) {
	this.senderStatus = senderStatus;
}

public Read getReceiverRead() {
	return receiverRead;
}

public void setReceiverRead(Read receiverRead) {
	this.receiverRead = receiverRead;
}

public Read getSenderRead() {
	return senderRead;
}

public void setSenderRead(Read senderRead) {
	this.senderRead = senderRead;
}

}
