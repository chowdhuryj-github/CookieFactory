/**
 * Course: SWE 2410
 * Name: Salvin, Trent & Grant
 * Date: 12/3/24
 * Due: 12/8/24
 */
package lab10.cookieFactory;

// imports
import lab10.cookie.Cookie;

/**
 * interface class for CookieFactory
 */
public interface CookieFactory {

    /**
     * method for creating a cookie
     * @return a cookie
     */
    Cookie createCookie();

    /**
     * method for retrieving the address
     * @return the address
     */
    int getAddress();

    /**
     * method for setting the address
     * @param address the address
     */
    void setAddress(int address);

    /**
     * method for allowing pickup
     * @return boolean decision
     */
    boolean readyForPickup();

    /**
     * adding production time
     * @return time for production
     */
    int productionTime();

    /**
     * return the type of factory
     * @return string
     */
    String getFactoryType();

}