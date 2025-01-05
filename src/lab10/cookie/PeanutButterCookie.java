/**
 * Course: SWE 2410
 * Name: Salvin, Trent & Grant
 * Date: 12/3/24
 * Due: 12/8/24
 */
package lab10.cookie;

// all necessary imports
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lab10.GameController;
import java.util.Objects;
import java.util.Random;

/**
 * class for PeanutButterCookie
 */
public class PeanutButterCookie implements Cookie {

    // integer attributes
    private static final int SEVEN = 7;

    // randomly generating a number
    private final Random random = new Random();

    // the image of the peanut butter cookie
    private final ImageView image = new ImageView(new Image(Objects.requireNonNull(
            GameController.class.getResource("Images/peanutButter.png")).toString()));

    // declared cookie attributes
    private final int priceOfCookie;
    private int ageOfCookie;
    private boolean cookieIsBad;
    private String type;

    /**
     * constructor for MnMCookie
     */
    public PeanutButterCookie() {
        this.priceOfCookie = SEVEN;
        this.cookieIsBad = false;
        this.ageOfCookie = random.nextInt(2);
        this.type = "Peanut Butter";

    }

    /**
     * This method returns this cookies image
     *
     * @return The image of this cookie
     */
    @Override
    public ImageView getImage() {
        return image;
    }

    @Override
    public int getCookiePrice() {
        return priceOfCookie;
    }

    @Override
    public int getCookieAge() {
        return ageOfCookie;
    }

    /**
     * used to retrieve the type of cookie
     *
     * @return the type
     */
    @Override
    public String getType() {
        return type;
    }
}