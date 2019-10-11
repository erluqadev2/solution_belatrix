package belatrix.logger;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.naming.ConfigurationException;

import belatrix.logger.refactor.LogLevel;
import belatrix.logger.refactor.LoggerFile;
import belatrix.logger.refactor.exceptions.ParamsInvalidException;
import junit.framework.TestCase;

public class LoggerFileTest extends TestCase {
	
	private LoggerFile loggerFile;

	public void testLoggerNull() {
		try {
			loggerFile = new  LoggerFile(null);
			Map<String, String> params = new HashMap<String, String>();
			loggerFile.printLog(params, LogLevel.WARNING, "message default");
			fail("NullPointerException no thrown");
		} catch (NullPointerException e) {
			assertTrue(true);
		} catch (Exception e) {
			fail("Exception Incorrect");
		}
	}
	
	public void testParamsException() {
		try {
			loggerFile = new  LoggerFile(Logger.getLogger("mylog"));
			Map<String, String> params = null;
			loggerFile.printLog(params, LogLevel.WARNING, "message default");
			fail("ParamsException no thrown");
		} catch (ParamsInvalidException e) {
			assertTrue(true);
		} catch (Exception e) {
			fail("Exception Incorrect");
		}
	}
	
	public void testParamsException2() {
		try {
			loggerFile = new  LoggerFile(Logger.getLogger("mylog"));
			
			Map<String, String> params = new HashMap<String, String>();
			//params.put(LoggerFile.KEY_FILEFOLDER, "D:\\Ericson_otros\\Belatrix");
			
			loggerFile.printLog(params, LogLevel.WARNING, "message default");
			fail("ParamsException no thrown");
		} catch (ParamsInvalidException e) {
			assertTrue(true);
		} catch (Exception e) {
			fail("Exception Incorrect");
		}
	}
	
	public void testConfigurationException() {
		try {
			loggerFile = new  LoggerFile(Logger.getLogger("mylog"));
			
			Map<String, String> params = new HashMap<String, String>();
			params.put(LoggerFile.KEY_FILEFOLDER, "D:\\Ericson_otros\\Belatrix");
			
			loggerFile.printLog(params, null, "message default");
			fail("ConfigurationException no thrown");
		} catch (ConfigurationException e) {
			assertTrue(true);
		} catch (Exception e) {
			fail("Exception Incorrect");
		}
	}
}
