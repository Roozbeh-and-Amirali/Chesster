package UIUX.Controllers;

import NetworkShit.ClientSide.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/*
Marboot be menu-e asli-e har kaarbar
 */

public class MainMenuController extends ParentController {

    @FXML
    Button profileButton;

    public void showProfile() {
        this.loadPage( "ProfilePage" );
    }

}
