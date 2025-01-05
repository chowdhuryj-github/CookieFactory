/**
 * Course: SWE 2410
 * Name: Salvin, Trent & Grant
 * Date: 12/3/24
 * Due: 12/8/24
 */
package lab10.transport;

// all required imports
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lab10.GameController;
import lab10.cookie.Cookie;
import lab10.cookieFactory.CookieFactory;
import java.util.Objects;
import java.util.Stack;

/**
 * the class for the Boat
 */
public class Boat implements Transportation {

    // all the constant attributes
    private static final int STORAGE_SIZE = 100;
    private static final int X_LOCATION = 50;
    private static final int Y_LOCATION = 100;
    private static final int WIDTH = 50;
    private static final double STEP = 0.1;
    private static final double FIFTY = 50.0;
    private static final int THIRTY = 30;
    private static final int HUNDRED = 100;

    // image attribute
    private final ImageView image = new ImageView(new Image(Objects.requireNonNull(
            GameController.class.getResource("Images/boat.png")).toString()));


    // other relevant attributes
    private boolean toSeller = true;
    private final Stack<Cookie> cookieStorage = new Stack<>();
    private CookieFactory factory = null;
    private boolean readyForPickup = false;
    private double step = 0;

    /**
     * When the boat is created, this constructor sets the boats position in its
     * respective cell and sets the image width.
     */
    public Boat() {
        image.setX(X_LOCATION);
        image.setY(Y_LOCATION);
        image.setPreserveRatio(true);
        image.setFitWidth(WIDTH);
    }

    /**
     * This is the move method for the boat, and it moves the boat in a sinusoidal
     * pattern and calls the reachedDestination() and reachedDistribution()
     * methods once it gets to their respective sides (distribution side or destination side).
     */
    @Override
    public void moveVehicle() {
        if (toSeller) {
            step += STEP;
            double newX = FIFTY + THIRTY * Math.sin(step);
            double newY = this.image.getY() - 2.0;
            this.image.setX(newX);
            this.image.setY(newY);

            if (image.getY() <= 0) {
                toSeller = false;
                reachedDestination();
            }

        } else {
            step += STEP;
            double newX = FIFTY + THIRTY * Math.sin(step);
            double newY = this.image.getY() + 2.0;
            this.image.setX(newX);
            this.image.setY(newY);

            if (image.getY() >= HUNDRED) {
                toSeller = true;
                reachedDistribution();
            }
        }
    }

    /**
     * This method makes sure that the boat has an assigned factory and if it does,
     * it sets the boats pickup variable to true.
     */
    private void reachedDistribution() {
        if (factory != null) {
            readyForPickup = true;
        }
    }

    // todo
    private void reachedDestination() {

    }

    /**
     * This returns the boats ImageView
     * @return image - The boats ImageView
     */
    @Override
    public ImageView getImage() {
        return image;
    }

    /**
     * This method assigns the boat a factory
     * @param factory - the factory that this boat is assigned to
     */
    @Override
    public void setFactory(CookieFactory factory) {
        this.factory = factory;
    }

    /**
     * This returns true if this boat is ready for pickup, false otherwise.
     * @return readyForPickup - true if boat is ready for pickup
     */
    @Override
    public boolean readyForPickup() {
        return readyForPickup;
    }

    /**
     * This method loads the cookies onto the boat and adds the new
     * stack of cookies to the boats stack.
     * @param cookieCrate - The new stack of cookies to add to the boat
     */
    @Override
    public void loadCookies(Stack<Cookie> cookieCrate) {
        cookieStorage.addAll(cookieCrate);
        readyForPickup = false;
    }

    /**
     * This method returns the number of cookies that can fit on this boat.
     * @return STORAGE_SIZE - cookieStorage.size(); -
     * The number of cookies that can fit on this boat.
     */
    @Override
    public int getStorage() {
        return STORAGE_SIZE - cookieStorage.size();
    }


}
