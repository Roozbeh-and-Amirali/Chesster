package UIUX.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class NewChallengePageController extends ParentController implements Initializable{
    @FXML
    RadioButton ratedRadio;
    @FXML
    RadioButton unratedRadio;

    public  void goBack(){
        loadPage("ChallengesPage");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ToggleGroup toggleGroup=new ToggleGroup();
        ratedRadio.setToggleGroup(toggleGroup);
        unratedRadio.setToggleGroup(toggleGroup);
        unratedRadio.setSelected(true);

    }
}
