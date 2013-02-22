package fr.univartois.ili.sadoc.ui.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContextFactory {

	private static ApplicationContext applicationContext = null;
	private static final String APPLICATION_CONTEXT_XML = "service-client.xml";

	public enum ApplicationContextType {
		CLASSPATH, FILE_SYSTEM
	}

	public static ApplicationContext getContext() {
		if (applicationContext == null) {
			applicationContext = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_XML);
		}
		return applicationContext;
	}
}
