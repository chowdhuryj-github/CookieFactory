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
import lab10.transport.Truck;

import java.util.Objects;

/**
 * class for Truck Factory
 */
public class TruckFactory implements LogisticFactory {

    // the image attribute
    private final ImageView image = new ImageView(new Image(
            Objects.requireNonNull(GameController.class.getResource("Images/warehouse.png")).toString()));

    /**
     * This method creates and returns a new truck
     * @return a new truck
     */
    @Override
    public Transportation createTransportation() {
        return new Truck();
    }

    /**
     * This method returns the ImageView of this LogisticsFactory
     * @return the ImageView of this LogisticsFactory
     */
    @Override
    public ImageView getImage() {
        return image;
    }
}
