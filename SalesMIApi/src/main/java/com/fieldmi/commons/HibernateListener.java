package com.fieldmi.commons; /**
								* Created by IntelliJ IDEA.
								* User: Milind
								* Date: Feb 9, 2012
								* Time: 12:18:17 PM
								* To change this template use File | Settings | File Templates.
								*/

import java.io.File;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class HibernateListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {
	public static File hibernateConfig;
	private static Properties props = new Properties();

	public static Properties getProps() {
		return props;
	}

	// Public constructor is required by servlet spec
	public HibernateListener() {

	}

	// -------------------------------------------------------
	// ServletContextListener implementation
	// -------------------------------------------------------
	@Override
	public void contextInitialized(ServletContextEvent event) {
		/*
		 * This method is called when the servlet context is initialized(when the Web
		 * application is deployed). You can initialize servlet context related data
		 * here.
		 */
		// TimeZone.setDefault(TimeZone.getTimeZone("IST"));
		// Locale.setDefault(Locale.ENGLISH);
		// PropertyConfigurator.configure(event.getServletContext().getRealPath("/")+
		// "/WEB-INF/classes/logger-config.properties");
		// org.slf4j.Logger logger = LoggerFactory.getLogger(HibernateListener.class);
		// logger.debug("Log4j + slf4j Initialised Successfully ... ");

		String fileName = event.getServletContext().getRealPath("/") + "/WEB-INF/classes/hibernate.cfg.xml";
		hibernateConfig = new File(fileName);
		// InjectorFactory.getInjector();
		// try {
		// props.load(this.getClass().getResourceAsStream("/email.properties"));
		// } catch (FileNotFoundException e1) {
		// FieldMILogger.error(this.getClass().getName(),e1);
		// } catch (IOException e) {
		// FieldMILogger.error(this.getClass().getName(),e);
		// }
		// batchProcessManager =
		// InjectorFactory.getInjector().getInstance(BatchProcessWorkManager.class);
		// batchProcessManager.beginWork();

		// ...................................................................................................

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		/*
		 * This method is invoked when the Servlet Context (the Web application) is
		 * undeployed or Application Server shuts down.
		 */

		// sf.close();
		// batchProcessManager.endWork();
	}

	// -------------------------------------------------------
	// HttpSessionListener implementation
	// -------------------------------------------------------
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		/* Session is created. */
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		/* Session is destroyed. */
	}

	// -------------------------------------------------------
	// HttpSessionAttributeListener implementation
	// -------------------------------------------------------

	@Override
	public void attributeAdded(HttpSessionBindingEvent sbe) {
		/*
		 * This method is called when an attribute is added to a session.
		 */
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent sbe) {
		/*
		 * This method is called when an attribute is removed from a session.
		 */
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent sbe) {
		/*
		 * This method is invoked when an attibute is replaced in a session.
		 */
	}
}
