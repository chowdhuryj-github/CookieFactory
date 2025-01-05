/**
 * Course: SWE 2410
 * Name: Salvin, Trent & Grant
 * Date: 12/3/24
 * Due: 12/8/24
 */
package lab10.transportFactory;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lab10.GameController;
import lab10.transport.Transportation;
import lab10.transport.Boat;

import java.util.Objects;

/**
 * class for BoatFactory
 */
public class BoatFactory implements LogisticFactory {

    // attribute for boat factory image
    private final ImageView image = new ImageView(new Image(Objects.requireNonNull(
            GameController.class.getResource("Images/port.png")).toString()));

    /**
     * This method creates and returns a new Boat
     * @return a new Boat
     */
    @Override
    public Transportation createTransportation() {
        return new Boat();
    }

    /**
     * This method returns this factory's image
     * @return the image of this factory
     */
    @Override
    public ImageView getImage() {
        return image;
    }

}
