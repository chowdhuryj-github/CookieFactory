/**
 * Course: SWE 2410
 * Name: Salvin, Trent & Grant
 * Date: 12/3/24
 * Due: 12/8/24
 */
package lab10;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * the main class
 */
public class Main extends Application {

    private static final int SECOND_WIDTH = 800;
    private static final int SECOND_HEIGHT = 400;

    /**
     * the main method
     */
    public Main() {
    }


    /**
     * the launch method
     * @param args arguments
     */
    public static void main(String[] args) {
        launch(args);
    }


    /**
     * JavaFX Method
     * @param stage stage
     * @throws Exception exception
     */
    @Override
    public void start(Stage stage) throws Exception {

        // adding icons to the applications
        Image primaryIcon = new Image(Objects.requireNonNull(
                getClass().getResourceAsStream("/lab10/images/MnM.png")));
        Image secondaryIcon = new Image(Objects.requireNonNull(
                getClass().getResourceAsStream("/lab10/images/cookie.png")));

        // the primary stage
        Parent title = FXMLLoader.load(Objects.requireNonNull(
                getClass().getResource("screen.fxml")));
        Scene scene = new Scene(title);
        stage.setTitle("Cookie Factory Simulator");
        stage.setScene(scene);
        stage.getIcons().add(primaryIcon);
        stage.show();

        // the secondary stage
        Stage secondStage = new Stage();
        StackPane secondLayout = new StackPane();

        Text textInSecondWindow = new Text(
                "Welcome to the Cookie Factory Program! Here are the following " +
                        "instructions to follow: \n" +
                        "1. Create factories to produce your cookies! Use the " +
                        "drop down menu! \n" +
                        "2. Once your costs fall below $1000, hit the right key to " +
                        "unlock transportation! \n" +
                        "3. Create transportation, such as planes, boats " +
                        "or trucks to transport your cookies! \n" +
                        "4. Once you're done, hold on the right key to watch " +
                        "your cookies get transported back and forth! \n" +
                        "5. Notice your money will go down, this means " +
                        "the cookies are being sold! \n" +
                        "6. Lastly, sometimes cookies go bad! We have a " +
                        "loss indicator for each type of store! \n" +
                        "7. Remember, the amount of money you start on " +
                        "is a budget you have! You get extra money after you're " +
                        "done building the factories! \n" +
                        "8. The number at the very top indicates how many cookies each store can buy, " +
                        "you will see that decrement as the factories produce the cookies! \n" +
                        "\n" +
                        "\n" +

                "The Prices of the Cookies are as follows: \n" +
                        "MnM: $5 \n" +
                        "Thin Mint: $6 \n" +
                        "Peanut Butter: $7 \n" +
                        "Chocolate Chip: $8"

        );
        secondLayout.getChildren().addAll(textInSecondWindow);

        Scene secondScene = new Scene(secondLayout, SECOND_WIDTH, SECOND_HEIGHT);

        secondStage.setTitle("Instructions");
        secondStage.setScene(secondScene);
        secondStage.getIcons().add(secondaryIcon);
        secondStage.show();


    }



}
