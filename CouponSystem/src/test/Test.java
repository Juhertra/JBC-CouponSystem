package test;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Collection;

import coupon.sys.core.beans.Company;
import coupon.sys.core.beans.Coupon;
import coupon.sys.core.beans.CouponType;
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
import coupon.sys.core.exceptions.CouponDaoDbException;
import coupon.sys.core.exceptions.CryptoHashException;
import coupon.sys.core.exceptions.CustomerDaoDbException;
import coupon.sys.core.facade.AdminFacade;
import coupon.sys.core.facade.CompanyFacade;

/**
 * The Class Test.
 */
public class Test extends Thread {

	public static void main(String[] args) throws ConnectionPoolException, SQLException, CompanyDaodbException,
			CustomerDaoDbException, CryptoHashException, CouponDaoDbException {
		ConnectionPool CP = ConnectionPool.getInstance();
		Test t1 = new Test();
		// DAO's
		CustomerDao CustDAO = new CustomerDaoDb();
		CompanyDao CompDAO = new CompanyDaoDb();
		CouponDao CoupDAO = new CouponDaoDb();
		AdminFacade AF = new AdminFacade();
		CompanyFacade CNYF = new CompanyFacade();

		Company comp1 = new Company();
		Coupon c1 = new Coupon();
		Customer cust1 = new Customer();

		t1.start();

		try {
			// comp1.setName("Celcom");
			// comp1.setPassword("7654");
			// comp1.setEmail("Celcom@Celcom.co.il");
			// comp1.setId(CompDAO.getCompanyId(comp1.getName()));
			// AF.removeCompany(comp1);
			// AF.createCompany(comp1);
			//
			 cust1.setName("Mario");
			 cust1.setPassword("5555");
//			 cust1.setId(CustDAO.getCustomerId(cust1.getName()));
			 //cust1.setCoupons(CustDAO.getCoupons(cust1));
//			 AF.removeCustomer(cust1);
			 AF.createCustomer(cust1);
			 System.out.println(CompDAO.getCoupons(CompDAO.getCompanyId(comp1.getName())));
			//
			// c1.setAmount(4);
			// c1.setCompanyId(CompDAO.getCompanyId("Celcom"));
			// c1.setEndDate(Date.valueOf("2018-9-11"));
			// c1.setMessage("Test Coupon");
			// c1.setPrice(500);
			// c1.setType(CouponType.CAMPING);
			// c1.setTitle("Camping Test Coupon");
			//
			// CNYF.createCoupon(c1);
			System.out.println(CNYF.getCouponById(100));
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
