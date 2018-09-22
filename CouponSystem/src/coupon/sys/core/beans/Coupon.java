package coupon.sys.core.beans;

import java.io.Serializable;
import java.sql.Date;

/**
 * This class is for creating the coupon object to use with the different
 * company and customer related methods.
 * 
 * @author Julio Hernan Trajtemberg
 * @version 1.0 September 18, 2018.
 */
public class Coupon implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private long id;

	/** The title. */
	private String title;

	/** The start date. */
	private Date startDate;

	/** The end date. */
	private Date endDate;

	/** The amount. */
	private int amount;

	/** The type. */
	private CouponType type;

	/** The message. */
	private String message;

	/** The price. */
	private double price;

	/** The image. */
	private String image;

	/** The company id. */
	private long companyId;

	/**
	 * Simple C'tor when no object needed.
	 */
	public Coupon() {

	}

	/**
	 * Coupon C'tor.
	 *
	 * @param id
	 *            Coupon ID - Auto-generated by DB
	 * @param title
	 *            Coupon title
	 * @param startDate
	 *            Coupon start date
	 * @param endDate
	 *            Coupon end date
	 * @param amount
	 *            Coupon amount - quantity of same coupons
	 * @param type
	 *            Coupon type - {@code CouponType}
	 * @param message
	 *            Coupon message - text to describe the coupon
	 * @param price
	 *            Coupon price
	 * @param image
	 *            Coupon image
	 * @param companyId
	 *            Coupon companyId - each coupon is correlated to a company ID
	 */
	public Coupon(long id, String title, Date startDate, Date endDate, int amount, CouponType type, String message,
			double price, String image, long companyId) {

		super();
		this.id = id;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.type = type;
		this.message = message;
		this.price = price;
		this.image = image;
		this.companyId = companyId;
	}

	/**
	 * Gets the id.
	 *
	 * @return id - return Coupon ID
	 */
	public long getId() {

		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            - Set Coupon ID
	 */
	public void setId(long id) {

		this.id = id;
	}

	/**
	 * Gets the title.
	 *
	 * @return title - returns Coupon title
	 */
	public String getTitle() {

		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title
	 *            - Set Coupon title
	 */
	public void setTitle(String title) {

		this.title = title;
	}

	/**
	 * Gets the start date.
	 *
	 * @return startDate - Returns coupon start date
	 */
	public Date getStartDate() {

		return startDate;
	}

	/**
	 * Sets the start date.
	 *
	 * @param startDate
	 *            - Set coupon start date
	 */
	public void setStartDate(Date startDate) {

		this.startDate = startDate;
	}

	/**
	 * Gets the end date.
	 *
	 * @return endDate - Returns coupon end date
	 */
	public Date getEndDate() {

		return endDate;
	}

	/**
	 * Sets the end date.
	 *
	 * @param endDate
	 *            - Set coupon end date
	 */
	public void setEndDate(Date endDate) {

		this.endDate = endDate;
	}

	/**
	 * Gets the amount.
	 *
	 * @return amount - Returns coupon quantities
	 */
	public int getAmount() {

		return amount;
	}

	/**
	 * Sets the amount.
	 *
	 * @param amount
	 *            - Set coupon quantities
	 */
	public void setAmount(int amount) {

		this.amount = amount;
	}

	/**
	 * Gets the type.
	 *
	 * @return type - Return {@code CouponType}
	 */
	public CouponType getType() {

		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type
	 *            - Set coupon {@code CouponType}
	 */
	public void setType(CouponType type) {

		this.type = type;
	}

	/**
	 * Gets the message.
	 *
	 * @return message - Return coupon message
	 */
	public String getMessage() {

		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message
	 *            - Set coupon message
	 */
	public void setMessage(String message) {

		this.message = message;
	}

	/**
	 * Gets the price.
	 *
	 * @return price - Return coupon price
	 */
	public double getPrice() {

		return price;
	}

	/**
	 * Sets the price.
	 *
	 * @param price
	 *            - Set coupon price
	 */
	public void setPrice(double price) {

		this.price = price;
	}

	/**
	 * Gets the image.
	 *
	 * @return image - Return coupon image
	 */
	public String getImage() {

		return image;
	}

	/**
	 * Sets the image.
	 *
	 * @param image
	 *            - Set coupon image
	 */
	public void setImage(String image) {

		this.image = image;
	}

	/**
	 * Gets the company id.
	 *
	 * @return companyId - Returns company ID, used to correlate to the coupon
	 */
	public long getCompanyId() {

		return companyId;
	}

	/**
	 * Sets the company id.
	 *
	 * @param companyId
	 *            - Set company ID, used to correlate to the coupon
	 */
	public void setCompanyId(long companyId) {

		this.companyId = companyId;
	}

	@Override
	public String toString() {

		return "Coupon [id=" + id + ", title=" + title + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", amount=" + amount + ", type=" + type + ", message=" + message + ", price=" + price + ", image="
				+ image + ", companyId=" + companyId + "]";
	}

}
