package belatrix.logger.refactor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Map;
import java.util.Properties;

import javax.naming.ConfigurationException;

import belatrix.logger.refactor.exceptions.ParamsInvalidException;

public class LoggerDB implements MyLogger {
	
	public static final String KEY_HOST = "host";
	public static final String KEY_PORT = "port";
	public static final String KEY_DBNAME = "dbname";
	public static final String KEY_USER = "user";
	public static final String KEY_PASSWORD = "password";

	public void printLog(Map params, LogLevel level, String message) throws Exception {
		// Validations
		if (params == null || params.size() == 0 ) {
			throw new ParamsInvalidException("Params are Empty");
		}
		String[] keys = {KEY_HOST, KEY_PORT, KEY_DBNAME, KEY_USER, KEY_PASSWORD};
		for (String key : keys) {
			if (!params.containsKey(key)) {
				throw new ParamsInvalidException("Key " + key + " not exist");
			}
		}
		if (level == null) {
			throw new ConfigurationException("Level not configurate");
		}
		
		
		// Conecction
		String host = params.get(KEY_HOST).toString();
		String port = params.get(KEY_PORT).toString();
		String dbname = params.get(KEY_DBNAME).toString();
		String urlConnection = "jdbc:postgresql://" + host + ":" + port + "/" + dbname;
		
		Connection connection = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", params.get(KEY_USER));
		connectionProps.put("password", params.get(KEY_PASSWORD));
		connection = DriverManager.getConnection(urlConnection, connectionProps);
	
		// Evaluamos LogLevel
		String loglevel = LogLevel.MESSAGE.levelName();
		if (level.level() >= LogLevel.WARNING.level()) {
			loglevel = LogLevel.WARNING.levelName();
		}
		if (level.level() >= LogLevel.ERROR.level()) {
			loglevel = LogLevel.ERROR.levelName();
		}
		
		// Insert DB
		Statement stmt = connection.createStatement();
		String insertSql = "insert into log_message(loglevel, logmessage) " + 
							"values('" + loglevel + "', '" + message + "') ";
		
		stmt.executeUpdate(insertSql);
	}

}
