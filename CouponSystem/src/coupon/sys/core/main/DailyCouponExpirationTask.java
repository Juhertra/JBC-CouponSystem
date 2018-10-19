package coupon.sys.core.main;

import java.util.Collection;
import java.util.TimerTask;

import coupon.sys.core.beans.Coupon;
import coupon.sys.core.dao.CouponDao;
import coupon.sys.core.dao.db.CouponDaoDb;
import coupon.sys.core.exceptions.CouponSystemExceptions;

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
		} catch (CouponSystemExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Method to search for old coupons and remove them from DB.
	 *
	 * @throws CCouponSystemExceptions
	 */
	private void cleanOldCoupons() throws CouponSystemExceptions {
		Collection<Coupon> oldCoupons = couponDao.getOldCoupons();
		int numOfOldCoupons = oldCoupons.size();
		if (numOfOldCoupons <= 0) {
			return;
		} else {
			for (Coupon coupon : oldCoupons) {
				couponDao.removeCoupon(coupon);
			}
		}
	}

}
