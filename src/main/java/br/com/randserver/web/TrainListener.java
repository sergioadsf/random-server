package br.com.randserver.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.context.annotation.Configuration;

@Configuration
@WebListener
public class TrainListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		Train.getInstance();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

}
