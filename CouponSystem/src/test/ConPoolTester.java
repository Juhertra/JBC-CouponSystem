//package test;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.Collection;
//
//import coupon.sys.core.beans.Company;
//import coupon.sys.core.beans.Customer;
//import coupon.sys.core.beans.Coupon;
//import coupon.sys.core.beans.CouponType;
//import coupon.sys.core.connectionPool.ConnectionPool;
//import coupon.sys.core.dao.CustomerDao;
//import coupon.sys.core.dao.db.CompanyDaoDb;
//import coupon.sys.core.exceptions.ConnectionPoolException;
//import coupon.sys.core.exceptions.CustomerDaoDbException;
//import coupon.sys.core.connectionPool.ConnectionPool;
//import coupon.sys.core.exceptions.ConnectionPoolException;
//
//public class ConPoolTester extends Thread {
//	
////	public static CustomerDao customerDao;
//	//public static Customer c1 = new Customer();
//	
//	public static void main(String[] args) throws CustomerDaoDbException, ConnectionPoolException, InterruptedException {
//		for (int i = 1; i <= ConnectionPool.MAX_CONNECTIONS; i++) {
//			ConPoolTester t = new ConPoolTester("t" + i);
//			t.start();
//		}
//
//		try {
//
//			Thread.sleep(5000);
//			ConnectionPool.getInstance().closeAllConnections();
//			System.out.println("connection pool is closed");
//		} catch (ConnectionPoolException | InterruptedException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public ConPoolTester(String name) {
//		super(name);
//	}
//
//	@Override
//	public void run() {
//		try {
//			Connection con = ConnectionPool.getInstance().getConnection();
//			Customer c1 = new Customer();
//			c1.setName("Orange");
//			c1.setPassword("1234");
//
//			CustomerDao customerDao = null;
//			customerDao.createCustomer(c1);
//			System.out.println(getName() + " got a connection");
//			Thread.sleep(3000);
//			System.out.println(c1.getName());
//			System.out.println(c1.getPassword());
//			ConnectionPool.getInstance().returnConnection(con);
//			System.out.println(getName() + " returned a connection");
//		} catch (ConnectionPoolException | InterruptedException e) {
//			e.printStackTrace();
//		} catch (CustomerDaoDbException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//}
