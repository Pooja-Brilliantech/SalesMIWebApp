package com.fieldmi.commons;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by IntelliJ IDEA. User: Milind Date: Feb 9, 2012 Time: 11:39:38 AM To
 * change this template use File | Settings | File Templates.
 */
public class HibernateUtils {
	private static SessionFactory sf;

	public HibernateUtils() {
	}

	static {
		sf = new Configuration().configure().buildSessionFactory();
	}

	public static SessionFactory getSessionFactory() {
		return sf;
	}

	public static Session getSession() {

		return getSessionFactory().openSession();
	}

}
