package coupon.sys.core.dao;

import java.sql.SQLException;
import java.util.Collection;

import coupon.sys.core.beans.Coupon;
import coupon.sys.core.beans.CouponType;
import coupon.sys.core.exceptions.ConnectionPoolException;
import coupon.sys.core.exceptions.CouponDaoDbException;

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
	 * @throws CouponDaoDbException the coupon dao db exception
	 * @throws ConnectionPoolException the connection pool exception
	 * @throws InterruptedException the interrupted exception
	 * @throws SQLException the SQL exception
	 */
	// - Abstract coupon C.R.U.D methods related actions.
	public void createCoupon(Coupon coupon)
			throws CouponDaoDbException, ConnectionPoolException, InterruptedException, SQLException;

	/**
	 * Removes the coupon.
	 *
	 * @param coupon the coupon
	 * @throws CouponDaoDbException the coupon dao db exception
	 * @throws ConnectionPoolException the connection pool exception
	 * @throws InterruptedException the interrupted exception
	 */
	public void removeCoupon(Coupon coupon) throws CouponDaoDbException, ConnectionPoolException, InterruptedException;

	/**
	 * Update coupon.
	 *
	 * @param coupon the coupon
	 * @throws CouponDaoDbException the coupon dao db exception
	 * @throws ConnectionPoolException the connection pool exception
	 * @throws InterruptedException the interrupted exception
	 */
	public void updateCoupon(Coupon coupon) throws CouponDaoDbException, ConnectionPoolException, InterruptedException;

	/**
	 * Gets the coupon.
	 *
	 * @param id the id
	 * @return the coupon
	 * @throws CouponDaoDbException the coupon dao db exception
	 * @throws ConnectionPoolException the connection pool exception
	 * @throws InterruptedException the interrupted exception
	 */
	// - Abstract methods for coupons related information getters
	public Coupon getCoupon(long id) throws CouponDaoDbException, ConnectionPoolException, InterruptedException;

	/**
	 * Gets the all coupons.
	 *
	 * @return the all coupons
	 * @throws CouponDaoDbException the coupon dao db exception
	 * @throws ConnectionPoolException the connection pool exception
	 * @throws InterruptedException the interrupted exception
	 */
	public Collection<Coupon> getAllCoupons()
			throws CouponDaoDbException, ConnectionPoolException, InterruptedException;

	/**
	 * Gets the coupons by type.
	 *
	 * @param couponType the coupon type
	 * @return the coupons by type
	 * @throws CouponDaoDbException the coupon dao db exception
	 * @throws ConnectionPoolException the connection pool exception
	 * @throws InterruptedException the interrupted exception
	 */
	public Collection<Coupon> getCouponsByType(CouponType couponType)
			throws CouponDaoDbException, ConnectionPoolException, InterruptedException;

	/**
	 * Gets the old coupons.
	 *
	 * @return the old coupons
	 * @throws ConnectionPoolException the connection pool exception
	 * @throws InterruptedException the interrupted exception
	 * @throws CouponDaoDbException the coupon dao db exception
	 */
	public Collection<Coupon> getOldCoupons() throws ConnectionPoolException, InterruptedException, CouponDaoDbException;

}
