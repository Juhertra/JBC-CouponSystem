package coupon.sys.core.dao;

import java.util.Collection;

import coupon.sys.core.beans.Coupon;
import coupon.sys.core.beans.CouponType;
import coupon.sys.core.exceptions.CouponSystemExceptions;

/**
 * The interface that {@code CouponDaoDb} implements from. it has all the
 * crucial company methods for communicating with the data base.
 * 
 * @author Julio Hernan Trajtemberg
 * @version 1.0 September 18, 2018.
 *
 */
public interface CouponDao {

	/**
	 * Creates the coupon.
	 *
	 * @param coupon the coupon
	 * @throws CouponSystemExceptions
	 */
	// - Abstract coupon C.R.U.D methods related actions.
	public void createCoupon(Coupon coupon) throws CouponSystemExceptions;

	/**
	 * Removes the coupon.
	 *
	 * @param coupon the coupon
	 * @throws CouponSystemExceptions
	 */
	public void removeCoupon(Coupon coupon) throws CouponSystemExceptions;

	/**
	 * Update coupon.
	 *
	 * @param coupon the coupon
	 * @throws CouponSystemExceptions
	 */
	public void updateCoupon(Coupon coupon) throws CouponSystemExceptions;

	/**
	 * Gets the coupon.
	 *
	 * @param id the id
	 * 
	 * @return the coupon
	 * @throws CouponSystemExceptions
	 */
	// - Abstract methods for coupons related information getters
	public Coupon getCoupon(long id) throws CouponSystemExceptions;

	/**
	 * Gets the all coupons.
	 *
	 * @return the all coupons
	 * @throws CouponSystemExceptions
	 */
	public Collection<Coupon> getAllCoupons() throws CouponSystemExceptions;

	/**
	 * Gets the coupons by type.
	 *
	 * @param couponType the coupon type
	 * 
	 * @return the coupons by type
	 * @throws CouponSystemExceptions
	 */
	public Collection<Coupon> getCouponsByType(CouponType couponType) throws CouponSystemExceptions;

	/**
	 * Gets the old coupons.
	 *
	 * @return the old coupons
	 * @throws CouponSystemExceptions
	 */
	public Collection<Coupon> getOldCoupons() throws CouponSystemExceptions;

}
