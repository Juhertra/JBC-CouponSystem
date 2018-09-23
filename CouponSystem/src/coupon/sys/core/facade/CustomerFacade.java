package coupon.sys.core.facade;

import java.util.Collection;
import java.util.Iterator;

import coupon.sys.core.beans.Coupon;
import coupon.sys.core.beans.CouponType;
import coupon.sys.core.beans.Customer;
import coupon.sys.core.dao.CouponDao;
import coupon.sys.core.dao.CustomerDao;
import coupon.sys.core.dao.db.CouponDaoDb;
import coupon.sys.core.dao.db.CustomerDaoDb;
import coupon.sys.core.exceptions.ConnectionPoolException;
import coupon.sys.core.exceptions.CouponDaoDbException;
import coupon.sys.core.exceptions.CustomerDaoDbException;

/**
 * The Class CustomerFacade.
 * 
 * Customer Facade provides the business logic for customers DAO's exists for
 * Customers/Coupons operations
 */
//
public class CustomerFacade implements CouponClientFacade {

	/** The customer dao. */
	private CustomerDao customerDao = new CustomerDaoDb();

	/** The coupon dao. */
	private CouponDao couponDao = new CouponDaoDb();

	/** The customer. */
	private Customer customer;

	/**
	 * Default public c'tor
	 * 
	 * Instantiates a new customer facade.
	 */
	public CustomerFacade() {
	}

	/**
	 * Gets the customer.
	 *
	 * @return the customer
	 */
	public Customer getCustomer() {
		return this.customer;
	}

	/**
	 * Set the Facade for this customer.
	 *
	 * @param customer
	 *            the new customer
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * Sets the customer dao.
	 *
	 * @param customerDao
	 *            the new customer dao
	 */
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
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
	// not in use, but needs to be implemented
	public CouponClientFacade login(String name, String password, ClientType clientType) {
		return null;
	}

	/**
	 * Gets the coupon by id.
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
	public Coupon getCouponById(long id) throws CouponDaoDbException, ConnectionPoolException, InterruptedException {
		return couponDao.getCoupon(id);
	}

	/**
	 * Gets the all purchased coupons.
	 *
	 * @return the all purchased coupons
	 * @throws CustomerDaoDbException
	 *             the customer dao db exception
	 * @throws ConnectionPoolException
	 *             the connection pool exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public Collection<Coupon> getAllPurchasedCoupons()
			throws CustomerDaoDbException, ConnectionPoolException, InterruptedException {
		return customerDao.getCoupons(this.customer);
	}

	/**
	 * Gets all and remove by comparing the CouponType.
	 * 
	 * Iterator.remove is the only safe way to modify a collection during iteration
	 *
	 * @param couponType
	 *            the coupon type
	 * @return the all purchased coupons by type
	 * @throws CustomerDaoDbException
	 *             the customer dao db exception
	 * @throws ConnectionPoolException
	 *             the connection pool exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public Collection<Coupon> getAllPurchasedCouponsByType(CouponType couponType)
			throws CustomerDaoDbException, ConnectionPoolException, InterruptedException {
		Collection<Coupon> allMyCoupons = customerDao.getCoupons(this.customer);
		for (Iterator<Coupon> iterator = allMyCoupons.iterator(); iterator.hasNext();) {
			Coupon coupon = iterator.next();
			if (coupon.getType() != couponType) {
				iterator.remove();
			}
		}
		return allMyCoupons;
	}

	/**
	 * Gets the all purchased coupons by price and remove if price is higher than
	 * requested.
	 * 
	 * @param price
	 *            the price
	 * @return the all purchased coupons by price
	 * @throws CustomerDaoDbException
	 *             the customer dao db exception
	 * @throws ConnectionPoolException
	 *             the connection pool exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public Collection<Coupon> getAllPurchasedCouponsByPrice(double price)
			throws CustomerDaoDbException, ConnectionPoolException, InterruptedException {
		Collection<Coupon> allMyCoupons = customerDao.getCoupons(this.customer);
		for (Iterator<Coupon> iterator = allMyCoupons.iterator(); iterator.hasNext();) {
			Coupon coupon = iterator.next();
			if (coupon.getPrice() > price) {
				iterator.remove();
			}
		}
		return allMyCoupons;
	}

	/**
	 * Gets the all available coupons that can be bought (those who are not mine
	 * already ) and ( amount > 0 ).
	 *
	 * @return the all available coupons
	 * @throws CustomerDaoDbException
	 *             the customer dao db exception
	 * @throws ConnectionPoolException
	 *             the connection pool exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws CouponDaoDbException
	 *             the coupon dao db exception
	 */
	public Collection<Coupon> getAllAvailableCoupons()
			throws CustomerDaoDbException, ConnectionPoolException, InterruptedException, CouponDaoDbException {
		// getting all coupons in system
		Collection<Coupon> allCoupons = couponDao.getAllCoupons();
		// get my coupons
		Collection<Coupon> myCoupons = customerDao.getCoupons(this.customer);
		// Subtract and store in a new var notMyCoupons
		allCoupons.removeAll(myCoupons);
		Collection<Coupon> notMyCoupons = allCoupons;
		for (Iterator<Coupon> iterator = notMyCoupons.iterator(); iterator.hasNext();) {
			Coupon coupon = iterator.next();
			// remove from list if out of stock
			if (coupon.getAmount() < 1) {
				iterator.remove();
			}
		}
		return notMyCoupons;
	}

	/**
	 * Gets the all available coupons by type.
	 *
	 * @param couponType
	 *            the coupon type
	 * @return the all available coupons by type
	 * @throws CustomerDaoDbException
	 *             the customer dao db exception
	 * @throws ConnectionPoolException
	 *             the connection pool exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws CouponDaoDbException
	 *             the coupon dao db exception
	 */
	public Collection<Coupon> getAllAvailableCouponsByType(CouponType couponType)
			throws CustomerDaoDbException, ConnectionPoolException, InterruptedException, CouponDaoDbException {
		Collection<Coupon> allAvailableCoupons = getAllAvailableCoupons();
		// list to populate with the matching coupons
		for (Iterator<Coupon> iterator = allAvailableCoupons.iterator(); iterator.hasNext();) {
			Coupon coupon = iterator.next();
			if (coupon.getType() != couponType) {
				iterator.remove();
			}
		}
		return allAvailableCoupons;
	}

	/**
	 * Gets the all available coupons by price.
	 *
	 * @param price
	 *            the price
	 * @return the all available coupons by price
	 * @throws CustomerDaoDbException
	 *             the customer dao db exception
	 * @throws ConnectionPoolException
	 *             the connection pool exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws CouponDaoDbException
	 *             the coupon dao db exception
	 */
	public Collection<Coupon> getAllAvailableCouponsByPrice(double price)
			throws CustomerDaoDbException, ConnectionPoolException, InterruptedException, CouponDaoDbException {
		Collection<Coupon> allAvailableCoupons = getAllAvailableCoupons();
		// list to populate with the matching coupons
		for (Iterator<Coupon> iterator = allAvailableCoupons.iterator(); iterator.hasNext();) {
			Coupon coupon = iterator.next();
			if (coupon.getPrice() > price) {
				iterator.remove();
			}
		}
		return allAvailableCoupons;
	}

	/**
	 * Purchase coupon.
	 * 
	 * Check if more than 1 available. Check that this customer doesn't already
	 * owned this coupon. Update amount in DB.
	 *
	 * @param coupon
	 *            the coupon
	 * @throws CouponDaoDbException
	 *             the coupon dao db exception
	 * @throws ConnectionPoolException
	 *             the connection pool exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws CustomerDaoDbException
	 *             the customer dao db exception
	 */
	public void purchaseCoupon(Coupon coupon)
			throws CouponDaoDbException, ConnectionPoolException, InterruptedException, CustomerDaoDbException {
		// getting real-time coupon amount from DB
		Coupon couponFromDB;
		try {
			couponFromDB = couponDao.getCoupon(coupon.getId());

			if (couponFromDB == null) {
				throw new CouponDaoDbException("Not available coupons");
			}
			if (couponFromDB.getAmount() <= 0) {
				throw new CouponDaoDbException("coupon amount is 0.");
			}
			// and not purchased already
			if (getAllPurchasedCoupons().contains(couponFromDB)) {
				throw new CouponDaoDbException("coupon already owned by customer " + getCustomer().getName());
			}

			customerDao.addCouponToCustomer(couponFromDB, this.customer);
			System.out.println("coupon purchased by customer " + getCustomer().getName());
			// decrease amount
			couponFromDB.setAmount(couponFromDB.getAmount() - 1);
			couponDao.updateCoupon(couponFromDB);
			System.out.println(couponFromDB);
		} catch (Exception e) {
			throw new CouponDaoDbException("coupon new amount updated in db for Customer: " + getCustomer().getName(), e);
		}
		
	}
}
