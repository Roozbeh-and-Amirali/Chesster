package UIUX.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class ParentController {

    public void loadPage( String address ) {

        Parent root = null;
        try {
            root = FXMLLoader.load( getClass().getResource( "/UIUX/FXMLs/" + address + ".fxml" ) );
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene( root );

    }

}
