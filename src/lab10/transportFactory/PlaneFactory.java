/**
 * Course: SWE 2410
 * Name: Salvin, Trent & Grant
 * Date: 12/3/24
 * Due: 12/8/24
 */
package lab10.transportFactory;

// all necessary imports
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lab10.GameController;
import lab10.transport.Transportation;
import lab10.transport.Plane;
import java.util.Objects;

/**
 * class for PlaneFactory
 */
public class PlaneFactory implements LogisticFactory {

    // the image attribute
    private final ImageView image = new ImageView(new Image(Objects.requireNonNull(
            GameController.class.getResource("Images/airport.png")).toString()));

    /**
     * When this method is called, a new plane is created and returned
     * @return a new plane
     */
    @Override
    public Transportation createTransportation() {
        return new Plane();
    }

    /**
     * This method gets the ImageView of this logistics factory
     * @return the ImageView of this logistics factory
     */
    @Override
    public ImageView getImage() {
        return image;
    }
}
