package belatrix.logger.refactor;

public enum LogLevel {
	MESSAGE(100, "MESSAGE"),
	WARNING(200, "WARNING"),
	ERROR(300, "ERROR");
	
	private int level;
	private String levelName;
	
	private LogLevel(int level, String levelName) {
		this.level = level;
		this.levelName = levelName;
	}
	
	public int level() {
		return this.level;
	}
	
	public String levelName() {
		return this.levelName;
	}
}
