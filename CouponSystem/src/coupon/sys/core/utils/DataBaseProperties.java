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

	/**
	 * Gets the url.
	 *
	 * @return the url
	 */
	public static String getUrl() {
		return url;
	}

	/**
	 * Gets the driver.
	 *
	 * @return the driver
	 */
	public static String getDriver() {
		return driver;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public static String getUser() {
		return user;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public static String getPassword() {
		return password;
	}

	/**
	 * Gets the max connections.
	 *
	 * @return the max connections
	 */
	public static int getMaxConnections() {
		return maxConnections;
	}

	/**
	 * Gets the thead interval minutes.
	 *
	 * @return the thead interval minutes
	 */
	public static int getTheadIntervalMinutes() {
		return theadIntervalMinutes;
	}

}
