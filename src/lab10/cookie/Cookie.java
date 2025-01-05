/**
 * Course: SWE 2410
 * Name: Salvin, Trent & Grant
 * Date: 12/3/24
 * Due: 12/8/24
 */
package lab10.cookie;

// required imports
import javafx.scene.image.ImageView;

/**
 * the Cookie class interface
 */
public interface Cookie {

    /**
     * This method returns this cookies image
     * @return The image of this cookie
     */
    ImageView getImage();

    /**
     * retrieve the price of the cookie
     * @return age of cookie
     */
    int getCookiePrice();


    /**
     * retrieving the age of the cookie
     * @return age of cookie
     */
    int getCookieAge();


    /**
     * used to retrieve the type of cookie
     * @return the type
     */
    String getType();


}