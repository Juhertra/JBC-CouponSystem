package coupon.sys.core.exceptions;

/**
 * The Class CryptoHashException.
 *
 * @author Julio Hernan Trajtemberg
 * @version 1.0 September 18, 2018.
 */
public class CryptoHashException extends CouponSystemExceptions {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new crypto hash exception.
	 */
	public CryptoHashException() {
		super();
	}

	/**
	 * Instantiates a new crypto hash exception.
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
	public CryptoHashException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Instantiates a new crypto hash exception.
	 *
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public CryptoHashException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new crypto hash exception.
	 *
	 * @param message
	 *            the message
	 */
	public CryptoHashException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new crypto hash exception.
	 *
	 * @param cause
	 *            the cause
	 */
	public CryptoHashException(Throwable cause) {
		super(cause);
	}

}
