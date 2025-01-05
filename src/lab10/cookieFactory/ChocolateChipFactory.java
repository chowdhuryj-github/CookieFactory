/**
 * Course: SWE 2410
 * Name: Salvin, Trent & Grant
 * Date: 12/3/24
 * Due: 12/8/24
 */
package lab10.cookieFactory;

// imports
import lab10.cookie.ChocolateChipCookie;
import lab10.cookie.Cookie;
import java.util.Random;

/**
 * class for ChocolateChipFactory
 */
public class ChocolateChipFactory implements CookieFactory {

    // attributes
    private int address;
    private int productionTime;
    private boolean isReady;


    /**
     * constructor for ChocolateChipFactory
     */
    public ChocolateChipFactory() {
        this.isReady = false;
        this.productionTime = 0;
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
     * @return the cookie
     */
    @Override
    public Cookie createCookie() {
        return new ChocolateChipCookie();
    }

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
        return "Chocolate Chip Factory";
    }
}