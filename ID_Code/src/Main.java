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

    static File background;
    static LinkedList<File> clothes;
    static LinkedList<File> accessories;

    // text fields on home page
    static String day;
    private static String temp;
    private static String text;

    static Summary todaySummary;

    public static void initialise(Today today){
        clothes = today.getClothes();
        accessories = today.getAccessories();
        background = today.getBackground();
        temp = today.getTemperature() + "°";
        text = today.getMessage();
        day = today.getDay();
    }

    // this is the main method that is run for the GUI
    @Override
    public void start(Stage primaryStage) throws Exception{
        todaySummary = new Summary();
        Today today = new Today(todaySummary);
        initialise(today);

        primaryStage.setTitle("thewearapp");
        loader = new FXMLLoader();

        if (accessories.size() > 0) {
            Controller.accessory1Image = new Image(new FileInputStream(accessories.get(0).getPath()));
        }
        if(accessories.size() > 1) {
            Controller.accessory1Image = new Image(new FileInputStream(accessories.get(1).getPath()));
        }
        Image tmp = new Image(new FileInputStream(background));
        Controller.clothing = new Image("file:"+clothes.get(0).getPath());
        Controller.backgroundImage = new BackgroundImage(tmp, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

        loader.getNamespace().put("mainDay", day);
        loader.getNamespace().put("mainTemp", temp);
        loader.getNamespace().put("mainText", text);
        loader.getNamespace().put("clothing", Controller.clothing);
        loader.getNamespace().put("accessory1Image", Controller.accessory1Image);
        loader.getNamespace().put("accessory2Image", Controller.accessory2Image);
        loader.getNamespace().put("backgroundImage", Controller.backgroundImage);

        // get format from fxml
        loader.setLocation(getClass().getResource("mainPage.fxml"));
        // set the controller for the home page
        controller = new Controller();
        loader.setController(controller);
        mainPage = loader.load();
        mainPage.setBackground(new Background(Controller.backgroundImage));

        Scene scene = new Scene(mainPage, this.width, this.height);
        primaryStage.setScene(scene);
        primaryStage.show();

        scene.getStylesheets().add(Main.class.getResource("Main.css").toExternalForm());
        screenController = new ScreenController(scene);
        screenController.addScreen("home", mainPage);

        // initialise hourly page
        FXMLLoader hourlyLoader = new FXMLLoader();
        hourlyController = new HourlyController();
        hourlyLoader.setController(hourlyController);
        hourlyLoader.setLocation(getClass().getResource("hourly.fxml"));
        screenController.addScreen("hourly", hourlyLoader.load());

        // initialise daily page
        FXMLLoader dailyLoader = new FXMLLoader();
        dailyController = new DailyController();
        dailyLoader.setController(dailyController);
        dailyLoader.setLocation(getClass().getResource("daily.fxml"));
        screenController.addScreen("daily", dailyLoader.load());

        // initialise settings page
        FXMLLoader settingsLoader = new FXMLLoader();
        settingsController = new SettingsController();
        settingsLoader.setController(settingsController);
        settingsLoader.setLocation(getClass().getResource("settings.fxml"));
        screenController.addScreen("settings", settingsLoader.load());
    }

    public static void main(String[] args) {
        launch(args);
    }
}