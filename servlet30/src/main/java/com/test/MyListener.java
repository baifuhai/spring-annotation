package com.test;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//@WebListener
public class MyListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		//ServletContext servletContext = arg0.getServletContext();
		System.out.println("contextInitialized...");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("contextDestroyed...");
	}

}
