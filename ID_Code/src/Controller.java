import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    // IMPORTANT: fields used by fxml CANNOT be marked static

    @FXML
    private Text text;
    @FXML
    private Text day;
    @FXML
    private Text temp;
    @FXML
    private Button settingsButton;

    @FXML
    private Background background;

    @FXML
    private ImageView imageView;

    static int clothesIndex=0;
    @FXML
    static Image clothing;

    @FXML
    private ImageView accessory1;
    @FXML
    private ImageView accessory2;
    @FXML
    static Image accessory1Image;
    @FXML
    static Image accessory2Image;

    @FXML
    static BackgroundImage backgroundImage;

    @FXML
    protected void toHome(ActionEvent event) {
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
        try {
            ImageView settingsIcon = new ImageView(new Image(new FileInputStream("src/data/gear.png")));
            settingsIcon.setPreserveRatio(true);
            settingsIcon.setFitHeight(25.0);
            settingsButton.setGraphic(settingsIcon);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("src/data/gear.png not found");
        }
    }

    @FXML
    public void refresh() throws FileNotFoundException {
        Summary summary = new Summary();
        Today today = new Today(summary);
        Main.initialise(today);
        accessory1.setImage(null);
        accessory2.setImage(null);
        if (Main.accessories.size() > 0){
            Controller.accessory1Image = new Image(new FileInputStream(Main.accessories.get(0).getPath()));
            accessory1.setImage(accessory1Image);
            if(Main.accessories.size() > 1) {
                Controller.accessory2Image = new Image(new FileInputStream(Main.accessories.get(1).getPath()));
                accessory2.setImage(accessory2Image);
            }
        }
        Image tmp = new Image(new FileInputStream(Main.background));
        Controller.backgroundImage = new BackgroundImage(tmp, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Main.mainPage.setBackground(new Background(Controller.backgroundImage));

        // set clothes
        imageView.setImage(new Image(new FileInputStream(Main.clothes.get(0).getPath())));

        // set fields in home page
        temp.setText(today.getTemperature() + "°");
        text.setText(today.getMessage());
        day.setText(today.getDay());
    }
}
