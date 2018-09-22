package coupon.sys.core.exceptions;

/**
 * The Class CouponSystemExceptions.
 *
 * @author Julio Hernan Trajtemberg
 * @version 1.0 September 18, 2018.
 */
public class CouponSystemExceptions extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new coupon system exceptions.
	 */
	public CouponSystemExceptions() {
		super();
	}

	/**
	 * Instantiates a new coupon system exceptions.
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
	public CouponSystemExceptions(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Instantiates a new coupon system exceptions.
	 *
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public CouponSystemExceptions(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new coupon system exceptions.
	 *
	 * @param message
	 *            the message
	 */
	public CouponSystemExceptions(String message) {
		super(message);
	}

	/**
	 * Instantiates a new coupon system exceptions.
	 *
	 * @param message
	 *            the message
	 * @param e
	 *            the e
	 */
	public CouponSystemExceptions(String message, Exception e) {
		super(message);
	}

	/**
	 * Instantiates a new coupon system exceptions.
	 *
	 * @param cause
	 *            the cause
	 */
	public CouponSystemExceptions(Throwable cause) {
		super(cause);
	}

}
