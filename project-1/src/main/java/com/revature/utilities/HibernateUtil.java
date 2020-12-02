package com.revature.utilities;

import java.util.TimeZone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	private static Session session = null;
	
	private static SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	
	public static Session getSession() {
		if(session == null) {
			session = sf.withOptions().jdbcTimeZone(TimeZone.getTimeZone("UTC")).openSession();
		}
		
		return session;
	}
	
	public static void closeSession() {
		session.close();
		sf.close();
	}

}
