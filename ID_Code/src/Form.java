package thewearapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.List;

public class Form extends Application {

    private int width = 300;
    private int height = 275;

    // IMAGES: or image object
    private String background;
    private String clothes; // or List<String>? for top and bottom
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

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
        primaryStage.setTitle("thewearapp");

        Button btn = new Button();
        btn.setText("say Hello World");
        btn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                System.out.println("Hello World!!!!");
            }
        });
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, this.width, this.height));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
