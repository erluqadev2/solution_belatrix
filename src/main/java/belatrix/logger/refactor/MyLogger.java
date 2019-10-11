package belatrix.logger.refactor;

import java.util.Map;
import java.util.logging.Logger;

public interface MyLogger {

	void printLog(Map params, LogLevel level, String message) throws Exception;
}
