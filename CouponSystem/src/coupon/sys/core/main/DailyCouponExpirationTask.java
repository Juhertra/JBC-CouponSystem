package coupon.sys.core.main;

import java.util.Collection;
import java.util.TimerTask;

import coupon.sys.core.beans.Coupon;
import coupon.sys.core.dao.CouponDao;
import coupon.sys.core.dao.db.CouponDaoDb;
import coupon.sys.core.exceptions.ConnectionPoolException;
import coupon.sys.core.exceptions.CouponDaoDbException;

// TODO: Auto-generated Javadoc
/**
 * TimerTask implements Runnable. this task will be scheduled from the
 * CouponSystem singleton. the interval is defined in the properties file in
 * minutes . ( 1440 minutes = 24 hours )
 * 
 * @author Julio Hernan Trajtemberg
 * @version 1.0 September 18, 2018.
 */
public class DailyCouponExpirationTask extends TimerTask {

	/** The coupon dao. */
	private CouponDao couponDao = new CouponDaoDb();

	/**
	 * Instantiates a new daily coupon expiration task.
	 *
	 * @param couponDao the coupon dao
	 */
	// c'tor invoked with the couponDAO passed in
	public DailyCouponExpirationTask(CouponDao couponDao) {
		this.couponDao = couponDao;
	}

	/**
	 * Run DailyCouponExpirationTask when invoked by the time scheduler.
	 */
	@Override
	public void run() {
		try {
			cleanOldCoupons();
		} catch (ConnectionPoolException | InterruptedException | CouponDaoDbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to search for old coupons and remove them from DB.
	 *
	 * @throws ConnectionPoolException the connection pool exception
	 * @throws InterruptedException the interrupted exception
	 * @throws CouponDaoDbException the coupon dao db exception
	 */
	private void cleanOldCoupons() throws ConnectionPoolException, InterruptedException, CouponDaoDbException {
		Collection<Coupon> oldCoupons = couponDao.getOldCoupons();
		int numOfOldCoupons = oldCoupons.size();
		if (numOfOldCoupons <= 0) {
			return;
		} else {
			for (Coupon coupon : oldCoupons) {
				try {
					couponDao.removeCoupon(coupon);
				} catch (CouponDaoDbException e) {
						throw new CouponDaoDbException(coupon.getId() + " could not removed.", e);
				}
			}
		}
	}

}
