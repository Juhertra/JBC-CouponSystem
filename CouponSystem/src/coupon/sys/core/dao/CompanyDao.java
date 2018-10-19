package coupon.sys.core.dao;

import java.sql.SQLException;
import java.util.Collection;

import coupon.sys.core.beans.Company;
import coupon.sys.core.beans.Coupon;
import coupon.sys.core.exceptions.DBDAOException;
import coupon.sys.core.exceptions.ConnectionPoolException;
import coupon.sys.core.exceptions.CouponSystemExceptions;
import coupon.sys.core.exceptions.CryptoHashException;

/**
 * The interface that {@code CompanyDaoDb} implements from. it has all the
 * crucial company methods for communicating with the data base.
 * 
 * @author Julio Hernan Trajtemberg
 * @version 1.0 September 18, 2018.
 */
public interface CompanyDao {

	/**
	 * Creates the company.
	 *
	 * @param company the company
	 * @throws DBDAOException   the company daodb exception
	 * @throws ConnectionPoolException the connection pool exception
	 * @throws InterruptedException    the interrupted exception
	 * @throws SQLException            the SQL exception
	 * @throws CouponSystemExceptions
	 */
	// - Abstract company C.R.U.D methods related actions.
	public void createCompany(Company company) throws CouponSystemExceptions;

	/**
	 * Removes the company.
	 *
	 * @param company the company
	 * @throws DBDAOException   the company daodb exception
	 * @throws ConnectionPoolException the connection pool exception
	 * @throws InterruptedException    the interrupted exception
	 * @throws CouponSystemExceptions
	 */
	public void removeCompany(Company company) throws CouponSystemExceptions;

	/**
	 * Update company.
	 *
	 * @param company the company
	 * @throws DBDAOException   the company daodb exception
	 * @throws ConnectionPoolException the connection pool exception
	 * @throws InterruptedException    the interrupted exception
	 * @throws CouponSystemExceptions
	 */
	public void updateCompany(Company company) throws CouponSystemExceptions;

	/**
	 * Gets the company.
	 *
	 * @param id the id
	 * @return the company
	 * @throws DBDAOException   the company daodb exception
	 * @throws ConnectionPoolException the connection pool exception
	 * @throws InterruptedException    the interrupted exception
	 * @throws CryptoHashException     the crypto hash exception
	 * @throws CouponSystemExceptions
	 */
	// - Abstract methods for company related information getters
	public Company getCompany(long id) throws CouponSystemExceptions;

	/**
	 * Gets the all companies.
	 *
	 * @return the all companies
	 * @throws DBDAOException   the company daodb exception
	 * @throws ConnectionPoolException the connection pool exception
	 * @throws InterruptedException    the interrupted exception
	 * @throws CryptoHashException     the crypto hash exception
	 * @throws CouponSystemExceptions
	 */
	public Collection<Company> getAllCompanies() throws CouponSystemExceptions;

	/**
	 * Gets the company id.
	 *
	 * @param name the name
	 * 
	 * @return the company id
	 * @throws DBDAOException   the company daodb exception
	 * @throws ConnectionPoolException the connection pool exception
	 * @throws InterruptedException    the interrupted exception
	 * @throws CouponSystemExceptions
	 */
	public long getCompanyId(String name) throws CouponSystemExceptions;

	/**
	 * Gets the coupons.
	 *
	 * @param id the id
	 * 
	 * @return the coupons
	 * @throws DBDAOException   the company daodb exception
	 * @throws ConnectionPoolException the connection pool exception
	 * @throws InterruptedException    the interrupted exception
	 * @throws CouponSystemExceptions
	 */
	public Collection<Coupon> getCoupons(long id) throws CouponSystemExceptions;

	/**
	 * Login.
	 *
	 * @param name     the name
	 * 
	 * @param password the password
	 * 
	 * @return the long
	 * @throws DBDAOException   the company daodb exception
	 * @throws ConnectionPoolException the connection pool exception
	 * @throws InterruptedException    the interrupted exception
	 * @throws CryptoHashException     the crypto hash exception
	 * @throws CouponSystemExceptions
	 */
	// - Abstract login method, for login later use.
	public Long login(String name, String password) throws CouponSystemExceptions;

}
