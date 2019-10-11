package belatrix.logger;

import java.util.HashMap;
import java.util.Map;

import javax.naming.ConfigurationException;

import belatrix.logger.refactor.LogLevel;
import belatrix.logger.refactor.LoggerDB;
import belatrix.logger.refactor.exceptions.ParamsInvalidException;
import junit.framework.TestCase;

public class LoggerDBTest extends TestCase {
	
	private LoggerDB loggerDB;

	public void testParamsException() {
		try {
			loggerDB = new  LoggerDB();
			
			Map<String, String> params = new HashMap<String, String>();
			params.put(LoggerDB.KEY_HOST, "localhost");
			//params.put(LoggerDB.KEY_PORT, "5432");
			params.put(LoggerDB.KEY_DBNAME, "loggerdb");
			params.put(LoggerDB.KEY_USER, "postgres");
			params.put(LoggerDB.KEY_PASSWORD, "postgres");
			
			loggerDB.printLog(params, LogLevel.WARNING, "message default");
			fail("ParamsException no thrown");
		} catch (ParamsInvalidException e) {
			assertTrue(true);
		} catch (Exception e) {
			fail("Exception Incorrect");
		}
	}
	
	
	public void testConfigurationException() {
		try {
			loggerDB = new  LoggerDB();
			
			Map<String, String> params = new HashMap<String, String>();
			params.put(LoggerDB.KEY_HOST, "localhost");
			params.put(LoggerDB.KEY_PORT, "5432");
			params.put(LoggerDB.KEY_DBNAME, "loggerdb");
			params.put(LoggerDB.KEY_USER, "postgres");
			params.put(LoggerDB.KEY_PASSWORD, "postgres");
			
			loggerDB.printLog(params, null, "message default");
			fail("ConfigurationException no thrown");
		} catch (ConfigurationException e) {
			assertTrue(true);
		} catch (Exception e) {
			fail("Exception Incorrect");
		}
	}
}
