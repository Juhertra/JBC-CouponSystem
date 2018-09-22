package coupon.sys.core.facade;

import java.sql.SQLException;
import java.util.Collection;

import coupon.sys.core.beans.Company;
import coupon.sys.core.beans.Customer;
import coupon.sys.core.dao.CompanyDao;
import coupon.sys.core.dao.CustomerDao;
import coupon.sys.core.dao.db.CompanyDaoDb;
import coupon.sys.core.dao.db.CustomerDaoDb;
import coupon.sys.core.exceptions.CompanyDaodbException;
import coupon.sys.core.exceptions.ConnectionPoolException;
import coupon.sys.core.exceptions.CryptoHashException;
import coupon.sys.core.exceptions.CustomerDaoDbException;

/**
 * This class is a part of the facade pattern layer. The class which manages the
 * admin user's request for using methods with admin functionality after a
 * successful login. The class uses the methods of the {@code CompanyDaoDb} and
 * the {@code CustomerDaoDb} classes - which communicates with the data base. In
 * addition, this class also catches any related exceptions that might occur.
 * 
 * @author Julio Hernan Trajtemberg
 * @version 1.0 September 18, 2018.
 */
public class AdminFacade implements CouponClientFacade {

	/** The customer dao. */
	private CustomerDao customerDao = new CustomerDaoDb();

	/** The company dao. */
	private CompanyDao companyDao = new CompanyDaoDb();

	/**
	 * Simple C'tor when no object needed.
	 */
	public AdminFacade() {

	}

	/**
	 * Not implemented, login was implemented at {@code CouponSystem} class.
	 *
	 * @param name
	 *            the name
	 * @param password
	 *            the password
	 * @param clientType
	 *            the client type
	 * @return the coupon client facade
	 */
	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) {

		return null;
	}

	/**
	 * This method activates the createCompany method in the {@code CompanyDaoDb}
	 * class while sending the received company parameter, eventually creating the
	 * company's record into the data base.
	 *
	 * @param company
	 *            - the company object that is filled with the fields of the company
	 *            to eventually add to the data base.
	 * @throws CompanyDaodbException
	 *             the company daodb exception
	 */
	public void createCompany(Company company) throws CompanyDaodbException {

		try {
			companyDao.createCompany(company);
		} catch (CompanyDaodbException | ConnectionPoolException | InterruptedException | SQLException e) {
			throw new CompanyDaodbException("Failed to create Company from Admin Facadde", e);
		}
	}

	/**
	 * This method activates the removeCompany method in the {@code CompanyDaoDb}
	 * class while sending the received company parameter, eventually removing the
	 * company's record from the data base.
	 *
	 * @param company
	 *            - the company object that is filled with the fields of the company
	 *            to eventually add to the data base.
	 * @throws CompanyDaodbException
	 *             the company daodb exception
	 */
	public void removeCompany(Company company) throws CompanyDaodbException {

		try {
			companyDao.removeCompany(company);
		} catch (CompanyDaodbException | ConnectionPoolException | InterruptedException e) {
			throw new CompanyDaodbException("Failed to remove Company from Admin Facadde", e);
		}
	}

	/**
	 * This method activates the updateCompany method in the {@code CompanyDaoDb}
	 * class while sending the received company parameter, eventually updating the
	 * company's record in the data base.
	 *
	 * @param company
	 *            - the company object that is filled with the fields of the company
	 *            to eventually add to the data base.
	 * @throws CompanyDaodbException
	 *             the company daodb exception
	 */
	public void updateCompany(Company company) throws CompanyDaodbException {

		try {
			companyDao.updateCompany(company);
		} catch (CompanyDaodbException | ConnectionPoolException | InterruptedException e) {
			throw new CompanyDaodbException("Failed to Update Company from Admin Facadde", e);
		}
	}

	/**
	 * This method activates the getAllCompanies method in the {@code CompanyDaoDb}
	 * class eventually getting and returning a Array List of Company objects from
	 * the records in the data base that represents all of the companies in the
	 * system.
	 *
	 * @return Collection of Company - collection of all the companies in the system
	 *         in Array list of company objects that we receive from the records of
	 *         the data base.
	 * @throws CompanyDaodbException
	 *             the company daodb exception
	 * @throws ConnectionPoolException
	 *             the connection pool exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws CryptoHashException
	 *             the crypto hash exception
	 */
	public Collection<Company> getAllCompanies()
			throws CompanyDaodbException, ConnectionPoolException, InterruptedException, CryptoHashException {

		if (companyDao == null) {
			return null;
		}
		return companyDao.getAllCompanies();
	}

	/**
	 * Helper method to ask DAO for the ID of a given name, then use the ID to get
	 * from DB.
	 *
	 * @param companyName
	 *            - Company name to search for it's ID
	 * @return CompanyID by searching the company name
	 * @throws CompanyDaodbException
	 *             the company daodb exception
	 * @throws ConnectionPoolException
	 *             the connection pool exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws CryptoHashException
	 *             the crypto hash exception
	 */
	public Company getCompany(String companyName)
			throws CompanyDaodbException, ConnectionPoolException, InterruptedException, CryptoHashException {

		long companyId = companyDao.getCompanyId(companyName);
		Company company = new Company();
		company = companyDao.getCompany(companyId);
		return company;
	}

	/**
	 * Helper methods to ask DAO for the company by ID.
	 *
	 * @param companyId
	 *            - To a search company by its ID
	 * @return Company ID
	 * @throws CompanyDaodbException
	 *             the company daodb exception
	 * @throws ConnectionPoolException
	 *             the connection pool exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws CryptoHashException
	 *             the crypto hash exception
	 */
	public Company getCompanyByID(long companyId)
			throws CompanyDaodbException, ConnectionPoolException, InterruptedException, CryptoHashException {

		return companyDao.getCompany(companyId);
	}

	/**
	 * This method activates the updateCustomer method in the {@code CustomerDaoDb}
	 * class while sending the received customer parameter, eventually updating the
	 * customer's record in the data base.
	 *
	 * @param customer
	 *            - the customer object that is filled with the fields of the
	 *            customer to eventually add to the data base.
	 * @throws CustomerDaoDbException
	 *             the customer dao db exception
	 * @throws ConnectionPoolException
	 *             the connection pool exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public void updateCustomer(Customer customer)
			throws CustomerDaoDbException, ConnectionPoolException, InterruptedException {

		customerDao.updateCustomer(customer);
	}

	/**
	 * This method activates the getAllCustomers method in the {@code CustomerDaoDb}
	 * class eventually getting and returning a Array List of customer objects from
	 * the records in the data base that represents all of the customers in the
	 * system.
	 *
	 * @return Collection of Customer - collection of all the customers in the
	 *         system in Array list of customer objects that we receive from the
	 *         records of the data base.
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
			throws CustomerDaoDbException, ConnectionPoolException, InterruptedException, CryptoHashException {

		Collection<Customer> allCustomers = customerDao.getAllCustomers();
		return allCustomers;
	}

	/**
	 * This method activates the createCustomer method in the {@code CustomerDaoDb}
	 * class while sending the received customer parameter, eventually creating the
	 * customer's record into the data base.
	 *
	 * @param customer
	 *            - the customer object that is filled with the fields of the
	 *            customer to eventually add to the data base.
	 * @throws CustomerDaoDbException
	 *             the customer dao db exception
	 * @throws ConnectionPoolException
	 *             the connection pool exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public void createCustomer(Customer customer)
			throws CustomerDaoDbException, ConnectionPoolException, InterruptedException, SQLException {

		customerDao.createCustomer(customer);
	}

	/**
	 * This method activates the removeCustomer method in the {@code CustomerDaoDb}
	 * class while sending the received customer parameter, eventually removing the
	 * customer's record from the data base.
	 *
	 * @param customer
	 *            - the customer object that is filled with the fields of the
	 *            customer to eventually add to the data base.
	 * @throws CustomerDaoDbException
	 *             the customer dao db exception
	 * @throws ConnectionPoolException
	 *             the connection pool exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public void removeCustomer(Customer customer)
			throws CustomerDaoDbException, ConnectionPoolException, InterruptedException {

		customerDao.removeCustomer(customer);
	}

	/**
	 * This method activates the getCustomer method in the {@code CustomerDaoDb}
	 * class while sending the received customer's id parameter, eventually getting
	 * and returning a Customer object from the customer's record in the data base.
	 *
	 * @param customerName
	 *            - the customer's name of the customer we wish to eventually get
	 *            from the data base.
	 * @return A Customer object, that represents the customer with the given name
	 *         from the records in the data base.
	 * @throws CustomerDaoDbException
	 *             the customer dao db exception
	 * @throws ConnectionPoolException
	 *             the connection pool exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws SQLException
	 *             the SQL exception
	 * @throws CryptoHashException
	 *             the crypto hash exception
	 */
	public Customer getCustomerByName(String customerName) throws CustomerDaoDbException, ConnectionPoolException,
			InterruptedException, SQLException, CryptoHashException {

		long customerId = customerDao.getCustomerId(customerName);
		Customer customer = new Customer();
		customer = customerDao.getCustomer(customerId);
		return customer;
	}

	/**
	 * This method activates the getCustomer method in the {@code CustomerDaoDb}
	 * class while sending the received customer's id parameter, eventually getting
	 * and returning a Customer object from the customer's record in the data base.
	 *
	 * @param customerId
	 *            the customer id
	 * @return {@code CustomerDaoDb.getCustomer(id)} - A Customer object, that
	 *         represents the customer with the given id from the records in the
	 *         data base.
	 * @throws CustomerDaoDbException
	 *             the customer dao db exception
	 * @throws ConnectionPoolException
	 *             the connection pool exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws CryptoHashException
	 *             the crypto hash exception
	 */
	public Customer getCustomer(long customerId)
			throws CustomerDaoDbException, ConnectionPoolException, InterruptedException, CryptoHashException {

		return customerDao.getCustomer(customerId);
	}
}
