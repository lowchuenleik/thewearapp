import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class DailyController implements Initializable {
    @FXML
    public void toHome(ActionEvent event){
        Main.controller.toHome(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
