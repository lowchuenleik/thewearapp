import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;

import java.io.FileNotFoundException;

public class SettingsController{
    // @FXML means that the fields are used by the .fxml documents
    // IMPORTANT: fields used by fxml CANNOT be marked static

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
    public void save() {
        Settings.setJacket((int)slider_jacket.getValue());
        Settings.setRain((int)slider_rain.getValue());
        Settings.setClothes(cb_1.isSelected(), cb_2.isSelected(), cb_3.isSelected(),cb_4.isSelected(),cb_5.isSelected());
    }

    @FXML
    protected void toHome(ActionEvent event) throws FileNotFoundException {
        save();
        Main.controller.toHome(event);
        Main.controller.refresh();
    }
}
