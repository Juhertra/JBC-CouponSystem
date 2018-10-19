package coupon.sys.core.dao.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import coupon.sys.core.beans.Company;
import coupon.sys.core.beans.Coupon;
import coupon.sys.core.beans.CouponType;
import coupon.sys.core.connectionPool.ConnectionPool;
import coupon.sys.core.dao.CompanyDao;
import coupon.sys.core.exceptions.DBDAOException;
import coupon.sys.core.exceptions.ConnectionPoolException;
import coupon.sys.core.exceptions.CouponSystemExceptions;
import coupon.sys.core.exceptions.CryptoHashException;
import coupon.sys.core.utils.CryptoHashAlgorithms;

/**
 * This class is a part of the DAO layer. The class which communicates between
 * the company's object related methods and the data base through sql queries.
 * 
 * @author Julio Hernan Trajtemberg
 * @version 1.0 September 18, 2018.
 *
 */
public class CompanyDaoDb implements CompanyDao {

	/** The connection pool. */
	private ConnectionPool connectionPool;
	/** The logged in company ID. */
	protected static long loggedInCompanyID = 0;

	/**
	 * Constructs the class that communicates with the db and initializes the pool
	 * variable with the Connection pool's instance.
	 */
	public CompanyDaoDb() {
		try {
			this.connectionPool = ConnectionPool.getInstance();
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CouponSystemExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Create Company, the method checks if the company already exists if it doesn't
	 * it crates it and if does throws exception.
	 *
	 * @param company the company
	 * @throws CouponSystemExceptions
	 */
	@Override
	public void createCompany(Company company) throws CouponSystemExceptions {

		// Search for company name on Company table
		Connection connection;
		Boolean companyNameExists;
		connection = connectionPool.getConnection();
		try {

			String verifyCompanyQuery = "SELECT * FROM Company";
			PreparedStatement verifyCompanyPstmt = connection.prepareStatement(verifyCompanyQuery);
			ResultSet resultSet = verifyCompanyPstmt.executeQuery();
			String companyName;
			companyNameExists = false;

			while (resultSet.next()) {
				companyName = resultSet.getString("NAME");
				if (companyName.equals(company.getName())) {
					companyNameExists = true;
					System.out.println("Customer Already Exists");
					break;
				}
			}
			// Write company to Company table
			if (companyNameExists == false) {

				System.out.println("Writing to DB - Creating company");
				String query = "INSERT INTO Company (NAME,PASSWORD,EMAIL) VALUES (?, ?, ?)";
				PreparedStatement pstmt = connection.prepareStatement(query);
				pstmt.setString(1, company.getName());
				pstmt.setString(2, company.getPassword());
				pstmt.setString(3, company.getEmail());
				pstmt.executeUpdate();
				pstmt.close();
				connectionPool.returnConnection(connection);
				System.out.println("Company created succesfully");
			}
		} catch (

		SQLException e) {
			throw new DBDAOException("Failed to create new company", e);
		} finally {
			connectionPool.returnConnection(connection);
			System.out.println("Company created succesfully");
		}
	}

	/**
	 * Remove company from Company table and from Company_Coupon table Return
	 * connection to pool.
	 *
	 * @param company the company
	 * @throws CouponSystemExceptions
	 */
	@Override
	public void removeCompany(Company company) throws CouponSystemExceptions {
		Connection connection = connectionPool.getConnection();
		try {
			System.out.println("Writing to DB - Removing Company");
			String query = "DELETE FROM Company WHERE ID=?";
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setLong(1, company.getId());
			pstmt.executeUpdate();
			pstmt.close();

			System.out.println("Writing to DB - Removing Company from joined table");
			String query2 = "DELETE FROM Company_Coupon WHERE Company_ID=?";
			PreparedStatement pstmt2 = connection.prepareStatement(query2);
			pstmt2.setLong(1, company.getId());
			pstmt2.executeUpdate();
			System.out.println("Company removed succesfully");
			pstmt2.close();
		} catch (SQLException e) {
			throw new DBDAOException("Failed to remove the requested company", e);
		} finally {
			connectionPool.returnConnection(connection);
		}

	}


	/**
	 * Update company password, email and name changes from Company table Return
	 * connection to pool.
	 *
	 * @param company the company
	 * @throws CouponSystemExceptions
	 */
	@Override
	public void updateCompany(Company company) throws CouponSystemExceptions {
		Connection connection = connectionPool.getConnection();
		try {
			String query = "UPDATE Company SET PASSWORD=?, EMAIL=? WHERE NAME=?";
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setString(1, company.getPassword());
			pstmt.setString(2, company.getEmail());
			pstmt.setString(3, company.getName());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			throw new DBDAOException("Failed to update the requested company", e);
		} finally {
			connectionPool.returnConnection(connection);
		}

	}

	/**
	 * Get company from Company table by ID Return connection to pool.
	 *
	 * @param id the id
	 * @return the company
	 * @throws CouponSystemExceptions
	 */
	@Override
	public Company getCompany(long id) throws CouponSystemExceptions {

		Connection connection = connectionPool.getConnection();
		Company company = new Company();
		try {
			String query = "SELECT * FROM Company WHERE ID=?";
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setLong(1, id);
			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				company.setId(resultSet.getLong("ID"));
				company.setName(resultSet.getString("NAME"));
				company.setPassword(resultSet.getString("PASSWORD"));
				company.setEmail(resultSet.getString("EMAIL"));
			}
		} catch (SQLException | CryptoHashException e) {
			throw new DBDAOException("Failed to get the requested company", e);
		} finally {
			connectionPool.returnConnection(connection);
		}
		return company;
	}

	/**
	 * Get all companies from Company table.
	 *
	 * @return connection to pool
	 * @throws CouponSystemExceptions
	 */
	@Override
	public Collection<Company> getAllCompanies() throws CouponSystemExceptions {

		Connection connection = connectionPool.getConnection();
		List<Company> allCompanies = new ArrayList<Company>();
		try {
			String query = "SELECT * FROM Company";
			PreparedStatement pstmt = connection.prepareStatement(query);
			ResultSet resultSet = pstmt.executeQuery();
			Company company = null;
			while (resultSet.next()) {
				company = new Company();
				company.setId(resultSet.getLong("ID"));
				company.setName(resultSet.getString("NAME"));
				company.setPassword(resultSet.getString("PASSWORD"));
				company.setEmail(resultSet.getString("EMAIL"));
				allCompanies.add(company);
			}
			pstmt.close();
			System.out.println(company);
		} catch (SQLException e) {
			throw new DBDAOException("Failed to get all companies", e);
		} catch (CryptoHashException e) {
			throw new DBDAOException("Failed to get all companies passwords", e);
		} finally {
			connectionPool.returnConnection(connection);
		}
		return allCompanies;
	}


	/**
	 * Get all coupons from a Company on Coupon table.
	 *
	 * @param id the id
	 * @return connection to pool
	 * @throws CouponSystemExceptions
	 */
	@Override
	public Collection<Coupon> getCoupons(long id) throws CouponSystemExceptions {
		Connection connection = connectionPool.getConnection();
		List<Coupon> coupons = new ArrayList<Coupon>();

		try {
			String query = "SELECT * FROM Coupon WHERE COMPANY_ID=?";
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setLong(1, id);
			ResultSet resultSet = pstmt.executeQuery();
			Coupon coupon = null;
			while (resultSet.next()) {
				coupon = new Coupon();
				coupon.setId(resultSet.getLong("ID"));
				coupon.setTitle(resultSet.getString("TITLE"));
				coupon.setStartDate(resultSet.getDate("START_DATE"));
				coupon.setEndDate(resultSet.getDate("END_DATE"));
				coupon.setAmount(resultSet.getInt("AMOUNT"));
				coupon.setType(CouponType.valueOf(resultSet.getString("TYPE")));
				coupon.setMessage(resultSet.getString("MESSAGE"));
				coupon.setPrice(resultSet.getDouble("PRICE"));
				coupon.setImage(resultSet.getString("IMAGE"));
				coupons.add(coupon);
			}
			pstmt.close();
		} catch (SQLException e) {
			throw new DBDAOException("Failed to get all coupons", e);
		} finally {
			connectionPool.returnConnection(connection);
		}

		return coupons;
	}

	/**
	 * A method for login into the system through passing user name and password.
	 * 
	 * The password is validated by converting the users input into system hash
	 * encryptions used and matching the hash with the one already written on the DB
	 *
	 * @param name     the name
	 * @param password the password
	 * @return connection - Returns connection to pool CompanyId - Returns Company
	 *         ID
	 * @throws CouponSystemExceptions
	 */
	@Override
	public Long login(String name, String password) throws CouponSystemExceptions {

		// Get connection from ConenctionPool
		Connection connection = connectionPool.getConnection();
		try {
			Long loggedInCompanyID = null;
			CryptoHashAlgorithms CHA = new CryptoHashAlgorithms();
			password = CHA.hashPassword(password.getBytes(), "SHA-256");

			String query = "SELECT ID FROM Company WHERE NAME=? AND PASSWORD=?";
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, password);
			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				loggedInCompanyID = resultSet.getLong(1);
				System.out.println(loggedInCompanyID);
//				loggedInCompanyID = companyId;
				// if authorization is successful, the ID is returned

			}
			System.out.println("Login Successfully");
			pstmt.close();
			return loggedInCompanyID;
		} catch (SQLException e) {
			throw new DBDAOException("Failed to login", e);
		} catch (CryptoHashException e) {
			throw new DBDAOException("Wrong password", e);
		} finally {
			connectionPool.returnConnection(connection);
		}
	}

	/**
	 * Get company from Company table by ID.
	 *
	 * @param companyName the company name
	 * @return connection Connection returns to pool company.getId() Returns the
	 *         company ID of the company name requested
	 * @throws CouponSystemExceptions
	 */
	@Override
	public long getCompanyId(String companyName) throws CouponSystemExceptions {
		Company company = new Company();
		Connection connection = connectionPool.getConnection();
		try {
			String query = "SELECT ID FROM Company WHERE NAME=?";
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setString(1, companyName);
			ResultSet resultSet = pstmt.executeQuery();
			// if line return : its a company record from DB.
			while (resultSet.next()) {
				company.setId(resultSet.getLong("ID"));
			}
			pstmt.close();
		} catch (SQLException e) {
			throw new DBDAOException("Failed to get company ID", e);
		} finally {
			connectionPool.returnConnection(connection);
		}
		return company.getId();

	}
}
