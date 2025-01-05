/**
 * Course: SWE 2410
 * Name: Salvin, Trent & Grant
 * Date: 12/3/24
 * Due: 12/8/24
 */
package lab10;

// all required imports
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.util.Duration;
import lab10.cookie.Cookie;
import lab10.cookieFactory.*;
import lab10.transport.Transportation;
import lab10.transportFactory.BoatFactory;
import lab10.transportFactory.LogisticFactory;
import lab10.transportFactory.PlaneFactory;
import lab10.transportFactory.TruckFactory;

import java.net.URL;
import java.util.*;

/**
 * class for GameController
 */
public class GameController implements Initializable {

    // all relevant attributes
    private static final int DISTRIBUTION_CASH = 500;
    private static final int FIVE_HUNDRED = 500;
    private static final int SEVENTY_FIVE = 75;
    private static final int FACTORY_CASH = 1000;
    private static final int ONE_THOUSAND = 1000;
    private static final int ONE_HUNDRED = 100;
    private static final int FIFTY = 50;
    private static final int TWO_THOUSAND_ONE = 2001;
    private static final int THREE_THOUSAND_ONE = 3001;
    private static final int SPACING = 5;
    private static final double FADE = 0.5;

    // number of cookies being ordered from each store
    private int chocolateCount = FIFTY;
    private int peanutButterCount = FIFTY;
    private int thinMintCount = FIFTY;
    private int mnmCount = FIFTY;

    // number of factories created
    private int chocolateFactoryCount = 0;
    private int peanutButterFactoryCount = 0;
    private int thinMintFactoryCount = 0;
    private int mnmFactoryCount = 0;

    // initializing the losses of each type of cookie
    private int chocolateChipLoss = 0;
    private int thinMintLoss = 0;
    private int peanutButterLoss = 0;
    private int mnmLoss = 0;

    // declaring the random class to generating random numbers
    private final Random random = new Random();



    // all FXML attributes
    @FXML
    private GridPane theGrid;

    @FXML
    private ChoiceBox<String> logisticsDropDown;

    @FXML
    private ChoiceBox<String> factoryDropDown;

    @FXML
    private Label moneyLabel;

    @FXML
    private Pane transportPane1;

    @FXML
    private Pane transportPane2;

    @FXML
    private Pane transportPane3;

    @FXML
    private Pane transportPane4;


    @FXML
    private Text ChocolateStoreText;

    @FXML
    private Text PeanutButterStoreText;

    @FXML
    private Text ThinMintStoreText;

    @FXML
    private Text MnMStoreText;

    @FXML
    private Text BadChocolateCookie;

    @FXML
    private Text BadThinMintCookie;

    @FXML
    private Text BadPeanutButterCookie;

    @FXML
    private Text BadMnMCookie;

    @FXML
    private Text ErrorMessage;

    // other relevant attributes
    private int money;
    private int factoryGridPlace;
    private int transportationGridPlace;

    // boolean attributes to ensure the cookie factories are built
    private boolean chocolateFactoryBuilt = false;
    private boolean mnmFactoryBuilt = false;
    private boolean thinMintFactoryBuilt = false;
    private boolean peanutButterFactoryBuilt = false;

    // boolean attribute to stop baking cookies
    private boolean stopBakingCookies = false;

    // data structure attributes
    private final HashMap<CookieFactory, Pane> cookieFactories = new HashMap<>();
    private final HashMap<CookieFactory, ProgressBar> factoryProgressBars = new HashMap<>();
    private final ArrayList<Transportation> transportations = new ArrayList<>();
    private final ArrayList<CookieFactory> factories = new ArrayList<>();
    private final ArrayList<Stack<Cookie>> initialCookieStorage = new ArrayList<>();

    /**
     * This initializes method is run before any other methods but after the JavaFX is loaded
     * @param url uniform resource location
     * @param resourceBundle resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // disabling the logistics drop down box
        logisticsDropDown.setDisable(true);

        // selecting the options between chocolate chip, mnm, peanut butter and thin mint
        ObservableList<String> cookieTypes = FXCollections.observableArrayList();
        cookieTypes.addAll("Chocolate Chip", "MnM", "Peanut Butter", "Thin Mint");
        factoryDropDown.setItems(cookieTypes);

        // selecting the options between boat, plane and truck for drop down
        ObservableList<String> delivery = FXCollections.observableArrayList();
        delivery.addAll("Boat", "Plane", "Truck");
        logisticsDropDown.setItems(delivery);

        // setting the text to 50 for each store
        ChocolateStoreText.setText("Chocolate: 50");
        MnMStoreText.setText("MnM: 50");
        PeanutButterStoreText.setText("Peanut Butter: 50");
        ThinMintStoreText.setText("Thin Mint: 50");

        // setting the loss text to $0 for each store
        BadChocolateCookie.setText("Loss: $0");
        BadMnMCookie.setText("Loss: $0");
        BadPeanutButterCookie.setText("Loss: $0");
        BadThinMintCookie.setText("Loss: $0");

        // here, we add up to 4 cookies
        initialCookieStorage.add(new Stack<>()); // Storage for factory 1
        initialCookieStorage.add(new Stack<>()); // Storage for factory 2
        initialCookieStorage.add(new Stack<>()); // Storage for factory 3
        initialCookieStorage.add(new Stack<>()); // storage for factory 4

        // setting up a random number of cash
        money = ONE_THOUSAND + random.nextInt(THREE_THOUSAND_ONE);
        moneyLabel.setText("$"+money);

        factoryGridPlace = 0;
        transportationGridPlace = 0;

        theGrid.setFocusTraversable(true);

    }


    /**
     * This method is called to create a new logistic factory and vehicle.
     */
    @FXML
    private void buildDis(){
        if(money >= DISTRIBUTION_CASH && transportations.size() < 4 &&
                logisticsDropDown.getValue() != null) {

            Pane content = new HBox();
            switch(logisticsDropDown.getValue()) {

                // switching to a boat
                case "Boat":
                    LogisticFactory boat = new BoatFactory();
                    Transportation createdBoat = boat.createTransportation();
                    addTransportation(createdBoat);
                    transportations.add(createdBoat);
                    money -= FIVE_HUNDRED;
                    addTransportationToFactory(createdBoat);
                    createLogisticsFactoryImage(boat, content);
                    break;

                // switching to a plane
                case "Plane":
                    LogisticFactory plane = new PlaneFactory();
                    Transportation createdPlane = plane.createTransportation();
                    transportations.add(createdPlane);
                    addTransportation(createdPlane);
                    money -= FIVE_HUNDRED;
                    addTransportationToFactory(createdPlane);
                    createLogisticsFactoryImage(plane, content);
                    break;

                // switching to a truck
                case "Truck":
                    LogisticFactory truck = new TruckFactory();
                    Transportation createdTruck = truck.createTransportation();
                    transportations.add(createdTruck);
                    addTransportation(createdTruck);
                    money -= FIVE_HUNDRED;
                    addTransportationToFactory(createdTruck);
                    createLogisticsFactoryImage(truck, content);
                    break;

                // default option
                default:
                    ErrorMessage.setText("Error Message: Select a Transport!");
            }

            theGrid.add(content, transportationGridPlace, 2);
            transportationGridPlace++;
        }

        theGrid.requestFocus();

    }

    /**
     * This method creates the image of the logistics factory
     * and assigns it to a row of the grid view.
     * @param factory The factory to create the ImageView for
     * @param content The content pane
     */
    private void createLogisticsFactoryImage(LogisticFactory factory, Pane content) {
        updateMoneyLabel();
        ImageView factoryImage = factory.getImage();
        factoryImage.setPreserveRatio(true);
        factoryImage.setFitWidth(SEVENTY_FIVE);
        content.getChildren().add(factoryImage);
    }

    /**
     * This method adds the new transportation to a transportPane
     * @param createdTransportation The new transportation that was created
     */
    private void addTransportation(Transportation createdTransportation) {
        if (transportationGridPlace == 0) {
            transportPane1.getChildren().add(createdTransportation.getImage());
        } else if (transportationGridPlace == 1) {
            transportPane2.getChildren().add(createdTransportation.getImage());
        } else if (transportationGridPlace == 2) {
            transportPane3.getChildren().add(createdTransportation.getImage());
        } else if (transportationGridPlace == 3) {
            transportPane4.getChildren().add(createdTransportation.getImage());
        }
    }

    /**
     * This method builds a new factory with its location
     * depending on the number of factories that exist.
     */
    @FXML
    private void buildFac(){
        if(money >= FACTORY_CASH && factories.size() < 4 && factoryDropDown.getValue() != null) {
            Pane content = new HBox();
            switch(factoryDropDown.getValue()) {

                // building a chocolate chip factory
                case "Chocolate Chip":
                    chocolateFactoryCount++;
                    CookieFactory choc = new ChocolateChipFactory();
                    factories.add(choc);
                    cookieFactories.put(choc, content);
                    money -= ONE_THOUSAND;
                    addFactoryToTransportation(choc);
                    createIconImage(content, choc);
                    chocolateFactoryBuilt = true;
                    break;

                // building a MnM factory
                case "MnM":
                    mnmFactoryCount++;
                    CookieFactory mnm = new MnMFactory();
                    factories.add(mnm);
                    cookieFactories.put(mnm, content);
                    money -= ONE_THOUSAND;
                    addFactoryToTransportation(mnm);
                    createIconImage(content, mnm);
                    mnmFactoryBuilt = true;
                    break;

                // building a peanut butter factory
                case "Peanut Butter":
                    peanutButterFactoryCount++;
                    CookieFactory pbf = new PeanutButterFactory();
                    factories.add(pbf);
                    cookieFactories.put(pbf, content);
                    money -= ONE_THOUSAND;
                    addFactoryToTransportation(pbf);
                    createIconImage(content, pbf);
                    peanutButterFactoryBuilt = true;
                    break;

                // building a thin mint factory
                case "Thin Mint":
                    thinMintCount++;
                    CookieFactory tm = new ThinMintFactory();
                    factories.add(tm);
                    cookieFactories.put(tm, content);
                    money -= ONE_THOUSAND;
                    addFactoryToTransportation(tm);
                    createIconImage(content, tm);
                    thinMintFactoryBuilt = true;
                    break;

                // the default
                default:
                    ErrorMessage.setText("Error Message: Select a Factory!");
            }

            theGrid.add(content, factoryGridPlace, 3);
            System.out.println(theGrid.getChildren());
            factoryGridPlace++;
        } else {
            ErrorMessage.setText("Error Message: Select a Factory!");
        }

        theGrid.requestFocus();
    }

    /**
     * This method creates the image icon for the new factory that is created.
     * @param content The content pane to put the factory image in
     */
    private void createIconImage(Pane content, CookieFactory factory) {

        // calling the updateMoneyLabel() method
        updateMoneyLabel();


        // retrieving the image of the factory
        String factoryImage = Objects.requireNonNull(
                GameController.class.getResource("images/factory.png")).toString();

        // creating a progress bar with progress at 0%
        ProgressBar factoryProgressBar = new ProgressBar();
        factoryProgressBar.setPrefWidth(SEVENTY_FIVE);
        factoryProgressBar.setProgress(0.0);

        // altering the image of the factory to make it small
        ImageView factoryView = new ImageView(new Image(factoryImage));
        factoryView.setFitWidth(SEVENTY_FIVE);
        factoryView.setFitHeight(ONE_HUNDRED);

        // VBox to combine both factory and progress bar
        VBox factoryBox = new VBox();
        factoryBox.getChildren().addAll(factoryView, factoryProgressBar);
        factoryBox.setSpacing(SPACING);
        factoryBox.setAlignment(Pos.CENTER);

        // adding to the content view
        content.getChildren().add(factoryBox);

        // adding to the hash maps
        factoryProgressBars.put(factory, factoryProgressBar);


    }

    /**
     * This method adds a transportation method to a factory
     * @param factory The factory to add transport to
     */
    private void addFactoryToTransportation(CookieFactory factory) {
        if (transportations.size() >= factories.size()) {
            transportations.get(factories.indexOf(factory)).setFactory(factory);
        }
    }

    /**
     * This method adds a transportation method to a factory
     * @param transportation The transportation to add a factory to
     */
    private void addTransportationToFactory(Transportation transportation) {
        if (factories.size() >= transportations.size()) {
            transportation.setFactory(factories.get(transportations.indexOf(transportation)));
        }
    }

    /**
     * This method updated the money label to display the correct amount of money
     */
    private void updateMoneyLabel() {
        moneyLabel.setText("$" + money);
    }

    /**
     * This method calls the advanceTime method when the right arrow is pressed
     * @param keyEvent The keyEvent of a key that is pressed
     */
    @FXML
    public void onKeyPressed(KeyEvent keyEvent) {

        if(money > 0 && keyEvent.getCode() == KeyCode.RIGHT) {
            // updating the progress bars
            updateProgressBars();

            // ensuring the right key press doesn't interfere with anything
            theGrid.requestFocus();

            // calling the advanceTime() method
            advanceTime();
        } else if(money <= 0 && keyEvent.getCode() == KeyCode.RIGHT) {
            ErrorMessage.setText("Error Message: Not Enough Money!");
        }
    }

    /**
     * method for updating the progress bars upon each key press
     */
    public void updateProgressBars() {
        for(Map.Entry<CookieFactory, ProgressBar> entry : factoryProgressBars.entrySet()) {

            // retrieving the key and the value
            ProgressBar progressBar = entry.getValue();

            // updating the progress bar
            double currProgress = progressBar.getProgress();
            double newProgress = Math.min(currProgress + 1.0 / FIFTY, 1.0);
            progressBar.setProgress(newProgress);

            // animation for when the progress bar is full
            if(progressBar.getProgress() >= 1.0) {
                progressBar.setStyle("-fx-accent: green");
                FadeTransition fade = new FadeTransition(Duration.seconds(1), progressBar);
                fade.setFromValue(1.0);
                fade.setToValue(FADE);
                fade.setCycleCount(Animation.INDEFINITE);
                fade.setAutoReverse(true);
                fade.play();

            }
        }
    }

    /**
     * This method advances time when Called. It calls
     * several different methods that are time-dependent.
     */
    @FXML
    private void advanceTime() {

        // allow the user to build logistics
        if(money < ONE_THOUSAND && !stopBakingCookies) {
            logisticsDropDown.setDisable(false);
            stopBakingCookies = true;
            int logisticsCash = FIVE_HUNDRED + random.nextInt(TWO_THOUSAND_ONE);
            money = money + logisticsCash;
        }

        // calling the update store order text
        updateStoreOrderText();

        // updating the money label
        updateMoneyLabel();

        // baking the cookies
        bakeCookies();

        // allowing the transportation to move
        moveTransports();

        // ensuring the key presses don't interfere with UI
        theGrid.requestFocus();
    }

    /**
     * method for update the store order text
     */
    public void updateStoreOrderText() {

        for(CookieFactory factory : factories) {

            int index = factories.indexOf(factory);
            Stack<Cookie> cookieStack = initialCookieStorage.get(index);

            int decrementCookieCount = cookieStack.size();

            switch(factory.getFactoryType()) {

                case "Thin Mint Factory":
                    // updating the number of thin mint cookies ordered
                    if(thinMintFactoryBuilt && thinMintCount > 0) {
                        thinMintCount = thinMintCount - decrementCookieCount;
                        thinMintCount = Math.max(thinMintCount, 0);
                    }
                    ThinMintStoreText.setText("Thin Mint: " + thinMintCount);
                    break;


                case "MnM Factory":
                    // updating the number of mnm cookies ordered
                    if(mnmFactoryBuilt && mnmCount > 0) {
                        mnmCount = mnmCount - decrementCookieCount;
                        mnmCount = Math.max(mnmCount, 0);
                    }
                    MnMStoreText.setText("Mnm: " + mnmCount);
                    break;


                case "Peanut Butter Factory":
                    // updating the number of peanut butter cookies ordered
                    if(peanutButterFactoryBuilt && peanutButterCount > 0) {
                        peanutButterCount = peanutButterCount - decrementCookieCount;
                        peanutButterCount = Math.max(peanutButterCount, 0);
                    }
                    PeanutButterStoreText.setText("Peanut Butter: " + peanutButterCount);
                    break;


                case "Chocolate Chip Factory":
                    // updating the number of chocolate cookies ordered
                    if(chocolateFactoryBuilt && chocolateCount > 0) {
                        chocolateCount = chocolateCount - decrementCookieCount;
                        chocolateCount = Math.max(chocolateCount, 0);
                    }
                    ChocolateStoreText.setText("Chocolate: " + chocolateCount);
                    break;

            }

        }

    }

    /**
     * This method bakes cookies at all the factories that are created.
     */
    private void bakeCookies() {

        // for looping through the collection of factories
        for (CookieFactory factory : factories) {

            // retrieving the storage of cookies from each factory
            Stack<Cookie> cookieStack = initialCookieStorage.get(factories.indexOf(factory));

            if(cookieStack.size() < FIFTY) {

                // creating a cookie
                Cookie cookie = factory.createCookie();
                cookieStack.push(cookie);

                // checking for the type of cookie to see the losses
                if(cookie.getCookieAge() == 1) {

                    switch(cookie.getType()) {

                        case "MnM":
                            mnmLoss = mnmLoss + (cookie.getCookiePrice() * mnmFactoryCount);
                            BadMnMCookie.setText("Loss: $" + mnmLoss);
                            break;

                        case "Peanut Butter":
                            peanutButterLoss = peanutButterLoss + (cookie.getCookiePrice() * peanutButterFactoryCount);
                            BadPeanutButterCookie.setText("Loss: $" + peanutButterLoss);
                            break;

                        case "Thin Mint":
                            thinMintLoss = thinMintLoss + (cookie.getCookiePrice() * thinMintFactoryCount);
                            BadThinMintCookie.setText("Loss: $" + thinMintLoss);
                            break;

                        case "Chocolate Chip":
                            chocolateChipLoss = chocolateChipLoss + (cookie.getCookiePrice() * chocolateFactoryCount);
                            BadChocolateCookie.setText("Loss: $" + chocolateChipLoss);
                            break;

                    }
                }

                // subtracting the costs of making a cookie
                money = money - cookie.getCookiePrice();


                // adding the cookie to the storage
                cookieStack.add(cookie);

                // setting up the HBox pane
                HBox hbox = (HBox) cookieFactories.get(factory);
                if (hbox.getChildren().size() > 1) {
                    hbox.getChildren().removeLast();
                }

                // retrieving the image of the cookie
                ImageView cookieImage = cookie.getImage();

                // resizing the cookie image to make it more manageable
                cookieImage.setPreserveRatio(true);
                cookieImage.setFitWidth(FIFTY);

                // setting up a stack pane
                StackPane stackPane = new StackPane();
                stackPane.getChildren().add(cookieImage);

                // setting up a count of cookies
                Text cookieCount = new Text();
                cookieCount.setFocusTraversable(false);

                // retrieving the number of cookies
                int numCookies = initialCookieStorage.get(factories.indexOf(factory)).size();

                // setting the number of cookies
                cookieCount.setText(Integer.toString(numCookies));

                // adding the number of cookies to the stack pane
                stackPane.getChildren().add(cookieCount);

                // updating the HBox
                hbox.getChildren().add(stackPane);
            }

        }
    }

    /**
     * This method moves all the transports one step and checks if they are ready for pickup
     */
    private void moveTransports() {
        for (Transportation transport : transportations) {
            transport.moveVehicle();
            checkForPickup(transport);
        }
    }

    /**
     * This method checks if transport is ready for pickup
     * @param transport The transport to check for pickup
     */
    private void checkForPickup(Transportation transport) {
        if(transport.readyForPickup()) {
            int cookieStorageLocation = transportations.indexOf(transport);
            Stack<Cookie> cookieCrate = new Stack<>();
            for(int i = 0; i < transport.getStorage() &&
                    !initialCookieStorage.get(cookieStorageLocation).isEmpty(); i++) {
                cookieCrate.add(initialCookieStorage.get(cookieStorageLocation).pop());
            }
            transport.loadCookies(cookieCrate);
        }
    }

}

