package belatrix.logger;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.naming.ConfigurationException;

import belatrix.logger.refactor.LogLevel;
import belatrix.logger.refactor.LoggerConsole;
import junit.framework.TestCase;

public class LoggerConsoleTest extends TestCase {
	
	private LoggerConsole loggerConsole;

	public void testLoggerNull() {
		try {
			loggerConsole = new  LoggerConsole(null);
			Map<String, String> params = new HashMap<String, String>();
			loggerConsole.printLog(params, LogLevel.WARNING, "message default");
			fail("NullPointerException no thrown");
		} catch (NullPointerException e) {
			assertTrue(true);
		} catch (Exception e) {
			fail("Exception Incorrect");
		}
	}
	
	public void testConfigurationException() {
		try {
			loggerConsole = new  LoggerConsole(Logger.getLogger("mylog"));
			
			loggerConsole.printLog(null, null, "message default");
			fail("ConfigurationException no thrown");
		} catch (ConfigurationException e) {
			assertTrue(true);
		} catch (Exception e) {
			fail("Exception Incorrect");
		}
	}
}
