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
 * class for ThinMintCookie
 */
public class ThinMintCookie implements Cookie {

    // integer attributes
    private static final int SIX = 6;

    // randomly generating a number
    private final Random random = new Random();

    private final ImageView image = new ImageView(new Image(Objects.requireNonNull(
            GameController.class.getResource("Images/thinMint.png")).toString()));


    // declared cookie attributes
    private final int priceOfCookie;
    private int ageOfCookie;
    private boolean cookieIsBad;
    private String type;

    /**
     * constructor for thin mint cookie
     */
    public ThinMintCookie() {
        this.priceOfCookie = SIX;
        this.cookieIsBad = false;
        this.ageOfCookie = random.nextInt(2);
        this.type = "Thin Mint";
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
     * retrieving price of cookie
     * @return price of cookie
     */
    @Override
    public int getCookiePrice() {
        return priceOfCookie;
    }


    /**
     * retrieving age of cookie
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