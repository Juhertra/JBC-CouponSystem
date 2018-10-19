package coupon.sys.core.exceptions;

/**
 * The Class CouponDaoDbException.
 *
 * @author Julio Hernan Trajtemberg
 * @version 1.0 September 18, 2018.
 */
public class CouponDaoDbException extends CouponSystemExceptions {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new coupon dao db exception.
	 */
	public CouponDaoDbException() {
		super();
	}

	/**
	 * Instantiates a new coupon dao db exception.
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
	public CouponDaoDbException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Instantiates a new coupon dao db exception.
	 *
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public CouponDaoDbException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new coupon dao db exception.
	 *
	 * @param message
	 *            the message
	 */
	public CouponDaoDbException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new coupon dao db exception.
	 *
	 * @param cause
	 *            the cause
	 */
	public CouponDaoDbException(Throwable cause) {
		super(cause);
	}

}
