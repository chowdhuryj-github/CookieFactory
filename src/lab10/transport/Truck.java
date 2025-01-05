/**
 * Course: SWE 2410
 * Name: Salvin, Trent & Grant
 * Date: 12/3/24
 * Due: 12/8/24
 */
package lab10.transport;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lab10.GameController;
import lab10.cookie.Cookie;
import lab10.cookieFactory.CookieFactory;

import java.util.Objects;
import java.util.Stack;

/**
 * class for Truck
 */
public class Truck implements Transportation {

    // all constant attributes
    private static final int X_LOCATION = 50;
    private static final int Y_LOCATION = 100;
    private static final int WIDTH = 50;
    private static final int HUNDRED = 100;
    private static final int STORAGE_SIZE = 25;
    private static final int TEN = 10;

    // other attributes
    private boolean readyForPickup = false;
    private CookieFactory factory = null;
    private final Stack<Cookie> cookieStorage = new Stack<>();
    private boolean toSeller = true;

    // the image attribute
    private final ImageView image = new ImageView(new Image(Objects.requireNonNull(
            GameController.class.getResource("Images/truck.png")).toString()));

    /**
     * When the truck is created, this constructor sets the trucks position
     * in its respective cell and sets the image width.
     */
    public Truck() {
        image.setX(X_LOCATION);
        image.setY(Y_LOCATION);
        image.setPreserveRatio(true);
        image.setFitWidth(WIDTH);
    }

    /**
     * This is the move method for the truck, and it moves the truck in a straight
     * line and calls the reachedDestination() and reachedDistribution()
     * methods once it gets to their respective sides (distribution side or destination side).
     */
    @Override
    public void moveVehicle() {
        if (toSeller) {
            image.setY(image.getY() - TEN);
            if (image.getY() <= 0) {
                toSeller = false;
                reachedDestination();
            }

        } else {
            image.setY(image.getY() + TEN);
            if (image.getY() >= HUNDRED) {
                toSeller = true;
                reachedDistribution();
            }
        }
    }

    /**
     * This method makes sure that the truck has an assigned factory and if it does,
     * it sets the trucks pickup variable to true.
     */
    private void reachedDistribution() {
        if (factory != null) {
            readyForPickup = true;
        }
    }

    /**
     * Todo
     */
    private void reachedDestination() {
    }

    /**
     * This returns the trucks ImageView
     * @return image - The trucks ImageView
     */
    @Override
    public ImageView getImage() {
        return image;
    }

    /**
     * This method assigns the truck a factory
     * @param factory - the factory that this truck is assigned to
     */
    @Override
    public void setFactory(CookieFactory factory) {
        this.factory = factory;
    }

    /**
     * This returns true if this truck is ready for pickup, false otherwise.
     * @return readyForPickup - true if truck is ready for pickup
     */
    @Override
    public boolean readyForPickup() {
        return readyForPickup;
    }

    /**
     * This method loads the cookies onto the truck and adds the
     * new stack of cookies to the trucks stack.
     * @param cookieCrate - The new stack of cookies to add to the truck
     */
    @Override
    public void loadCookies(Stack<Cookie> cookieCrate) {
        cookieStorage.addAll(cookieCrate);
        readyForPickup = false;
    }

    /**
     * This method returns the number of cookies that can fit on this truck.
     * @return STORAGE_SIZE - cookieStorage.size(); -
     * The number of cookies that can fit on this truck.
     */
    @Override
    public int getStorage() {
        return STORAGE_SIZE - cookieStorage.size();
    }
}
