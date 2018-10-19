package coupon.sys.core.utils;

/**
 * The Class DataBaseProperties. This class is used for Global properties.
 */

public class DataBaseProperties {

	/** The url. */
	private static String url = "jdbc:derby://localhost:1527/coupon_sys_db";

	/** The driver. */
	private static String driver = "org.apache.derby.jdbc.ClientDriver";

	/** The user. */
	private static String user = "admin";

	/** The password. */
	private static String password = "1234";

	/** The max connections. */
	private static int maxConnections = 10;

	/** The thread interval minutes. */
	private static int theadIntervalMinutes = 1440;

	public static String getUrl() {
		return url;
	}

	public static void setUrl(String url) {
		DataBaseProperties.url = url;
	}

	public static String getDriver() {
		return driver;
	}

	public static void setDriver(String driver) {
		DataBaseProperties.driver = driver;
	}

	public static String getUser() {
		return user;
	}

	public static void setUser(String user) {
		DataBaseProperties.user = user;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		DataBaseProperties.password = password;
	}

	public static int getMaxConnections() {
		return maxConnections;
	}

	public static void setMaxConnections(int maxConnections) {
		DataBaseProperties.maxConnections = maxConnections;
	}

	public static int getTheadIntervalMinutes() {
		return theadIntervalMinutes;
	}

	public static void setTheadIntervalMinutes(int theadIntervalMinutes) {
		DataBaseProperties.theadIntervalMinutes = theadIntervalMinutes;
	}

	@Override
	public String toString() {
		return "DataBaseProperties [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}
