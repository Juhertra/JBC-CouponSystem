package coupon.sys.core.dao;

import java.sql.SQLException;
import java.util.Collection;

import coupon.sys.core.beans.Coupon;
import coupon.sys.core.beans.Customer;
import coupon.sys.core.exceptions.ConnectionPoolException;
import coupon.sys.core.exceptions.CouponSystemExceptions;
import coupon.sys.core.exceptions.CryptoHashException;
import coupon.sys.core.exceptions.FacadeException;

/**
 * The interface that {@code CustomerDaoDb} implements from. it has all the
 * crucial company methods for communicating with the data base.
 * 
 * @author Julio Hernan Trajtemberg
 * @version 1.0 September 18, 2018.
 *
 */
public interface CustomerDao {

	/**
	 * Creates the customer.
	 *
	 * @param customer the customer
	 * 
	 * @throws FacadeException  the customer dao db exception
	 * @throws ConnectionPoolException the connection pool exception
	 * @throws InterruptedException    the interrupted exception
	 * @throws SQLException            the SQL exception
	 * @throws CouponSystemExceptions
	 */
	// - Abstract coupon C.R.U.D methods related actions.
	public void createCustomer(Customer customer) throws CouponSystemExceptions;

	/**
	 * Removes the customer.
	 *
	 * @param customer the customer
	 * 
	 * @throws FacadeException  the customer dao db exception
	 * @throws ConnectionPoolException the connection pool exception
	 * @throws InterruptedException    the interrupted exception
	 */
	public void removeCustomer(Customer customer) throws CouponSystemExceptions;

	/**
	 * Update customer.
	 *
	 * @param customer the customer
	 * 
	 * @throws FacadeException  the customer dao db exception
	 * @throws ConnectionPoolException the connection pool exception
	 * @throws InterruptedException    the interrupted exception
	 */
	public void updateCustomer(Customer customer) throws CouponSystemExceptions;

	/**
	 * Gets the customer.
	 *
	 * @param id the id
	 * 
	 * @return the customer
	 * @throws FacadeException  the customer dao db exception
	 * @throws ConnectionPoolException the connection pool exception
	 * @throws InterruptedException    the interrupted exception
	 * @throws CryptoHashException     the crypto hash exception
	 */
	// - Abstract methods for company related information getters and setters
	public Customer getCustomer(long id) throws CouponSystemExceptions;

	/**
	 * Gets the all customers.
	 *
	 * @return the all customers
	 * @throws FacadeException  the customer dao db exception
	 * @throws ConnectionPoolException the connection pool exception
	 * @throws InterruptedException    the interrupted exception
	 * @throws CryptoHashException     the crypto hash exception
	 */
	public Collection<Customer> getAllCustomers() throws CouponSystemExceptions;

	/**
	 * Gets the coupons.
	 *
	 * @param customer the customer
	 * 
	 * @return the coupons
	 * @throws FacadeException  the customer dao db exception
	 * @throws ConnectionPoolException the connection pool exception
	 * @throws InterruptedException    the interrupted exception
	 */
	public Collection<Coupon> getCoupons(Customer customer) throws CouponSystemExceptions;

	/**
	 * Gets the customer id.
	 *
	 * @param customerName the customer name
	 * 
	 * @return the customer id
	 * @throws FacadeException  the customer dao db exception
	 * @throws ConnectionPoolException the connection pool exception
	 * @throws InterruptedException    the interrupted exception
	 * @throws SQLException            the SQL exception
	 */
	long getCustomerId(String customerName) throws CouponSystemExceptions;

	/**
	 * Adds the coupon to customer.
	 *
	 * @param coupon   the coupon
	 * 
	 * @param customer the customer
	 * @throws ConnectionPoolException the connection pool exception
	 * @throws InterruptedException    the interrupted exception
	 * @throws FacadeException  the customer dao db exception
	 */
	void addCouponToCustomer(Coupon coupon, Customer customer) throws CouponSystemExceptions;

	/**
	 * Login.
	 *
	 * @param name     the name
	 * 
	 * @param password the password
	 * 
	 * @return the long
	 * @throws FacadeException  the customer dao db exception
	 * @throws ConnectionPoolException the connection pool exception
	 * @throws InterruptedException    the interrupted exception
	 * @throws CryptoHashException     the crypto hash exception
	 */
	// - Abstract login method, for login later use.
	public Long login(String name, String password) throws CouponSystemExceptions;

}
