package coupon.sys.core.exceptions;

/**
 * The Class CompanyDaodbException.
 *
 * @author Julio Hernan Trajtemberg
 * @version 1.0 September 18, 2018.
 */
public class CompanyDaodbException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new company daodb exception.
	 */
	public CompanyDaodbException() {
		super();
	}

	/**
	 * Instantiates a new company daodb exception.
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
	public CompanyDaodbException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Instantiates a new company daodb exception.
	 *
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public CompanyDaodbException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new company daodb exception.
	 *
	 * @param message
	 *            the message
	 */
	public CompanyDaodbException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new company daodb exception.
	 *
	 * @param cause
	 *            the cause
	 */
	public CompanyDaodbException(Throwable cause) {
		super(cause);
	}

}
