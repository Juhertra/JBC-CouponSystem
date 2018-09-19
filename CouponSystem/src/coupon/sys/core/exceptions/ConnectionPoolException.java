package coupon.sys.core.exceptions;

// TODO: Auto-generated Javadoc
/**
 * The Class ConnectionPoolException.
 *
 * @author Julio Hernan Trajtemberg
 * @version 1.0 September 18, 2018.
 */
public class ConnectionPoolException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new connection pool exception.
	 */
	public ConnectionPoolException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new connection pool exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 * @param enableSuppression the enable suppression
	 * @param writableStackTrace the writable stack trace
	 */
	public ConnectionPoolException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new connection pool exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public ConnectionPoolException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new connection pool exception.
	 *
	 * @param message the message
	 */
	public ConnectionPoolException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new connection pool exception.
	 *
	 * @param cause the cause
	 */
	public ConnectionPoolException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
