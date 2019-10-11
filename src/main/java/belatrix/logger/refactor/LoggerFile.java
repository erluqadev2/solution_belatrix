package belatrix.logger.refactor;

import java.io.File;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.naming.ConfigurationException;

import belatrix.logger.refactor.exceptions.ParamsInvalidException;

public class LoggerFile implements MyLogger {
	
	public static final String KEY_FILEFOLDER = "logfilefolder";
	public static final String NAME_FILE = "logfile.txt";
	public static final String PATH_SEPATATOR = System.getProperty("file.separator");
	
	private Logger logger;
	
	public LoggerFile(Logger logger) {
		this.logger = logger;
	}

	public void printLog(Map params, LogLevel level, String message) throws Exception {
		// Validations
		if (logger == null) {
			throw new NullPointerException("Logger is NULL");
		}
		if (params == null || params.size() == 0 ) {
			throw new ParamsInvalidException("Params are Empty");
		}
		if (!params.containsKey(KEY_FILEFOLDER)) {
			throw new ParamsInvalidException("Key " + KEY_FILEFOLDER + " not exist");
		}
		if (level == null) {
			throw new ConfigurationException("Level not configurate");
		}
		if (message == null || message.equals("")) {
			throw new Exception("Message Null or Empty");
		}
		
		// Configurate Folder
		String pathFolder = params.get(KEY_FILEFOLDER) + PATH_SEPATATOR + NAME_FILE;
		FileHandler fh = new FileHandler(pathFolder);
		SimpleFormatter simpleFormatter = new SimpleFormatter();
		fh.setFormatter(simpleFormatter);
		File logFile = new File(pathFolder);
		if (!logFile.exists()) {
			logFile.createNewFile();
		}
		
		// Print log
		logger.addHandler(fh);
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
