package coupon.sys.core.facade;

/**
 * This interface is used for the different login methods. all of the facades
 * implements it, and there for have to instantiate them.
 * 
 * @author Julio Hernan Trajtemberg
 * @version 1.0 September 18, 2018.
 */
public interface CouponClientFacade {

	/**
	 * Login.
	 *
	 * @param name
	 *            the name
	 * @param password
	 *            the password
	 * @param clientType
	 *            the client type
	 * @return the coupon client facade
	 */
	public CouponClientFacade login(String name, String password, ClientType clientType);
}
