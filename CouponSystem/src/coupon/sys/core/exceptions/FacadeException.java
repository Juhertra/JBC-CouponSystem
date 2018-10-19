package coupon.sys.core.exceptions;

/**
 * The Class CustomerDaoDbException.
 *
 * @author Julio Hernan Trajtemberg
 * @version 1.0 September 18, 2018.
 */
public class FacadeException extends CouponSystemExceptions {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new customer dao db exception.
	 */
	public FacadeException() {
		super();
	}

	/**
	 * Instantiates a new customer dao db exception.
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
	public FacadeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Instantiates a new customer dao db exception.
	 *
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public FacadeException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new customer dao db exception.
	 *
	 * @param message
	 *            the message
	 */
	public FacadeException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new customer dao db exception.
	 *
	 * @param cause
	 *            the cause
	 */
	public FacadeException(Throwable cause) {
		super(cause);
	}

}
