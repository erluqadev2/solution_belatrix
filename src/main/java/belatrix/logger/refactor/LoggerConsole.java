package belatrix.logger.refactor;

import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.ConfigurationException;

public class LoggerConsole implements MyLogger {
	
	private Logger logger;
	
	public LoggerConsole(Logger logger) {
		this.logger = logger;
	}

	public void printLog(Map params, LogLevel level, String message) throws Exception {
		
		if (logger == null) {
			throw new NullPointerException("Logger is NULL");
		}
		if (level == null) {
			throw new ConfigurationException("Level not configurate");
		}
		
		ConsoleHandler ch = new ConsoleHandler();
		logger.addHandler(ch);
		if (level.level() >= LogLevel.ERROR.level()) {
			logger.log(Level.SEVERE, message);
		}
		if (level.level() >= LogLevel.WARNING.level()) {
			logger.log(Level.WARNING, message);
		}
		if (level.level() >= LogLevel.MESSAGE.level()) {
			logger.log(Level.INFO, message);
		}
	}

}
