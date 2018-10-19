package coupon.sys.core.facade;

import java.sql.Date;
import java.util.Collection;
import java.util.Iterator;

import coupon.sys.core.beans.Company;
import coupon.sys.core.beans.Coupon;
import coupon.sys.core.beans.CouponType;
import coupon.sys.core.dao.CompanyDao;
import coupon.sys.core.dao.CouponDao;
import coupon.sys.core.dao.db.CompanyDaoDb;
import coupon.sys.core.dao.db.CouponDaoDb;
import coupon.sys.core.exceptions.ConnectionPoolException;
import coupon.sys.core.exceptions.CouponSystemExceptions;
import coupon.sys.core.exceptions.CryptoHashException;
import coupon.sys.core.exceptions.DBDAOException;

/**
 * This class is a part of the facade pattern layer. The class which manages the
 * company user's request for using methods with company functionality after a
 * successful login. The class uses the methods of the {@code CompanyDaoDb} and
 * the {@code CouponDaoDb} classes - which communicates with the data base. In
 * addition, this class also catches any related exceptions that might occur.
 * 
 * @author Julio Hernan Trajtemberg
 * @version 1.0 September 18, 2018.
 */
public class CompanyFacade implements CouponClientFacade {

	/** The company dao. */
	private CompanyDao companyDao = new CompanyDaoDb();

	/** The coupon dao. */
	private CouponDao couponDao = new CouponDaoDb();

	/** The logged in company ID. */
	protected static long loggedInCompanyID = 0;

	/**
	 * Simple C'tor when no object needed.
	 */
	public CompanyFacade() {

	}

	/**
	 * Gets the company.
	 *
	 * @param companyName the company name
	 * @return the company
	 * @throws DBDAOException   the company daodb exception
	 * @throws ConnectionPoolException the connection pool exception
	 * @throws InterruptedException    the interrupted exception
	 * @throws CryptoHashException     the crypto hash exception
	 */
	// returns the company
	public Company getCompany(String companyName) throws CouponSystemExceptions {
		long loggedInCompanyID = companyDao.getCompanyId(companyName);
		Company company = new Company();
		company = companyDao.getCompany(loggedInCompanyID);
		return company;
	}

	/**
	 * Sets the company dao.
	 *
	 * @param companyDao the new company dao
	 */
	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	/**
	 * Sets the coupon dao.
	 *
	 * @param couponDao the new coupon dao
	 */
	public void setCouponDao(CouponDao couponDao) {
		this.couponDao = couponDao;
	}

	@Override
	// not used
	public CouponClientFacade login(String name, String password, ClientType clientType) {
		return null;
	}

	/**
	 * This method activates the createCoupon method in the {@code CouponDaoDb}
	 * class while sending the received coupon parameter, eventually creating the
	 * coupon's record into the data base. The coupon is recorded as the coupon of
	 * the logged in company.
	 *
	 * @param coupon - the coupon object that is filled with the fields of the
	 *               coupon to eventually add to the data base.
	 * @throws CouponSystemExceptions
	 */
	public void createCoupon(Coupon coupon) throws CouponSystemExceptions {

		couponDao.createCoupon(coupon);
	}

	/**
	 * This method activates the removeCoupon method in the {@code CouponDaoDb}
	 * class while sending the received coupon parameter, eventually removing the
	 * coupon's record from the data base.
	 *
	 * @param coupon - the coupon object that is filled with the fields of the
	 *               coupon to eventually remove from the data base.
	 * @throws CouponSystemExceptions
	 */
	public void removeCoupon(Coupon coupon) throws CouponSystemExceptions {

		couponDao.removeCoupon(coupon);
	}

	/**
	 * This method activates the updateCoupon method in the {@code CouponDaoDb}
	 * class while sending the received coupon parameter, eventually updating the
	 * coupon's record in the data base.
	 *
	 * @param coupon - the coupon object that is filled with the fields of the
	 *               coupon to eventually update in data base.
	 * @throws CouponSystemExceptions
	 */
	public void updateCoupon(Coupon coupon) throws CouponSystemExceptions {

		couponDao.updateCoupon(coupon);
	}

	/**
	 * This method activates the getAllCoupons method in the {@code CouponDaoDb},
	 * eventually getting and returning an Array List of Coupon objects.
	 *
	 * @return {@code CouponDaoDb.getCoupons()} - returning an Array List of all the
	 *         Coupon objects of the logged in company from records in the data base
	 * @throws CouponSystemExceptions
	 */
	public Collection<Coupon> getAllCoupons() throws CouponSystemExceptions {

		Collection<Coupon> allCoupons = couponDao.getAllCoupons();
		return allCoupons;
	}

	/**
	 * This method activates the getCoupon method in the {@code CouponDaoDb} class
	 * while sending the received coupon id parameter, eventually getting and
	 * returning a Coupon object.
	 *
	 * @param id - the id of the coupon, like it's on the data base, so that we will
	 *           receive a coupon object from the record in the data base by it's
	 *           id.
	 * @return {@code CouponDaoDb.getCoupon(id)} - returning a Coupon object from
	 *         the coupon's record in the data base.
	 * @throws CouponSystemExceptions
	 */
	public Coupon getCoupon(long id) throws CouponSystemExceptions {

		return couponDao.getCoupon(id);
	}

	/**
	 * Get all coupons by ID and remove if coupon type is not the requested ID.
	 *
	 * @param id the id
	 * @return the coupon by id
	 * @throws CouponSystemExceptions
	 */
	public Collection<Coupon> getCouponById(long id) throws CouponSystemExceptions {
		Collection<Coupon> myCoupon = this.getAllCoupons();
		for (Iterator<Coupon> iterator = myCoupon.iterator(); iterator.hasNext();) {
			Coupon coupon = iterator.next();
			if (coupon.getId() != id) {
				iterator.remove();
			}
		}
		return myCoupon;
	}

	/**
	 * Get all coupons by type and remove if coupon type is not the requested type.
	 *
	 * @param couponType the coupon type
	 * @return the all coupons by type
	 * @throws CouponSystemExceptions
	 */
	public Collection<Coupon> getAllCouponsByType(CouponType couponType) throws CouponSystemExceptions {
		Collection<Coupon> allCoupons = this.getAllCoupons();
		for (Iterator<Coupon> iterator = allCoupons.iterator(); iterator.hasNext();) {
			Coupon coupon = iterator.next();
			if (coupon.getType() != couponType) {
				iterator.remove();
			}
		}
		return allCoupons;
	}

	/**
	 * Get all coupons by price and remove if price is higher than requested.
	 *
	 * @param price the price
	 * @return the all coupons by max price
	 * @throws CouponSystemExceptions
	 */
	public Collection<Coupon> getAllCouponsByMaxPrice(double price) throws CouponSystemExceptions {
		Collection<Coupon> allCoupons = this.getAllCoupons();
		for (Iterator<Coupon> iterator = allCoupons.iterator(); iterator.hasNext();) {
			Coupon coupon = iterator.next();
			if (coupon.getPrice() > price) {
				iterator.remove();
			}
		}
		return allCoupons;
	}

	/**
	 * Get all coupons by date and remove if end date is greater than requested
	 * 
	 *
	 * @param date the date
	 * @return the all coupons by max date
	 * @throws CouponSystemExceptions
	 */
	public Collection<Coupon> getAllCouponsByMaxDate(String date) throws CouponSystemExceptions {
		Collection<Coupon> allCoupons = this.getAllCoupons();
		Date maxDate = Date.valueOf(date);
		if (maxDate != null) {
			for (Iterator<Coupon> iterator = allCoupons.iterator(); iterator.hasNext();) {
				Coupon coupon = iterator.next();
				if (coupon.getEndDate().after(maxDate)) {
					iterator.remove();
				}
			}
			return allCoupons;
		} else {
			return null;
		}
	}

}
