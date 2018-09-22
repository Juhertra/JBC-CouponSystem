package coupon.sys.core.dao;

import java.sql.SQLException;
import java.util.Collection;

import coupon.sys.core.beans.Coupon;
import coupon.sys.core.beans.Customer;
import coupon.sys.core.exceptions.ConnectionPoolException;
import coupon.sys.core.exceptions.CryptoHashException;
import coupon.sys.core.exceptions.CustomerDaoDbException;

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
	 * @param customer
	 *            the customer
	 * 
	 * @throws CustomerDaoDbException
	 *             the customer dao db exception
	 * @throws ConnectionPoolException
	 *             the connection pool exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	// - Abstract coupon C.R.U.D methods related actions.
	public void createCustomer(Customer customer)
			throws CustomerDaoDbException, ConnectionPoolException, InterruptedException, SQLException;

	/**
	 * Removes the customer.
	 *
	 * @param customer
	 *            the customer
	 * 
	 * @throws CustomerDaoDbException
	 *             the customer dao db exception
	 * @throws ConnectionPoolException
	 *             the connection pool exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public void removeCustomer(Customer customer)
			throws CustomerDaoDbException, ConnectionPoolException, InterruptedException;

	/**
	 * Update customer.
	 *
	 * @param customer
	 *            the customer
	 * 
	 * @throws CustomerDaoDbException
	 *             the customer dao db exception
	 * @throws ConnectionPoolException
	 *             the connection pool exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public void updateCustomer(Customer customer)
			throws CustomerDaoDbException, ConnectionPoolException, InterruptedException;

	/**
	 * Gets the customer.
	 *
	 * @param id
	 *            the id
	 * 
	 * @return the customer
	 * @throws CustomerDaoDbException
	 *             the customer dao db exception
	 * @throws ConnectionPoolException
	 *             the connection pool exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws CryptoHashException
	 *             the crypto hash exception
	 */
	// - Abstract methods for company related information getters and setters
	public Customer getCustomer(long id)
			throws CustomerDaoDbException, ConnectionPoolException, InterruptedException, CryptoHashException;

	/**
	 * Gets the all customers.
	 *
	 * @return the all customers
	 * @throws CustomerDaoDbException
	 *             the customer dao db exception
	 * @throws ConnectionPoolException
	 *             the connection pool exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws CryptoHashException
	 *             the crypto hash exception
	 */
	public Collection<Customer> getAllCustomers()
			throws CustomerDaoDbException, ConnectionPoolException, InterruptedException, CryptoHashException;

	/**
	 * Gets the coupons.
	 *
	 * @param customer
	 *            the customer
	 * 
	 * @return the coupons
	 * @throws CustomerDaoDbException
	 *             the customer dao db exception
	 * @throws ConnectionPoolException
	 *             the connection pool exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public Collection<Coupon> getCoupons(Customer customer)
			throws CustomerDaoDbException, ConnectionPoolException, InterruptedException;

	/**
	 * Gets the customer id.
	 *
	 * @param customerName
	 *            the customer name
	 * 
	 * @return the customer id
	 * @throws CustomerDaoDbException
	 *             the customer dao db exception
	 * @throws ConnectionPoolException
	 *             the connection pool exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	long getCustomerId(String customerName)
			throws CustomerDaoDbException, ConnectionPoolException, InterruptedException, SQLException;

	/**
	 * Adds the coupon to customer.
	 *
	 * @param coupon
	 *            the coupon
	 * 
	 * @param customer
	 *            the customer
	 * @throws ConnectionPoolException
	 *             the connection pool exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws CustomerDaoDbException
	 *             the customer dao db exception
	 */
	void addCouponToCustomer(Coupon coupon, Customer customer)
			throws ConnectionPoolException, InterruptedException, CustomerDaoDbException;

	/**
	 * Login.
	 *
	 * @param name
	 *            the name
	 * 
	 * @param password
	 *            the password
	 * 
	 * @return the long
	 * @throws CustomerDaoDbException
	 *             the customer dao db exception
	 * @throws ConnectionPoolException
	 *             the connection pool exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws CryptoHashException
	 *             the crypto hash exception
	 */
	// - Abstract login method, for login later use.
	public Long login(String name, String password)
			throws CustomerDaoDbException, ConnectionPoolException, InterruptedException, CryptoHashException;

}
