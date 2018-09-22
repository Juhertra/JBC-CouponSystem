package coupon.sys.core.facade;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import coupon.sys.core.beans.Company;
import coupon.sys.core.beans.Coupon;
import coupon.sys.core.beans.CouponType;
import coupon.sys.core.dao.CompanyDao;
import coupon.sys.core.dao.CouponDao;
import coupon.sys.core.dao.db.CompanyDaoDb;
import coupon.sys.core.dao.db.CouponDaoDb;
import coupon.sys.core.exceptions.CompanyDaodbException;
import coupon.sys.core.exceptions.ConnectionPoolException;
import coupon.sys.core.exceptions.CouponDaoDbException;
import coupon.sys.core.exceptions.CryptoHashException;

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

	/**
	 * Simple C'tor when no object needed.
	 */
	public CompanyFacade() {

	}

	/**
	 * Gets the company.
	 *
	 * @param companyName
	 *            the company name
	 * @return the company
	 * @throws CompanyDaodbException
	 *             the company daodb exception
	 * @throws ConnectionPoolException
	 *             the connection pool exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws CryptoHashException
	 *             the crypto hash exception
	 */
	// returns the company
	public Company getCompany(String companyName)
			throws CompanyDaodbException, ConnectionPoolException, InterruptedException, CryptoHashException {
		long companyId = companyDao.getCompanyId(companyName);
		Company company = new Company();
		company = companyDao.getCompany(companyId);
		return company;
	}

	/**
	 * Sets the company dao.
	 *
	 * @param companyDao
	 *            the new company dao
	 */
	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	/**
	 * Sets the coupon dao.
	 *
	 * @param couponDao
	 *            the new coupon dao
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
	 * @param coupon
	 *            - the coupon object that is filled with the fields of the coupon
	 *            to eventually add to the data base.
	 * @throws CouponDaoDbException
	 *             the coupon dao db exception
	 * @throws ConnectionPoolException
	 *             the connection pool exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public void createCoupon(Coupon coupon)
			throws CouponDaoDbException, ConnectionPoolException, InterruptedException, SQLException {

		couponDao.createCoupon(coupon);
	}

	/**
	 * This method activates the removeCoupon method in the {@code CouponDaoDb}
	 * class while sending the received coupon parameter, eventually removing the
	 * coupon's record from the data base.
	 *
	 * @param coupon
	 *            - the coupon object that is filled with the fields of the coupon
	 *            to eventually remove from the data base.
	 * @throws CouponDaoDbException
	 *             the coupon dao db exception
	 * @throws ConnectionPoolException
	 *             the connection pool exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public void removeCoupon(Coupon coupon) throws CouponDaoDbException, ConnectionPoolException, InterruptedException {

		couponDao.removeCoupon(coupon);
	}

	/**
	 * This method activates the updateCoupon method in the {@code CouponDaoDb}
	 * class while sending the received coupon parameter, eventually updating the
	 * coupon's record in the data base.
	 *
	 * @param coupon
	 *            - the coupon object that is filled with the fields of the coupon
	 *            to eventually update in data base.
	 * @throws CouponDaoDbException
	 *             the coupon dao db exception
	 * @throws ConnectionPoolException
	 *             the connection pool exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public void updateCoupon(Coupon coupon) throws CouponDaoDbException, ConnectionPoolException, InterruptedException {

		couponDao.updateCoupon(coupon);
	}

	/**
	 * This method activates the getAllCoupons method in the {@code CouponDaoDb},
	 * eventually getting and returning an Array List of Coupon objects.
	 *
	 * @return {@code CouponDaoDb.getCoupons()} - returning an Array List of all the
	 *         Coupon objects of the logged in company from records in the data base
	 * @throws CouponDaoDbException
	 *             the coupon dao db exception
	 * @throws ConnectionPoolException
	 *             the connection pool exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public Collection<Coupon> getAllCoupons()
			throws CouponDaoDbException, ConnectionPoolException, InterruptedException {

		Collection<Coupon> allCoupons = couponDao.getAllCoupons();
		return allCoupons;
	}

	/**
	 * This method activates the getCoupon method in the {@code CouponDaoDb} class
	 * while sending the received coupon id parameter, eventually getting and
	 * returning a Coupon object.
	 *
	 * @param id
	 *            - the id of the coupon, like it's on the data base, so that we
	 *            will receive a coupon object from the record in the data base by
	 *            it's id.
	 * @return {@code CouponDaoDb.getCoupon(id)} - returning a Coupon object from
	 *         the coupon's record in the data base.
	 * @throws CouponDaoDbException
	 *             the coupon dao db exception
	 * @throws ConnectionPoolException
	 *             the connection pool exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public Coupon getCoupon(long id) throws CouponDaoDbException, ConnectionPoolException, InterruptedException {

		return couponDao.getCoupon(id);
	}

	/**
	 * Get all coupons by ID and remove if coupon type is not the requested ID.
	 *
	 * @param id
	 *            the id
	 * @return the coupon by id
	 * @throws CouponDaoDbException
	 *             the coupon dao db exception
	 * @throws ConnectionPoolException
	 *             the connection pool exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public Collection<Coupon> getCouponById(long id)
			throws CouponDaoDbException, ConnectionPoolException, InterruptedException {
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
	 * @param couponType
	 *            the coupon type
	 * @return the all coupons by type
	 * @throws CouponDaoDbException
	 *             the coupon dao db exception
	 * @throws ConnectionPoolException
	 *             the connection pool exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public Collection<Coupon> getAllCouponsByType(CouponType couponType)
			throws CouponDaoDbException, ConnectionPoolException, InterruptedException {
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
	 * @param price
	 *            the price
	 * @return the all coupons by max price
	 * @throws CouponDaoDbException
	 *             the coupon dao db exception
	 * @throws ConnectionPoolException
	 *             the connection pool exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public Collection<Coupon> getAllCouponsByMaxPrice(double price)
			throws CouponDaoDbException, ConnectionPoolException, InterruptedException {
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
	 * @param date
	 *            the date
	 * @return the all coupons by max date
	 * @throws CouponDaoDbException
	 *             the coupon dao db exception
	 * @throws ConnectionPoolException
	 *             the connection pool exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public Collection<Coupon> getAllCouponsByMaxDate(String date)
			throws CouponDaoDbException, ConnectionPoolException, InterruptedException {
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
