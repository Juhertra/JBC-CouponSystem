package coupon.sys.core.exceptions;

/**
 * The Class ConnectionPoolException.
 *
 * @author Julio Hernan Trajtemberg
 * @version 1.0 September 18, 2018.
 */
public class ConnectionPoolException extends CouponSystemExceptions {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new connection pool exception.
	 */
	public ConnectionPoolException() {
		super();
	}

	/**
	 * Instantiates a new connection pool exception.
	 *
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 * @param enableSuppression
	 *            the enable suppression
	 * @param writableStackTrace
	 *            the writable stack trace
	 */
	public ConnectionPoolException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Instantiates a new connection pool exception.
	 *
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public ConnectionPoolException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new connection pool exception.
	 *
	 * @param message
	 *            the message
	 */
	public ConnectionPoolException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new connection pool exception.
	 *
	 * @param cause
	 *            the cause
	 */
	public ConnectionPoolException(Throwable cause) {
		super(cause);
	}

}
