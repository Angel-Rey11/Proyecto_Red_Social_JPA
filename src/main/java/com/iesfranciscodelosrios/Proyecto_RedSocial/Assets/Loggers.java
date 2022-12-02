package com.iesfranciscodelosrios.Proyecto_RedSocial.Assets;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Loggers {
	
	public static void LogsSevere(String s) {
		try {
			InputStream configFile = Loggers.class.getResourceAsStream("Loggin.properties");
			LogManager.getLogManager().readConfiguration(configFile);
		} catch (SecurityException | IOException | NullPointerException e) {
			System.out.println("Logging system not initialized");
		}
		Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		logger.severe(s);
	}
	
	public static void LogsInfo(String s) {
		try {
			InputStream configFile = Loggers.class.getResourceAsStream("Loggin.properties");
			LogManager.getLogManager().readConfiguration(configFile);
		} catch (SecurityException | IOException | NullPointerException e) {
			System.out.println("Logging system not initialized");
		}
		Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		logger.info(s);
	}
}
