import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
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

public class HourlyController implements Initializable {
    // @FXML means that the fields are used by the .fxml documents
    // IMPORTANT: fields used by fxml CANNOT be marked static

    @FXML
    private Text hourlyTitle;
    @FXML
    private ScrollPane scrollpaneHours;
    @FXML
    private AnchorPane anchorpaneHours;
    @FXML
    private AnchorPane firstHour;

    @FXML
    private Button settingsButton;
    @FXML
    private AnchorPane header;

    // toggle between pages
    @FXML
    public void toHome(ActionEvent event){
        Main.controller.toHome(event);
    }
    @FXML
    public void toSettings(ActionEvent event){
        Main.controller.toSettings(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //initialise settings button
        try {
            ImageView settingsIcon = new ImageView(new Image(new FileInputStream("src/data/gear.png")));
            settingsIcon.setPreserveRatio(true);
            settingsIcon.setFitHeight(25.0);
            settingsButton.setGraphic(settingsIcon);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("src/data/gear.png not found");
        }

        // initialise title of page
        hourlyTitle.setText(Main.day);

        // get lists of hourly data
        List<String> hours = Hourly.getHours();
        List<Integer> temperatures = Hourly.getTemperatures();
        List<File> weatherIcons = Hourly.getWeatherIcons();

        // put the data into a table on the hourly page
        for (int i=0; i<hours.size(); i++) {
            String tempText = temperatures.get(i) + "°C";
            String hourText = hours.get(i);
            String iconPath = weatherIcons.get(i).getPath();
            int hour = Integer.parseInt(hourText.substring(0, 2));
            if (hour > 22 || hour < 5){
                iconPath = "src/data/hourlyicons/cloudy.png";
            }

            AnchorPane secondHour = new AnchorPane();
            secondHour.setLayoutX(2.0);
            secondHour.setLayoutY(i*58.0);
            secondHour.setPrefHeight(58.0);
            secondHour.setPrefWidth(293.0);
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
            secondHourText.setText(hourText);

            secondHour.getChildren().addAll(secondWeatherIcon, secondTemp, secondHourText);

            anchorpaneHours.getChildren().add(secondHour);
        }

    }
}
