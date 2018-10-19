package coupon.sys.core.connectionPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import coupon.sys.core.exceptions.ConnectionPoolException;
import coupon.sys.core.exceptions.CouponSystemExceptions;
import coupon.sys.core.utils.DataBaseProperties;

/**
 * The Class ConnectionPool.
 *
 * @author Julio Hernan Trajtemberg
 * @version 1.0 September 18, 2018.
 */
public class ConnectionPool {

	/** The connection pool. */
	// Singleton
	private static ConnectionPool connectionPool = null;

	/** The connection set. */
	// Set and Mapping of Connections with Hash
	private Set<Connection> connectionSet = Collections.synchronizedSet(new HashSet<Connection>());

	/** The close connection pool. */
	// private Iterator<Connection> connectionIterator = connectionSet.iterator();
	private boolean closeConnectionPool = false;

	/** The Constant MAX_CONNECTIONS. */
	// DB Properties
	public static final int MAX_CONNECTIONS = DataBaseProperties.getMaxConnections();

	/** The Constant URL. */
	private static final String URL = DataBaseProperties.getUrl();

	/** The Constant DRIVER. */
	private static final String DRIVER = DataBaseProperties.getDriver();

	// private static final String USER = DataBaseProperties.getUser();
	// private static final String PASSWORD = DataBaseProperties.getPassword();

	/**
	 * Instantiates a new connection pool.
	 *
	 * @throws ConnectionPoolException the connection pool exception
	 */
	private ConnectionPool() throws CouponSystemExceptions {
		try {
			Class.forName(DRIVER);
			for (int i = 1; i <= MAX_CONNECTIONS; i++) {
				connectionSet.add(DriverManager.getConnection(URL));
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new ConnectionPoolException("Failed to create connection: ", e);
		}
	}

	/**
	 * Get connection instance from pool .
	 *
	 * @return connectionPool if available, else
	 * @throws ConnectionPoolException the connection pool exception
	 */
	public static ConnectionPool getInstance() throws CouponSystemExceptions {
		if (connectionPool == null) {
			connectionPool = new ConnectionPool();
		}
		return connectionPool;

	}

	/**
	 * Get connection from connection pool If closeAllConnections() method was used,
	 * getConnection() method stops giving connections
	 * 
	 * @return the connection
	 * 
	 * @throws ConnectionPoolException the connection pool exception
	 */

	public synchronized Connection getConnection() throws CouponSystemExceptions {
		if (closeConnectionPool == false) {
			while (connectionSet.isEmpty()) {
				try {
					wait();
				} catch (InterruptedException e) {
					throw new ConnectionPoolException("Error trying to get a connections from pool ", e);
				}
			}
			Iterator<Connection> connectionIterator = connectionSet.iterator();
			Connection connection = connectionIterator.next();
			connectionIterator.remove();
			return connection;

		}
		return null;
	}

	/**
	 * Return connection to pool.
	 *
	 * @param connection the connection
	 * @throws ConnectionPoolException the connection pool exception
	 */
	public synchronized void returnConnection(Connection connection) throws CouponSystemExceptions {
		connectionSet.add(connection);
		notifyAll();
	}

	/**
	 * Check that all the connections where closed, if a connections is free it
	 * locks it and keep checking until each connections is returned or gives a
	 * timeout until hard shutdown of connections.
	 *
	 * @throws ConnectionPoolException the connection pool exception
	 */
	public synchronized void closeAllConnections() throws ConnectionPoolException {
		closeConnectionPool = true;
		long timeOut = 300;
		while (connectionSet.size() < MAX_CONNECTIONS) {
			try {
				wait(timeOut);
			} catch (InterruptedException e) {
				throw new ConnectionPoolException("connection pool shutdown error", e);
			}
			Iterator<Connection> connectionIterator = connectionSet.iterator();
			while (connectionIterator.hasNext()) {
				Connection connection = connectionIterator.next();
				try {
					connection.close();
				} catch (SQLException e) {
					throw new ConnectionPoolException("Error closing connection", e);
				}
				connectionIterator.remove();
			}
		}
	}
}