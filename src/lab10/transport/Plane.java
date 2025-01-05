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
 * class for Plane
 */
public class Plane implements Transportation {

    // all constant attributes
    private static final int X_LOCATION = 50;
    private static final int Y_LOCATION = 100;
    private static final int WIDTH = 50;
    private static final double STEP = 0.05;
    private static final double FIFTY = 50.0;
    private static final int THIRTY = 30;
    private static final int HUNDRED = 100;
    private static final int STORAGE_SIZE = 25;

    // all other attributes
    private final Stack<Cookie> cookieStorage = new Stack<>();
    private boolean readyForPickup = false;
    private CookieFactory factory = null;
    private double angle = 0.0;
    private boolean toSeller = true;

    // the image attribute
    private final ImageView image = new ImageView(new Image(Objects.requireNonNull(
            GameController.class.getResource("Images/plane.png")).toString()));

    /**
     * When the plane is created, this constructor sets the planes position in
     * its respective cell and sets the image width.
     */
    public Plane() {
        image.setX(X_LOCATION);
        image.setY(Y_LOCATION);
        image.setPreserveRatio(true);
        image.setFitWidth(WIDTH);
    }

    /**
     * This is the move method for the plane, and it moves the plane in a curved
     * pattern and calls the reachedDestination() and reachedDistribution()
     * methods once it gets to their respective sides (distribution side or destination side).
     */
    @Override
    public void moveVehicle() {
        if (toSeller) {
            calculateAngle();
            if (image.getY() <= 0) {
                toSeller = false;
                reachedDestination();
            }
        } else {
            calculateAngle();
            if (image.getY() >= HUNDRED) {
                toSeller = true;
                reachedDistribution();
            }
        }
    }

    /**
     * This method calculates the angle of the planes movement
     */
    private void calculateAngle() {
        double radius = THIRTY;
        double newY = FIFTY + 3 * radius * Math.cos(angle);
        double newX;

        angle += STEP;
        if (angle >= 2 * Math.PI) {
            angle -= 2 * Math.PI;
        }
        if (angle <= Math.PI) {
            newX = FIFTY - radius * Math.sin(angle);
        } else {
            newX = FIFTY + radius * Math.sin(angle);
        }
        this.image.setX(newX);
        this.image.setY(newY);
    }

    /**
     * This method makes sure that the plane has an assigned factory and if it does,
     * it sets the planes pickup variable to true.
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
     * This returns the planes ImageView
     * @return image - The boats ImageView
     */
    @Override
    public ImageView getImage() {
        return image;
    }

    /**
     * This method assigns the plane a factory
     * @param factory - the factory that this plane is assigned to
     */
    @Override
    public void setFactory(CookieFactory factory) {
        this.factory = factory;
    }

    /**
     * This returns true if this plane is ready for pickup, false otherwise.
     * @return readyForPickup - true if plane is ready for pickup
     */
    @Override
    public boolean readyForPickup() {
        return readyForPickup;
    }

    /**
     * This method loads the cookies onto the plane and adds the
     * new stack of cookies to the planes stack.
     * @param cookieCrate - The new stack of cookies to add to the plane
     */
    @Override
    public void loadCookies(Stack<Cookie> cookieCrate) {
        cookieStorage.addAll(cookieCrate);
        readyForPickup = false;
    }

    /**
     * This method returns the number of cookies that can fit on this plane.
     * @return STORAGE_SIZE - cookieStorage.size(); -
     * The number of cookies that can fit on this plane.
     */
    @Override
    public int getStorage() {
        return STORAGE_SIZE - cookieStorage.size();
    }
}
