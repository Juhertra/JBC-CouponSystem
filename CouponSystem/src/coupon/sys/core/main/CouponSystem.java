package coupon.sys.core.main;

import java.util.Timer;

import coupon.sys.core.beans.Company;
import coupon.sys.core.beans.Customer;
import coupon.sys.core.connectionPool.ConnectionPool;
import coupon.sys.core.dao.CompanyDao;
import coupon.sys.core.dao.CouponDao;
import coupon.sys.core.dao.CustomerDao;
import coupon.sys.core.dao.db.CompanyDaoDb;
import coupon.sys.core.dao.db.CouponDaoDb;
import coupon.sys.core.dao.db.CustomerDaoDb;
import coupon.sys.core.exceptions.CompanyDaodbException;
import coupon.sys.core.exceptions.ConnectionPoolException;
import coupon.sys.core.exceptions.CouponSystemExceptions;
import coupon.sys.core.exceptions.CryptoHashException;
import coupon.sys.core.exceptions.CustomerDaoDbException;
import coupon.sys.core.facade.AdminFacade;
import coupon.sys.core.facade.ClientType;
import coupon.sys.core.facade.CompanyFacade;
import coupon.sys.core.facade.CouponClientFacade;
import coupon.sys.core.facade.CustomerFacade;
import coupon.sys.core.utils.DataBaseProperties;

// TODO: Auto-generated Javadoc
/**
 * This is the singleton pattern coupon system of the project, that returns the
 * right facade object if the login attempt is successful. And by that the user
 * can check or make actions in the program. Also starts the Daily timer task 
 * thread for deleting unnecessary coupons. And is able to shut down the system.
 * 
 * @author Julio Hernan Trajtemberg
 * @version 1.0 September 18, 2018.
 *
 */
public class CouponSystem {

	/** The coupon system instance. */
	private static CouponSystem couponSystemInstance = new CouponSystem();
	
	/** The connection pool. */
	private ConnectionPool connectionPool;
	
	/** The company dao. */
	private CompanyDao companyDao = new CompanyDaoDb();
	
	/** The coupon dao. */
	private CouponDao couponDao = new CouponDaoDb();
	
	/** The customer dao. */
	private CustomerDao customerDao = new CustomerDaoDb();
	
	/** The timer. */
	// scheduler for the daily cleaner thread task
	private Timer timer;

	/**
	 * Instantiates a new coupon system.
	 */
	// private c'tor 'hides' the public default one. CouponSystem is a singleton
	private CouponSystem() {
		// get/create the connection pool
			try {
				connectionPool = ConnectionPool.getInstance();
			} catch (ConnectionPoolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		// start the daily cleaner thread
		startTimer();
	}
	
	/**
	 * (singleton) - if no instance , create it only once.
	 * 
	 * @return couponSystemInstance
	 */
	public synchronized static CouponSystem getInstance() {
		if (couponSystemInstance == null) {
			// create a CouponSystem only once
			couponSystemInstance = new CouponSystem();
		}
		return couponSystemInstance;
	}

	/**
	 * Graceful shutdown.
	 *
	 * @throws ConnectionPoolException the connection pool exception
	 */
	public void shutdown() throws ConnectionPoolException {
		try {
			connectionPool.closeAllConnections();
		} catch (ConnectionPoolException e) {
			throw new ConnectionPoolException("sql error returned from pool", e);
		}
		System.exit(0);
	}

	/**
	 * Start timer.
	 */
	// will start cleaner thread ( at c'tor , or after user stopped it manually )
	public void startTimer() {
		// start daily thread to clean old coupons
		System.out.println("Thread scheduler started. interval = " + DataBaseProperties.getTheadIntervalMinutes() + " minutes.");
		timer = new Timer();
		// 60000 msec = 1 min
		// minutes of timer specified in DataBaseProperties file
		timer.scheduleAtFixedRate(new DailyCouponExpirationTask(couponDao), 30, (DataBaseProperties.getTheadIntervalMinutes() * 60000));
	}

	/**
	 * Stop timer.
	 */
	// stop the cleaner thread
	public void stopTimer() {
		timer.cancel();
		System.out.println("Thread stopped by user request.");
	}

	/**
	 * Login.
	 *
	 * @param name the name
	 * @param password the password
	 * @param clientType the client type
	 * @return the coupon client facade
	 * @throws CouponSystemExceptions the coupon system exceptions
	 * @throws CryptoHashException the crypto hash exception
	 * @throws CustomerDaoDbException the customer dao db exception
	 * @throws ConnectionPoolException the connection pool exception
	 * @throws InterruptedException the interrupted exception
	 * @throws CompanyDaodbException the company daodb exception
	 */
	// global authentication method. depends on the ClientType
	public CouponClientFacade login(String name, String password, ClientType clientType) throws CouponSystemExceptions, CryptoHashException, CustomerDaoDbException, ConnectionPoolException, InterruptedException, CompanyDaodbException {
		// login of Admin
		if (clientType == ClientType.ADMIN) {
			if (password.equals(DataBaseProperties.getPassword()) && name.equals(DataBaseProperties.getUser())) {
				// admin auth success, init AdminFacade and passing the DAO's
				AdminFacade adminFacade = new AdminFacade();
				return adminFacade;
			}
		}

		// login of Company
		if (clientType == ClientType.COMPANY)

		{
			// success auth will return the customer ID
			Long companyId = companyDao.login(name, password);
			if (companyId != null && companyId != 0) {
				// construct a customer with those params
				Company company = new Company();
				company.setId(companyId);
				company.setName(name);
				company.setPassword(password);
				// create a CustomerFacade, referring to this company
				CompanyFacade companyFacade = new CompanyFacade();
				companyFacade.setCompanyDao(companyDao);
				companyFacade.setCouponDao(couponDao);
				return companyFacade;
			}
		}

		// login of Customer
		if (clientType == ClientType.CUSTOMER) {
			// success auth will return the customer ID
			Long customerId = customerDao.login(name, password);
			if (customerId != null && customerId != 0) {
				// construct a customer with those params
				Customer customer = new Customer();
				customer.setId(customerId);
				customer.setName(name);
				customer.setPassword(password);
				// create a CustomerFacade
				CustomerFacade customerFacade = new CustomerFacade();
				// customerFacade need to ref that specific customer ( 1 per client )
				customerFacade.setCustomer(customer);
				// and ref the DAOs ( customer + coupons )
				customerFacade.setCustomerDao(customerDao);
				customerFacade.setCouponDao(couponDao);
				return customerFacade;
			}
		}
		return null;
	}

}
