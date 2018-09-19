package coupon.sys.core.dao;

import java.sql.SQLException;
import java.util.Collection;

import coupon.sys.core.beans.Company;
import coupon.sys.core.beans.Coupon;
import coupon.sys.core.exceptions.CompanyDaodbException;
import coupon.sys.core.exceptions.ConnectionPoolException;
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
	 * @throws CompanyDaodbException the company daodb exception
	 * @throws ConnectionPoolException the connection pool exception
	 * @throws InterruptedException the interrupted exception
	 * @throws SQLException the SQL exception
	 */
	// - Abstract company C.R.U.D methods related actions.
	public void createCompany(Company company)
			throws CompanyDaodbException, ConnectionPoolException, InterruptedException, SQLException;

	/**
	 * Removes the company.
	 *
	 * @param company the company
	 * @throws CompanyDaodbException the company daodb exception
	 * @throws ConnectionPoolException the connection pool exception
	 * @throws InterruptedException the interrupted exception
	 */
	public void removeCompany(Company company)
			throws CompanyDaodbException, ConnectionPoolException, InterruptedException;

	/**
	 * Update company.
	 *
	 * @param company the company
	 * @throws CompanyDaodbException the company daodb exception
	 * @throws ConnectionPoolException the connection pool exception
	 * @throws InterruptedException the interrupted exception
	 */
	public void updateCompany(Company company)
			throws CompanyDaodbException, ConnectionPoolException, InterruptedException;

	/**
	 * Gets the company.
	 *
	 * @param id the id
	 * @return the company
	 * @throws CompanyDaodbException the company daodb exception
	 * @throws ConnectionPoolException the connection pool exception
	 * @throws InterruptedException the interrupted exception
	 * @throws CryptoHashException the crypto hash exception
	 */
	// - Abstract methods for company related information getters
	public Company getCompany(long id)
			throws CompanyDaodbException, ConnectionPoolException, InterruptedException, CryptoHashException;

	/**
	 * Gets the all companies.
	 *
	 * @return the all companies
	 * @throws CompanyDaodbException the company daodb exception
	 * @throws ConnectionPoolException the connection pool exception
	 * @throws InterruptedException the interrupted exception
	 * @throws CryptoHashException the crypto hash exception
	 */
	public Collection<Company> getAllCompanies()
			throws CompanyDaodbException, ConnectionPoolException, InterruptedException, CryptoHashException;

	/**
	 * Gets the company id.
	 *
	 * @param name the name
	 * @return the company id
	 * @throws CompanyDaodbException the company daodb exception
	 * @throws ConnectionPoolException the connection pool exception
	 * @throws InterruptedException the interrupted exception
	 */
	public long getCompanyId(String name) throws CompanyDaodbException, ConnectionPoolException, InterruptedException;

	/**
	 * Gets the coupons.
	 *
	 * @param id the id
	 * @return the coupons
	 * @throws CompanyDaodbException the company daodb exception
	 * @throws ConnectionPoolException the connection pool exception
	 * @throws InterruptedException the interrupted exception
	 */
	public Collection<Coupon> getCoupons(long id)
			throws CompanyDaodbException, ConnectionPoolException, InterruptedException;

	/**
	 * Login.
	 *
	 * @param name the name
	 * @param password the password
	 * @return the long
	 * @throws CompanyDaodbException the company daodb exception
	 * @throws ConnectionPoolException the connection pool exception
	 * @throws InterruptedException the interrupted exception
	 * @throws CryptoHashException the crypto hash exception
	 */
	// - Abstract login method, for login later use.
	public Long login(String name, String password)
			throws CompanyDaodbException, ConnectionPoolException, InterruptedException, CryptoHashException;

}
