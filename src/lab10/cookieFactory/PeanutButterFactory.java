/**
 * Course: SWE 2410
 * Name: Salvin, Trent & Grant
 * Date: 12/3/24
 * Due: 12/8/24
 */
package lab10.cookieFactory;

// all imports
import lab10.cookie.Cookie;
import lab10.cookie.PeanutButterCookie;
import java.util.Random;

/**
 * class for PeanutButterFactory
 */
public class PeanutButterFactory implements CookieFactory {

    // attributes
    private int address;
    private int productionTime;
    private boolean isReady;

    /**
     * constructor for PeanutButterFactory
     */
    public PeanutButterFactory() {
        this.productionTime = 0;
        this.isReady = false;
    }

    /**
     * method for retrieving the address
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
     * @return a peanut butter cookie
     */
    @Override
    public Cookie createCookie() {
        return new PeanutButterCookie();
    }

    /**
     * method for picking up the cookie
     * @return true
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
     * @return production time
     */
    @Override
    public int productionTime() {
        Random random = new Random();
        productionTime = productionTime + random.nextInt();
        return productionTime;
    }

    /**
     * return the type of factory
     *
     * @return string
     */
    @Override
    public String getFactoryType() {
        return "Peanut Butter Factory";
    }
}