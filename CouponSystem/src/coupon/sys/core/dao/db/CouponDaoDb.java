package coupon.sys.core.dao.db;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import coupon.sys.core.beans.Coupon;
import coupon.sys.core.beans.CouponType;
import coupon.sys.core.connectionPool.ConnectionPool;
import coupon.sys.core.dao.CouponDao;
import coupon.sys.core.exceptions.ConnectionPoolException;
import coupon.sys.core.exceptions.CouponSystemExceptions;
import coupon.sys.core.exceptions.DBDAOException;

/**
 * This class is a part of the DAO layer. The class which communicates between
 * the coupon's object related methods and the data base through sql queries.
 * 
 * @author Julio Hernan Trajtemberg
 * @version 1.0 September 18, 2018.
 *
 */
public class CouponDaoDb implements CouponDao {

	/** The connection pool. */
	private ConnectionPool connectionPool;

	/**
	 * Constructs the class that communicates with the db and initializes the pool
	 * variable with the Connection pool's instance.
	 */
	public CouponDaoDb() {

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
	 * This method creates the coupon's record into the data base, using the
	 * received coupon parameter.
	 *
	 * @param coupon the coupon
	 * @throws CouponSystemExceptions
	 */
	@Override
	public void createCoupon(Coupon coupon, long loggedInCompanyID) throws CouponSystemExceptions {

		Connection connection = connectionPool.getConnection();

		Boolean couponTitleExists;
		try {
			String verifyCouponQuery = "SELECT * FROM Coupon";
			PreparedStatement verifyCouponPstmt = connection.prepareStatement(verifyCouponQuery);
			ResultSet couponResultSet = verifyCouponPstmt.executeQuery();
			String couponTitle;
			couponTitleExists = false;

			while (couponResultSet.next()) {
				couponTitle = couponResultSet.getString("TITLE");
				if (couponTitle.equals(coupon.getTitle())) {
					couponTitleExists = true;
					System.out.println("Coupon Already Exists");
					break;
				}
			}

			if (couponTitleExists == false) {

				// Add new coupon into coupon table
				System.out.println("Writing to DB - Creating coupon");
				String query = "INSERT INTO Coupon (TITLE, START_DATE, END_DATE, AMOUNT, "
						+ "TYPE, MESSAGE, PRICE, IMAGE) VALUES (?, ? ,? ,? ,? ,? ,?, ?)";
				PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, coupon.getTitle());
				pstmt.setDate(2, coupon.getStartDate());
				pstmt.setDate(3, coupon.getEndDate());
				pstmt.setInt(4, coupon.getAmount());
				pstmt.setString(5, coupon.getType().toString());
				pstmt.setString(6, coupon.getMessage());
				pstmt.setDouble(7, coupon.getPrice());
				pstmt.setString(8, coupon.getImage());
				pstmt.executeUpdate();

				// Get coupon ID
				long id = 0;
				query = "SELECT ID FROM Coupon WHERE TITLE=?";
				pstmt = connection.prepareStatement(query);
				pstmt.setString(1, coupon.getTitle());
				ResultSet resultSet = pstmt.executeQuery();
				while (resultSet.next()) {
					id = resultSet.getLong("ID");
				}

				// Add company and coupon ID into Company_Coupon join table
				query = "INSERT INTO Company_Coupon (COMPANY_ID, COUPON_ID) VALUES(?, ?)";
				pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				pstmt.setLong(1, loggedInCompanyID);
				pstmt.setLong(2, id);
				pstmt.executeUpdate();
				pstmt.close();
			}
		} catch (SQLException e) {
			throw new DBDAOException("Failed to create new coupon", e);
		} finally {
			connectionPool.returnConnection(connection);
			System.out.println("Coupon created succesfully");
		}
	}


	/**
	 * This method removes the coupon's record from the data base, using the
	 * received coupon parameter.
	 *
	 * @param coupon the coupon
	 * @throws CouponSystemExceptions
	 */
	@Override
	public void removeCoupon(Coupon coupon) throws CouponSystemExceptions {
		Connection connection = connectionPool.getConnection();
		try {
			String query = "DELETE FROM Coupon WHERE ID=?";
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setLong(1, coupon.getId());
			pstmt.executeUpdate();
			pstmt.close();

			String query2 = "DELETE FROM Customer_Coupon WHERE COUPON_ID=?";
			PreparedStatement pstmt2 = connection.prepareStatement(query2);
			pstmt2.setLong(1, coupon.getId());
			pstmt2.executeUpdate();
			pstmt2.close();

			String query3 = "DELETE FROM Company_Coupon WHERE COUPON_ID=?";
			PreparedStatement pstmt3 = connection.prepareStatement(query3);
			pstmt3.setLong(1, coupon.getId());
			pstmt3.executeUpdate();
			pstmt3.close();
		} catch (SQLException e) {
			throw new DBDAOException("Failed to remove the requested coupon", e);
		} finally {
			connectionPool.returnConnection(connection);
		}
	}

	/**
	 * This method updates some columns in the coupon's record in the data base,
	 * using the received company parameter.
	 *
	 * @param coupon the coupon
	 * @throws CouponSystemExceptions
	 */
	@Override
	public void updateCoupon(Coupon coupon) throws CouponSystemExceptions {
		Connection connection = connectionPool.getConnection();
		try {
			String query = "UPDATE Coupon SET END_DATE=?, PRICE=?, AMOUNT=?, MESSAGE=?, IMAGE=? WHERE ID=?";
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setDate(1, (java.sql.Date) coupon.getEndDate());
			pstmt.setDouble(2, coupon.getPrice());
			pstmt.setInt(3, coupon.getAmount());
			pstmt.setString(4, coupon.getMessage());
			pstmt.setString(5, coupon.getImage());
			pstmt.setLong(6, coupon.getId());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			throw new DBDAOException("Failed to update the requested coupon", e);
		} finally {
			connectionPool.returnConnection(connection);
		}
	}

	/**
	 * This method receives the coupon's record from the data base, using the
	 * received coupon's id parameter.
	 *
	 * @param id the id
	 * @return the coupon
	 * @throws CouponSystemExceptions
	 */
	@Override
	public Coupon getCoupon(long id) throws CouponSystemExceptions {
		Coupon coupon = new Coupon();
		Connection connection = connectionPool.getConnection();
		try {
			String query = "SELECT * FROM Coupon WHERE ID=?";
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setLong(1, id);
			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				coupon.setId(resultSet.getLong("ID"));
				coupon.setTitle(resultSet.getString("TITLE"));
				coupon.setStartDate(resultSet.getDate("START_DATE"));
				coupon.setEndDate(resultSet.getDate("END_DATE"));
				coupon.setAmount(resultSet.getInt("AMOUNT"));
				coupon.setType(CouponType.valueOf(resultSet.getString("TYPE")));
				coupon.setMessage(resultSet.getString("MESSAGE"));
				coupon.setPrice(resultSet.getDouble("PRICE"));
				coupon.setImage(resultSet.getString("IMAGE"));
			}
			pstmt.close();
		} catch (SQLException e) {
			throw new DBDAOException("Failed to get the requested coupon", e);
		} finally {
			connectionPool.returnConnection(connection);
		}

		return coupon;
	}

	/**
	 * This method receives all the coupon's records of all the coupons from the
	 * data base.
	 *
	 * @return the all coupons
	 * @throws CouponSystemExceptions
	 */
	@Override
	public Collection<Coupon> getAllCoupons() throws CouponSystemExceptions {
		List<Coupon> allCoupons = new ArrayList<Coupon>();
		Connection connection = connectionPool.getConnection();
		try {
			String query = "SELECT * FROM Coupon";
			PreparedStatement pstmt = connection.prepareStatement(query);
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
				// add to list
				allCoupons.add(coupon);
			}
			pstmt.close();
		} catch (SQLException e) {
			throw new DBDAOException("Failed to get all coupons", e);
		} finally {
			connectionPool.returnConnection(connection);
		}
		return allCoupons;
	}

	/**
	 * This method receives all the records of coupons that are in the wished
	 * category from all of the coupons from the data base.
	 *
	 * @param couponType the coupon type
	 * @return the coupons by type
	 * @throws CouponSystemExceptions
	 */
	@Override
	public Collection<Coupon> getCouponsByType(CouponType couponType) throws CouponSystemExceptions {
		List<Coupon> allCouponsByType = new ArrayList<Coupon>();
		Connection connection = connectionPool.getConnection();
		try {
			String query = "SELECT * FROM Coupon WHERE TYPE=?";
			PreparedStatement pstmt = connection.prepareStatement(query);
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

				// add to list
				allCouponsByType.add(coupon);
			}
			pstmt.close();
		} catch (SQLException e) {
			throw new DBDAOException("Failed to get coupons by type", e);
		} finally {
			connectionPool.returnConnection(connection);
		}
		return allCouponsByType;
	}

	/**
	 * method that gets id's of coupons which have expired and returns a list of
	 * those coupons.
	 *
	 * @return the old coupons
	 * @throws CouponSystemExceptions
	 */
	public List<Coupon> getOldCoupons() throws CouponSystemExceptions {

		// constructing a List<Coupon> to return
		List<Coupon> oldCoupons = new ArrayList<Coupon>();
		Connection connection = connectionPool.getConnection();
		try {
			String query = "SELECT ID FROM Coupon WHERE CURRENT_DATE>END_DATE";
			PreparedStatement pstmt = connection.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			Coupon coupon = null;
			while (rs.next()) {
				coupon = new Coupon();
				coupon.setId(rs.getLong("ID"));
				oldCoupons.add(coupon);
			}
			pstmt.close();
		} catch (SQLException e) {
			throw new DBDAOException("Can't get old coupon", e);
		} finally {
			connectionPool.returnConnection(connection);
		}
		return oldCoupons;
	}
}
