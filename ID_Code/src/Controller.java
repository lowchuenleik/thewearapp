import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

// add button actions for the other buttons

public class Controller implements Initializable {
    // IMPORTANT: fields used by fxml CANNOT be marked static

//    @FXML
//    private AnchorPane jack;
    @FXML
    private Slider slider_jacket;
    @FXML
    private Slider slider_rain;

    @FXML
    private CheckBox cb_1;
    @FXML
    private CheckBox cb_2;
    @FXML
    private CheckBox cb_3;
    @FXML
    private CheckBox cb_4;
    @FXML
    private CheckBox cb_5;

    @FXML
    private CheckBox notifications;

    @FXML
    private void setFahrenheit(){
        Settings.setUnit(false);
    }

    @FXML
    private void setCelsius(){
        Settings.setUnit(true);
    }

    @FXML
    public void refresh() throws FileNotFoundException {
//        System.out.println(jack.getLayoutX());
        Settings.setEnableNotifications(notifications.isSelected());
        System.out.println(slider_jacket.getValue());
        Settings.setJacket((int)slider_jacket.getValue());
        System.out.println(slider_rain.getValue());
        Settings.setRain((int)slider_rain.getValue());
        System.out.println(Settings.getJacket());
        System.out.println(Settings.getClothes());
        System.out.println(cb_1.isSelected());
        System.out.println(cb_2.isSelected());
        Settings.setClothes(cb_1.isSelected(), cb_2.isSelected(), cb_3.isSelected(),cb_4.isSelected(),cb_5.isSelected());
//        System.out.println();
        //Slider slider = (Slider)jack.getChildren().get(0);
        //System.out.println(slider.getMin());


        // TESTING
        Summary summary = new Summary();
        Today today = new Today(summary);
        Main.initialise(today);
        if (Main.accessories.size() >0) {
            Controller.accessory1Image = new Image(new FileInputStream(Main.accessories.get(0).getPath()));
            accessory1.setImage(accessory1Image);
        }
        if(Main.accessories.size() > 1) {
            Controller.accessory2Image = new Image(new FileInputStream(Main.accessories.get(1).getPath()));
            accessory2.setImage(accessory2Image);
        }
        Image tmp = new Image(new FileInputStream(Main.background));
        Controller.backgroundImage = new BackgroundImage(tmp, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Main.mainPage.setBackground(new Background(Controller.backgroundImage));
    }



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
    protected void toHome(ActionEvent event) throws FileNotFoundException {
        refresh();
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
