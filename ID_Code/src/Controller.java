package thewearapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

// add button actions for the other buttons

public class Controller implements Initializable {
    @FXML
    private Background background;

    @FXML
    private ImageView imageView;
    static int clothesIndex=0;
    @FXML
    static Image clothing;

    @FXML
    static ImageView accessory1;
    @FXML
    static ImageView accessory2;
    @FXML
    static Image accessory1Image;
    @FXML
    static Image accessory2Image;

    @FXML
    static BackgroundImage backgroundImage;



    @FXML
    protected void setOnAction(ActionEvent event){

        System.out.println("Hello World!!!!");
    }

    @FXML
    protected void toHome(ActionEvent event){
        Main.screenController.activate("home");
    }

    @FXML
    protected void toSettings(ActionEvent event){
        Main.screenController.activate("settings");
    }

    @FXML
    protected void toDaily(ActionEvent event){
        Main.screenController.activate("daily");
    }

    @FXML
    protected void toHourly(ActionEvent event){
        Main.screenController.activate("hourly");
    }


    @FXML
    protected void nextClothes(ActionEvent event) throws IOException {
        if (clothesIndex < Main.clothes.size()-1){
            clothesIndex++;
        }
        else{
            clothesIndex=0;
        }
        Controller.clothing = new Image("file:"+Main.clothes.get(clothesIndex).getPath());
        imageView.setImage(Controller.clothing);
    }

    @FXML
    protected void previousClothes(ActionEvent event) throws IOException {
        if (clothesIndex > 0){
            clothesIndex--;
        }
        else{
            clothesIndex=Main.clothes.size()-1;
        }
        Controller.clothing = new Image("file:"+Main.clothes.get(clothesIndex).getPath());
        imageView.setImage(Controller.clothing);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        File file = new File(Main.clothes);
//        Image image = new Image(file.toURI().toString());
//        imageView = new ImageView();
//        imageView.setImage(new Image("file:"+Main.clothes.get(0).getPath()));
//        Controller.accessory1 = new ImageView();
//        Controller.accessory2 = new ImageView();
//
//        Controller.accessory1Image = new Image("file:"+"data\\accessories\\gloves.png");
//
//        Controller.accessory1.setImage(accessory1Image);
//        Controller.accessory1Image = new Image("file:"+"data\\accessories\\hat.png");
//
//        Controller.accessory2.setImage(new Image("file:"+"data\\accessories\\hat.png"));
    }
}
