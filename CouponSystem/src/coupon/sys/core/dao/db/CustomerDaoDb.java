package coupon.sys.core.dao.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import coupon.sys.core.beans.Coupon;
import coupon.sys.core.beans.CouponType;
import coupon.sys.core.beans.Customer;
import coupon.sys.core.connectionPool.ConnectionPool;
import coupon.sys.core.dao.CustomerDao;
import coupon.sys.core.exceptions.ConnectionPoolException;
import coupon.sys.core.exceptions.CryptoHashException;
import coupon.sys.core.exceptions.CustomerDaoDbException;
import coupon.sys.core.utils.CryptoHashAlgorithms;

/**
 * This class is a part of the DAO layer. The class which communicates between
 * the customer's object related methods and the data base through sql queries.
 * 
 * @author Julio Hernan Trajtemberg
 * @version 1.0 September 18, 2018.
 *
 */
public class CustomerDaoDb implements CustomerDao {

	/** The connection pool. */
	private ConnectionPool connectionPool;

	/**
	 * Constructs the class that communicates with the db and initializes the pool
	 * variable with the Connection pool's instance.
	 */
	public CustomerDaoDb() {
		try {
			this.connectionPool = ConnectionPool.getInstance();
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Create Customer, the method checks if the company already exists if it
	 * doesn't it crates it and if does throws exception.
	 *
	 * @param customer
	 *            the customer
	 * @throws CustomerDaoDbException
	 *             the customer dao db exception
	 * @throws ConnectionPoolException
	 *             the connection pool exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	@Override
	public void createCustomer(Customer customer)
			throws CustomerDaoDbException, ConnectionPoolException, InterruptedException, SQLException {

		Connection connection = connectionPool.getConnection();
		String verifyCustomerQuery = "SELECT * FROM Customer";
		PreparedStatement verifyCustomerPstmt = connection.prepareStatement(verifyCustomerQuery);
		ResultSet resultSet = verifyCustomerPstmt.executeQuery();
		String customerName;
		Boolean custamerNameExists = false;

		while (resultSet.next()) {
			customerName = resultSet.getString("NAME");
			if (customerName.equals(customer.getName())) {
				custamerNameExists = true;
				System.out.println("Customer Already Exists");
				break;
			}
		}

		// Write customer to Customer table
		if (custamerNameExists == false) {
			try {
				System.out.println("Writing to DB - Creating Customer");
				String query = "INSERT INTO Customer (NAME, PASSWORD) VALUES (?, ?)";
				PreparedStatement pstmt = connection.prepareStatement(query);
				pstmt.setString(1, customer.getName());
				pstmt.setString(2, customer.getPassword());
				pstmt.executeUpdate();
				pstmt.close();
			} catch (SQLException e) {
				throw new CustomerDaoDbException("Failed to create new customer", e);
			} finally {
				connectionPool.returnConnection(connection);
				System.out.println("Customer created successfully.");
			}
		} else {
			throw new CustomerDaoDbException("Customer already exists.");
		}
	}

	/**
	 * This method removes the customer's record from the data base, using the
	 * received customer parameter and removes the customer from joined table.
	 *
	 * @param customer
	 *            the customer
	 * @throws CustomerDaoDbException
	 *             the customer dao db exception
	 * @throws ConnectionPoolException
	 *             the connection pool exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	@Override
	public void removeCustomer(Customer customer)
			throws CustomerDaoDbException, ConnectionPoolException, InterruptedException {

		Connection connection = connectionPool.getConnection();
		try {
			System.out.println("Writing to DB - Removing Customer");
			String query = "DELETE FROM Customer WHERE ID=?; DELETE FROM Customer_Coupon WHERE CUSTOMER_ID=?";
			PreparedStatement pstmt = connection.prepareStatement(query);

			// deleting from 2 tables (2 sql queries)
			pstmt.setLong(1, customer.getId());
			pstmt.setLong(2, customer.getId());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			throw new CustomerDaoDbException("Failed to remove the requested customer", e);
		} finally {
			connectionPool.returnConnection(connection);
			System.out.println("Customer removed successfully.");
		}

	}

	/**
	 * This method updates some columns in the customer's record in the data base,
	 * using the received customer parameter.
	 *
	 * @param customer
	 *            the customer
	 * @throws CustomerDaoDbException
	 *             the customer dao db exception
	 * @throws ConnectionPoolException
	 *             the connection pool exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	@Override
	public void updateCustomer(Customer customer)
			throws CustomerDaoDbException, ConnectionPoolException, InterruptedException {

		Connection connection = connectionPool.getConnection();
		try {
			System.out.println("Writing to DB - Updating Customer");
			String query = "UPDATE Customer SET NAME=?, PASSWORD=? WHERE ID=?";
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setString(1, customer.getName());
			pstmt.setString(2, customer.getPassword());
			pstmt.setLong(3, customer.getId());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			throw new CustomerDaoDbException("Failed to update the requested customer", e);
		} finally {
			connectionPool.returnConnection(connection);
			System.out.println("Customer updated successfully.");
		}

	}

	/**
	 * This method receives the customer's record from the data base, using the
	 * received customer's id parameter.
	 *
	 * @param id
	 *            the id
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
	@Override
	public Customer getCustomer(long id)
			throws CustomerDaoDbException, ConnectionPoolException, InterruptedException, CryptoHashException {
		Customer customer = new Customer();
		Connection connection = connectionPool.getConnection();
		try {
			System.out.println("Writing to DB - Selecting Customer");
			String query = "SELECT * FROM Customer WHERE ID=?";
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setLong(1, id);
			ResultSet resultSet = pstmt.executeQuery();

			// if line return : its a customer record from DB.
			while (resultSet.next()) {
				customer.setId(resultSet.getLong("ID"));
				customer.setName(resultSet.getString("NAME"));
				customer.setPassword(resultSet.getString("PASSWORD"));
			}
			pstmt.close();
		} catch (SQLException e) {
			throw new CustomerDaoDbException("Failed to get the requested customer", e);
		} finally {
			connectionPool.returnConnection(connection);
		}

		return customer;
	}

	/**
	 * This method receives all the customer's records of all the customers from the
	 * data base.
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
	@Override
	public Collection<Customer> getAllCustomers()
			throws CustomerDaoDbException, ConnectionPoolException, InterruptedException, CryptoHashException {
		List<Customer> allCustomers = new ArrayList<Customer>();
		Connection connection = connectionPool.getConnection();
		try {
			System.out.println("Writing to DB - Getting all Customers");
			String query = "SELECT * FROM Customer";
			PreparedStatement pstmt = connection.prepareStatement(query);
			ResultSet resultSet = pstmt.executeQuery();
			Customer customer = null;
			while (resultSet.next()) {
				customer = new Customer();
				customer.setId(resultSet.getLong("ID"));
				customer.setName(resultSet.getString("NAME"));
				customer.setPassword(resultSet.getString("PASSWORD"));
				allCustomers.add(customer);
			}
			System.out.println(customer);
			pstmt.close();
		} catch (SQLException e) {
			throw new CustomerDaoDbException("Failed to get all customers", e);
		} finally {

			connectionPool.returnConnection(connection);
			System.out.println("All customers presented successfully");
		}
		return allCustomers;
	}

	/**
	 * This method receives all the logged customer's coupons from the records of
	 * the data base.
	 *
	 * @param customer
	 *            the customer
	 * @return the coupons
	 * @throws CustomerDaoDbException
	 *             the customer dao db exception
	 * @throws ConnectionPoolException
	 *             the connection pool exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	@Override
	public Collection<Coupon> getCoupons(Customer customer)
			throws CustomerDaoDbException, ConnectionPoolException, InterruptedException {
		List<Coupon> getCoupons = new ArrayList<Coupon>();
		Connection connection = connectionPool.getConnection();

		try {
			String query = "SELECT Coupon.* FROM Coupon WHERE Customer_Coupon.CUSTOMER_ID = ?";
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setLong(1, customer.getId());
			ResultSet resultSet = pstmt.executeQuery();
			Coupon coupon = null;
			while (resultSet.next()) {
				coupon = new Coupon();
				coupon.setId(resultSet.getLong("ID"));
				coupon.setTitle(resultSet.getString("TITLE"));
				coupon.setStartDate(resultSet.getDate("START_DATE"));
				coupon.setStartDate(resultSet.getDate("END_DATE"));
				coupon.setAmount(resultSet.getInt("AMOUNT"));
				coupon.setType(CouponType.valueOf(resultSet.getString("TYPE")));
				coupon.setMessage(resultSet.getString("MESSAGE"));
				coupon.setPrice(resultSet.getDouble("PRICE"));
				coupon.setImage(resultSet.getString("IMAGE"));
				coupon.setCompanyId(resultSet.getLong("COMPANY_ID"));

				// add to list
				getCoupons.add(coupon);
			}
			pstmt.close();
		} catch (SQLException e) {
			throw new CustomerDaoDbException("Failed to get the requested coupon", e);
		} finally {
			connectionPool.returnConnection(connection);
		}
		return getCoupons;
	}

	/**
	 * A method for login into the system through passing user name and password.
	 * 
	 * The password is validated by converting the users input into system hash
	 * encryptions used and matching the hash with the one already written on the DB
	 *
	 * @param name
	 *            the name
	 * @param password
	 *            the password
	 * @return customerId - Returns connection to pool CustomerId - Returns Customer
	 *         ID
	 * @throws CustomerDaoDbException
	 *             the customer dao db exception
	 * @throws ConnectionPoolException
	 *             the connection pool exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws CryptoHashException
	 *             the crypto hash exception
	 */
	@Override
	public Long login(String name, String password)
			throws CustomerDaoDbException, ConnectionPoolException, InterruptedException, CryptoHashException {

		// Get connection from ConenctionPool
		Connection connection = connectionPool.getConnection();
		Long customerId = null;
		CryptoHashAlgorithms CHA = new CryptoHashAlgorithms();
		password = CHA.hashPassword(password.getBytes(), "SHA-256");
		try {
			String query = "SELECT ID FROM Customer WHERE NAME=? AND PASSWORD=?";
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, password);
			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {

				// if authorization is successful, the ID is returned
				customerId = resultSet.getLong(1);
			}
			pstmt.close();
		} catch (SQLException e) {
			throw new CustomerDaoDbException("Failed to login", e);
		} finally {
			connectionPool.returnConnection(connection);
		}
		return customerId;
	}

	/**
	 * Get customer from Customer table by ID.
	 *
	 * @param customerName
	 *            the customer name
	 * @return connection - Connection returns to pool customer.getId() - Returns
	 *         the customer ID of the company name requested
	 * @throws CustomerDaoDbException
	 *             the customer dao db exception
	 * @throws ConnectionPoolException
	 *             the connection pool exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	@Override
	public long getCustomerId(String customerName)
			throws CustomerDaoDbException, ConnectionPoolException, InterruptedException {

		// construct a Customer to return data
		Customer customer = new Customer();
		Connection connection = connectionPool.getConnection();
		try {
			String query = "SELECT ID FROM Customer WHERE NAME=?";
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setString(1, customerName);
			ResultSet resultSet = pstmt.executeQuery();

			// if a line returns : its a customer record from DB.
			while (resultSet.next()) {
				customer.setId(resultSet.getLong("ID"));
			}
			pstmt.close();
		} catch (SQLException e) {
			throw new CustomerDaoDbException("Failed to get customer ID for " + customer.getName(), e);
		} finally {
			connectionPool.returnConnection(connection);
		}
		return customer.getId();
	}

	/**
	 * This method adds a coupon to a customer. Customer ID and Coupon ID are added
	 * to customer_coupon join table
	 *
	 * @param coupon
	 *            the coupon
	 * @param customer
	 *            the customer
	 * @throws ConnectionPoolException
	 *             the connection pool exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws CustomerDaoDbException
	 *             the customer dao db exception
	 */
	@Override
	public void addCouponToCustomer(Coupon coupon, Customer customer)
			throws ConnectionPoolException, InterruptedException, CustomerDaoDbException {
		Connection connection = connectionPool.getConnection();
		try {
			String query = "INSERT INTO Customer_Coupon (CUSTOMER_ID, COUPON_ID) VALUES (?, ?)";
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setLong(1, customer.getId());
			pstmt.setLong(2, coupon.getId());
			pstmt.executeUpdate();
			pstmt.close();
			throw new CustomerDaoDbException("coupon purchased by " + customer.getName() + ".");
		} catch (SQLException e) {
			throw new CustomerDaoDbException("cannot create customer: ", e);
		} finally {
			connectionPool.returnConnection(connection);
		}
	}
}
