import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DailyController implements Initializable {

    @FXML
    private Button settingsButton;
    @FXML
    private AnchorPane anchorpaneHours;
    @FXML
    public void toHome(ActionEvent event){
        Main.controller.toHome(event);
    }
    @FXML
    public void toSettings(ActionEvent event){ Main.controller.toSettings(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // initialise settings button
        try {
            ImageView settingsIcon = new ImageView(new Image(new FileInputStream("src/data/gear.png")));
            settingsIcon.setPreserveRatio(true);
            settingsIcon.setFitHeight(20.0);
            settingsButton.setGraphic(settingsIcon);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("src/data/gear.png not found");
        }

        // initialise daily page
        Weekly weekly = new Weekly();
        List<String> dayNames = weekly.getDayNames();
        List<Integer> dayTemperatures = weekly.getTemperatures();
        List<File> dayWeatherIcons = weekly.getWeatherIcons();

        for (int i=0; i<dayNames.size(); i++) {
            String tempText = dayTemperatures.get(i) + "°C";
            String nameText = dayNames.get(i);
            String iconPath = dayWeatherIcons.get(i).getPath();

            AnchorPane secondHour = new AnchorPane();
            secondHour.setLayoutX(2.0);
            secondHour.setLayoutY(i*58.0);
            secondHour.setPrefHeight(58.0);
            secondHour.setPrefWidth(287.0);
            secondHour.setStyle(" -fx-border-color: white;\n-fx-border-width: 5px;\n-fx-background-color: #848689\n\n");

            ImageView secondWeatherIcon = new ImageView();
            secondWeatherIcon.setFitHeight(43.0);
            secondWeatherIcon.setFitWidth(57.33333333333333);
            secondWeatherIcon.setLayoutX(129.0);
            secondWeatherIcon.setLayoutY(8.0);
            secondWeatherIcon.setPickOnBounds(true);
            secondWeatherIcon.setPreserveRatio(true);

            try {
                secondWeatherIcon.setImage(new Image(new FileInputStream(iconPath)));
            } catch (FileNotFoundException e) {
                System.out.println(iconPath + " can't be found");
                e.printStackTrace();
            }

            Text secondTemp = new Text();
            secondTemp.setFill(Color.WHITE);
            secondTemp.setLayoutX(191.0);
            secondTemp.setLayoutY(40.0);
            secondTemp.setStrokeType(StrokeType.OUTSIDE);
            secondTemp.setStrokeWidth(0.0);
            secondTemp.setStyle("-fx-font-weight:bold;\n-fx-font-size: 30;");
            secondTemp.setText(tempText);

            Text secondHourText = new Text();
            secondHourText.setFill(Color.WHITE);
            secondHourText.setLayoutX(24.0);
            secondHourText.setLayoutY(43.0);
            secondHourText.setStrokeType(StrokeType.OUTSIDE);
            secondHourText.setStrokeWidth(0.0);
            secondHourText.setStyle("-fx-font-weight:bold;\n-fx-font-size: 30;");
            secondHourText.setText(nameText);

            secondHour.getChildren().addAll(secondWeatherIcon, secondTemp, secondHourText);

            anchorpaneHours.getChildren().add(secondHour);
        }
    }


}
