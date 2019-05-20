import javafx.application.Application;
import javafx.fxml.FXML;
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
    static Controller controller;
    static SettingsController settingsController;
    static HourlyController hourlyController;
    static DailyController dailyController;

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
    static String day;
    private static String temp;
    private static String text;

    // hourly screen (time, weather icon, temperature)
    private List<List<String>> hourlyData;

    // weekly screen (time, clothes icon, temperature)
    private List<List<String>> weeklyData;

    // ACTUAL
    // TODO: initialise fields with Today class and maybe API class
    public static void initialise(Today today){
        clothes = today.getClothes();
        accessories = today.getAccessories();
        background = today.getBackground();
        temp = today.getTemperature() + "°";
        text = today.getMessage();

        //TODO: today needs getDay()
        day = today.getDay();
    }

    public void refresh(){

    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        //TESTING
//        Summary summary = new Summary();
//        Today today = new Today(summary);
//        initialise(today);


        primaryStage.setTitle("thewearapp");
        loader = new FXMLLoader();

        // TESTING
        day = "Sat";
        temp = "7°";
        text = "It's cool weather, so feel free to bring along a jacket!";
        clothes = new LinkedList<>();
        clothes.add(new File("src\\data\\clothes\\cold1.png"));
        clothes.add(new File("src\\data\\clothes\\cold2.png"));
        Controller.accessory1Image = new Image(new FileInputStream("src\\data\\accessories\\gloves.png"));
        Controller.accessory2Image = new Image(new FileInputStream("src\\data\\accessories\\hat.png"));
        Image tmp = new Image(new FileInputStream("src\\data\\backgrounds\\cloudy.png"));

        //ACTUAL
//        if (accessories.size() >0) {
//            Controller.accessory1Image = new Image(new FileInputStream(accessories.get(0).getPath()));
//        }
//        if(accessories.size() > 1) {
//            Controller.accessory1Image = new Image(new FileInputStream(accessories.get(1).getPath()));
//        }
//        Image tmp = new Image(new FileInputStream(background));

        loader.getNamespace().put("day", day);
        loader.getNamespace().put("temp", temp);
        loader.getNamespace().put("text", text);

        Controller.clothing = new Image("file:"+clothes.get(0).getPath());
        loader.getNamespace().put("clothing", Controller.clothing);



        loader.getNamespace().put("accessory1Image", Controller.accessory1Image);
        loader.getNamespace().put("accessory2Image", Controller.accessory2Image);


        Controller.backgroundImage = new BackgroundImage(tmp, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        loader.getNamespace().put("backgroundImage", Controller.backgroundImage);

        // get format from fxml
        loader.setLocation(getClass().getResource("mainPage.fxml"));

        controller = new Controller();
        loader.setController(controller);

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
        primaryStage.show();

//        scene.getStylesheets().add(Main.class.getResource("Main.css").toExternalForm());
        screenController = new ScreenController(scene);
        screenController.addScreen("home", mainPage);

//        screenController.addScreen("home", FXMLLoader.load(getClass().getResource( "mainPage.fxml" )));

        FXMLLoader hourlyLoader = new FXMLLoader();
        hourlyController = new HourlyController();
        hourlyLoader.setController(hourlyController);
        hourlyLoader.setLocation(getClass().getResource("hourly.fxml"));
        screenController.addScreen("hourly", hourlyLoader.load());

        FXMLLoader dailyLoader = new FXMLLoader();
        dailyController = new DailyController();
        dailyLoader.setController(dailyController);
        dailyLoader.setLocation(getClass().getResource("daily.fxml"));
        screenController.addScreen("daily", dailyLoader.load());

        FXMLLoader settingsLoader = new FXMLLoader();
        settingsController = new SettingsController();
        settingsLoader.setController(settingsController);
        settingsLoader.setLocation(getClass().getResource("settings.fxml"));
        screenController.addScreen("settings", settingsLoader.load());
//        screenController.addScreen("settings", FXMLLoader.load(getClass().getResource("settings.fxml")));
    }

    public static void main(String[] args) {
        launch(args);
    }
}