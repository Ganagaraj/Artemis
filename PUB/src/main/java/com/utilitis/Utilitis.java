package com.utilitis;

import javax.jms.MessageProducer;
import javax.jms.Session;

public class Utilitis {
	private static MessageProducer publisher;
    private static Session session;
    public void setSession(Session ses) {
    	session = ses;
    }
    public void setPublisher(MessageProducer pub) {
    	publisher = pub;
    }
    
    public Session getSession() {
    	return session;
    }
    
    public MessageProducer getPublisher() {
    	return publisher;
    }
}
