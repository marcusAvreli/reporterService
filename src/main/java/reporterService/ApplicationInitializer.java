package reporterService;

/*
 * #%L
 * elab4-backend
 * =======
 * Copyright (C) 2011 - 2019 Huygens ING
 * =======
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;



//http://192.168.134.150:8080/identityiq/rest/XYZCustom/custom/
//https://stackoverflow.com/questions/23277429/java-lang-abstractmethoderror-javax-ws-rs-core-uribuilder-uri/26767488
public class ApplicationInitializer implements ServletContextListener {
	 private static final Logger logger = LogManager.getLogger(ApplicationInitializer.class);
	public ApplicationInitializer() {
		System.setProperty("application.starttime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

		//Log.info("clearing expired searches");
		//SearchService searchService = SearchService.instance();
		//searchService.removeExpiredSearches();

		logger.info("initializer constructor");
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.info("initializing context");
		//logger.info("serverinfo={}", sce.getServletContext().getServerInfo());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.info("destroying context");
	}

}