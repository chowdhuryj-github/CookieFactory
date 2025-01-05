/**
 * Course: SWE 2410
 * Name: Salvin, Trent & Grant
 * Date: 12/3/24
 * Due: 12/8/24
 */
package lab10.transportFactory;

import javafx.scene.image.ImageView;
import lab10.transport.Transportation;

/**
 * interface for logistics
 */
public interface LogisticFactory {

    /**
     * When this method is called, a new transportation is created and returned
     * @return A new transportation
     */
    Transportation createTransportation();

    /**
     * This class returns the image of the logistics factory
     * @return The image of the logistics factory
     */
    ImageView getImage();


}
