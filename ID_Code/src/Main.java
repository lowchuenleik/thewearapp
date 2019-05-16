import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.List;

public class Main extends Application {

    static ScreenController screenController;

    private int width = 300;
    private int height = 534;

    static Pane mainPage;
    static FXMLLoader loader;

    // IMAGES: or image object
    static File background;
    static LinkedList<File> clothes; // or List<String>? for top and bottom
    static LinkedList<File> accessories;

    // TODO: Fixed images, ignoring for now
    private String gear;
    private String leftPanel;
    private String rightPanel;
    private String leftArrow;
    private String rightArrow;

    // text
    private String day;
    private String temp;
    private String text;

    // hourly screen (time, weather icon, temperature)
    private List<List<String>> hourlyData;

    // weekly screen (time, clothes icon, temperature)
    private List<List<String>> weeklyData;

    // TODO: initialise fields with Today class and maybe API class
    public void initialise(Today today){
        clothes = today.getClothes();
        accessories = today.getAccessories();
        background = today.getBackground();
        temp = today.getTemperature() + "°";
        text = today.getMessage();

        //TODO: today needs getDay()
//        day = today.getDay();
    }

    public void refresh(){

    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Summary summary = new Summary();
        Today today = new Today(summary);
        initialise(today);
        primaryStage.setTitle("thewearapp");
        loader = new FXMLLoader();

        // TESTING
//        day = "Sat";

        loader.getNamespace().put("day", day);

        // TESTING
//        temp = "7°";

        loader.getNamespace().put("temp", temp);
        loader.getNamespace().put("text", text);

        // TESTING
//        clothes = new LinkedList<>();
//        clothes.add(new File("src\\data\\clothes\\cold1.png"));

        Controller.clothing = new Image("file:"+clothes.get(0).getPath());
        loader.getNamespace().put("clothing", Controller.clothing);

        // TESTING
//        clothes.add(new File("src\\data\\clothes\\cold2.png"));

        //ACTUAL
        if (accessories.size() >0) {
            Controller.accessory1Image = new Image(new FileInputStream(accessories.get(0).getPath()));
        }
        if(accessories.size() > 1) {
            Controller.accessory1Image = new Image(new FileInputStream(accessories.get(1).getPath()));
        }

        // TESTING
//        Controller.accessory1Image = new Image(new FileInputStream("src\\data\\accessories\\gloves.png"));
//        Controller.accessory2Image = new Image(new FileInputStream("src\\data\\accessories\\hat.png"));

        loader.getNamespace().put("accessory1Image", Controller.accessory1Image);
        loader.getNamespace().put("accessory2Image", Controller.accessory2Image);

        //ACTUAL
        Image tmp = new Image(new FileInputStream(background));

        // TESTING
//        Image tmp = new Image(new FileInputStream("src\\data\\backgrounds\\cloudy.png"));


        Controller.backgroundImage = new BackgroundImage(tmp, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        loader.getNamespace().put("backgroundImage", Controller.backgroundImage);

        // get format from fxml
        loader.setLocation(getClass().getResource("mainPage.fxml"));

//        String image = Main.class.getResource("data\\background\\cloudy.png").toExternalForm();
//        System.out.println(image);
//        mainPage.setStyle("-fx-background-image: url(\"" + image + "\"); " +
//                "-fx-background-position: center center; " +
//                "-fx-background-repeat: stretch;");
        mainPage = loader.load();

        mainPage.setBackground(new Background(Controller.backgroundImage));


//        Button btn = new Button();
//        btn.setText("say Hello World");
//        btn.setOnAction(new EventHandler<ActionEvent>(){
//            @Override
//            public void handle(ActionEvent event){
//                System.out.println("Hello World!!!!");
//            }
//        });
//        mainPage.getChildren().add(btn);
        Scene scene = new Scene(mainPage, this.width, this.height);
//        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
//        scene.getStylesheets().add(Main.class.getResource("Main.css").toExternalForm());
        screenController = new ScreenController(scene);
        screenController.addScreen("home", mainPage);
        screenController.addScreen("hourly", FXMLLoader.load(getClass().getResource("hourly.fxml")));
        screenController.addScreen("daily", FXMLLoader.load(getClass().getResource("daily.fxml")));
//        screenController.addScreen("home", FXMLLoader.load(getClass().getResource( "mainPage.fxml" )));
        screenController.addScreen("settings", FXMLLoader.load(getClass().getResource("settings.fxml")));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
