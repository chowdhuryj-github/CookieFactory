/**
 * Course: SWE 2410
 * Name: Salvin, Trent & Grant
 * Date: 12/3/24
 * Due: 12/8/24
 */
package lab10.transport;

import javafx.scene.image.ImageView;
import lab10.cookie.Cookie;
import lab10.cookieFactory.CookieFactory;

import java.util.Stack;

/**
 * interface for Transportation
 */
public interface Transportation {

    /**
     * This method moves the transport one step
     */
    void moveVehicle();

    /**
     * This method get the image of a transport
     * @return The transports ImageVIew
     */
    ImageView getImage();

    /**
     * This method assigns a factory to a transport
     * @param factory The factory to be assigned to the transport
     */
    void setFactory(CookieFactory factory);

    /**
     * This method returns a transports readyForPickup variable
     * @return The transports readyForPickup variable
     */
    boolean readyForPickup();

    /**
     * This method takes and loads cookies onto a transport
     * @param cookieCrate The cookies to be added to the transports storage
     */
    void loadCookies(Stack<Cookie> cookieCrate);

    /**
     * This method returns the transports storage
     * @return The storage a transport
     */
    int getStorage();
}
