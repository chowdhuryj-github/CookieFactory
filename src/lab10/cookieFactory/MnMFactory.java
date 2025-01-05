/**
 * Name: Salvin, Trent & Grant
 * Course: SWE 2410
 * Date: 12/3/24
 * Due: 12/8/24
 */
package lab10.cookieFactory;

// imports
import lab10.cookie.Cookie;
import lab10.cookie.MnMCookie;
import java.util.Random;

/**
 * class for MnMFactory
 */
public class MnMFactory implements CookieFactory {

    // attributes
    private int address;
    private int productionTime;
    private boolean isReady;

    /**
     * constructor for MNM Factory
     */
    public MnMFactory() {
        this.isReady = false;
        this.productionTime = 0;
    }


    /**
     * method for getting the address
     * @return the address
     */
    @Override
    public int getAddress() {
        return address;
    }

    /**
     * method for setting the address
     * @param address the address
     */
    @Override
    public void setAddress(int address) {
        this.address = address;
    }

    /**
     * method for creating the cookie
     * @return a cookie
     */
    @Override
    public Cookie createCookie() {
        return new MnMCookie();
    }

    /**
     * method for allowing pickup
     * @return if ready for pickup or not
     */
    @Override
    public boolean readyForPickup(){
        if(productionTime > 0) {
            isReady = true;
        }
        return isReady;
    }

    /**
     * adding production time
     * @return time for production
     */
    @Override
    public int productionTime() {
        Random random = new Random();
        productionTime = productionTime + random.nextInt(1, 4);
        return productionTime;
    }

    /**
     * return the type of factory
     *
     * @return string
     */
    @Override
    public String getFactoryType() {
        return "MnM Factory";
    }

}