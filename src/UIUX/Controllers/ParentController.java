package UIUX.Controllers;

import NetworkShit.ClientSide.Client;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

/*
All Controllers extend this class
 */
public class ParentController {

//	Baraa-e Load Kardan-e Safahaat-e Digar
	public void loadPage( String address ) {

		Parent root = null;
		try {
			root = FXMLLoader.load( getClass().getResource( "/UIUX/FXMLs/" + address + ".fxml" ) );
		} catch (IOException e) {
			e.printStackTrace();
		}

		Scene scene = new Scene( root );
		Client.pStage.setScene( scene );
		Client.pStage.show();

	}

}
