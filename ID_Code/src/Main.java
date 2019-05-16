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
    private int height = 275;

    static Pane mainPage;
    static FXMLLoader loader;

    // IMAGES: or image object
    private String background;
    static LinkedList<File> clothes; // or List<String>? for top and bottom
    private String accessories;

    // Fixed images
    private String gear;
    private String leftPanel;
    private String rightPanel;
    private String leftArrow;
    private String rightArrow;

    // text
    private String day;
    private float temp;
    private String text;

    // hourly screen (time, weather icon, temperature)
    private List<List<String>> hourlyData;

    // weekly screen (time, clothes icon, temperature)
    private List<List<String>> weeklyData;

    // TODO: initialise fields with Today class and maybe API class
    public void initialise(){

    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        initialise();
        primaryStage.setTitle("thewearapp");
        loader = new FXMLLoader();
        day = "Sat";
        loader.getNamespace().put("day", day);
        clothes = new LinkedList<>();
        clothes.add(new File("data\\clothes\\cold1.png"));
        Controller.clothing = new Image("file:"+clothes.get(0).getPath());
        loader.getNamespace().put("clothing", Controller.clothing);
        clothes.add(new File("data\\clothes\\cold2.png"));


        Controller.accessory1Image = new Image("file:"+"data\\accessories\\gloves.png");
        Controller.accessory2Image = new Image("file:"+"data\\accessories\\hat.png");

        loader.getNamespace().put("accessory1Image", Controller.accessory1Image);
        loader.getNamespace().put("accessory2Image", Controller.accessory2Image);

        Image tmp = new Image(new FileInputStream("data\\backgrounds\\cloudy.png"));
        Controller.backgroundImage = new BackgroundImage(tmp, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        loader.getNamespace().put("backgroundImage", Controller.backgroundImage);

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
        screenController.addScreen("hourly", FXMLLoader.load(getClass().getResource( "hourly.fxml" )));
        screenController.addScreen("daily", FXMLLoader.load(getClass().getResource("daily.fxml")));
//        screenController.addScreen("home", FXMLLoader.load(getClass().getResource( "mainPage.fxml" )));
        screenController.addScreen("settings", FXMLLoader.load(getClass().getResource( "settings.fxml" )));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
