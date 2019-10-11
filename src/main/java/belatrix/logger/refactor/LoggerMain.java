package belatrix.logger.refactor;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class LoggerMain {
	
	public static Logger logger;

	static {
		logger = Logger.getLogger("mylog");
	}
	
	public static void printLogger(MyLogger[] loggers, LogLevel level, Map params, String message) throws Exception {
		for (MyLogger lg : loggers) {
			lg.printLog(params, level, message);
		}
	}
	
	public static void main(String[] args) {
		
		try {
			// set Logger Types to use
			MyLogger logConsole = new LoggerConsole(logger);
			MyLogger logDb = new LoggerDB();
			MyLogger logFile = new LoggerFile(logger);
			MyLogger[] loggers = {logConsole, logDb, logFile};
			
			LogLevel level = LogLevel.ERROR; // set Level
			
			Map<String, String> params = new HashMap<String, String>();
			
			// Params LogDB
			params.put(LoggerDB.KEY_HOST, "localhost");
			params.put(LoggerDB.KEY_PORT, "5432");
			params.put(LoggerDB.KEY_DBNAME, "loggerdb");
			params.put(LoggerDB.KEY_USER, "postgres");
			params.put(LoggerDB.KEY_PASSWORD, "postgres");
			
			// Params LogFile
			params.put(LoggerFile.KEY_FILEFOLDER, "D:\\Ericson_otros\\Belatrix");
			
			String message = "error de prueba 1";
			
			
			LoggerMain.printLogger(loggers, level, params, message);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
