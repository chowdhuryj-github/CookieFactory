/**
 * Course: SWE 2410
 * Name: Salvin, Trent & Grant
 * Date: 12/3/24
 * Due: 12/8/24
 */
package lab10.cookie;

// required imports
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lab10.GameController;
import java.util.Objects;
import java.util.Random;

/**
 * class for Chocolate Chip Cookie
 */
public class ChocolateChipCookie implements Cookie {

    // integer attributes
    private static final int EIGHT = 8;

    // randomly generating a number
    private final Random random = new Random();

    // image of the chocolate chip cookie
    private final ImageView image = new ImageView(new Image(Objects.requireNonNull(
            GameController.class.getResource("Images/cookie.png")).toString()));


    // declared cookie attributes
    private final int priceOfCookie;
    private int ageOfCookie;
    private boolean cookieIsBad;
    private String type;

    /**
     * constructor for ChocolateChipCookie
     */
    public ChocolateChipCookie() {
        this.priceOfCookie = EIGHT;
        this.cookieIsBad = false;
        this.ageOfCookie = random.nextInt(2);
        this.type = "Chocolate Chip";
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

    /**
     * retrieving the price of the cookie
     * @return price of cookie
     */
    @Override
    public int getCookiePrice() {
        return priceOfCookie;
    }


    /**
     * retrieving the age of the cookie
     * @return age of cookie
     */
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