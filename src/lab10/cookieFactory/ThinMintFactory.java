/**
 * Course: SWE 2410
 * Name: Salvin, Trent & Grant
 * Date: 12/3/24
 * Due: 12/8/24
 */
package lab10.cookieFactory;

// all imports
import lab10.cookie.Cookie;
import lab10.cookie.ThinMintCookie;

import java.util.Random;

/**
 * class for ThinMintFactory
 */
public class ThinMintFactory implements CookieFactory {

    // attributes
    private int address;
    private int productionTime;
    private boolean isReady;

    /**
     * Constructor for ThinMintFactory
     */
    public ThinMintFactory() {
        this.isReady = false;
        this.productionTime = 0;
    }

    /**
     * returning the address
     * @return the address
     */
    @Override
    public int getAddress() {
        return address;
    }

    /**
     * setting the address
     * @param address the address
     */
    @Override
    public void setAddress(int address) {
        this.address = address;
    }

    /**
     * creating a cookie
     * @return the cookie object
     */
    @Override
    public Cookie createCookie() {
        return new ThinMintCookie();
    }

    /**
     * if the cookie is ready or not
     * @return the boolean decision
     */
    @Override
    public boolean readyForPickup() {
        if(productionTime > 0) {
            isReady = true;
        }
        return isReady;
    }

    /**
     * adding production time
     * @return the production time
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
        return "Thin Mint Factory";
    }
}