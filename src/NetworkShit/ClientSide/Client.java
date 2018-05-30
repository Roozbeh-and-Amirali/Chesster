package NetworkShit.ClientSide;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Client extends Application {

//	Stage-e Asli-e Barnaame!
	public static Stage pStage;

	public static void main(String[] args) {
		launch( args );
	}

	@Override
	public void start(Stage primaryStage) { // Avvalesh, MainMenu ro load mikonim!
		Client.pStage = primaryStage;
		Parent root = null;
		try {
			root = FXMLLoader.load( getClass().getResource( "/UIUX/FXMLs/MainMenu.fxml" ) );
		} catch (IOException e) {
			e.printStackTrace();
		}
		Client.pStage.setTitle( "Main Menu" );
		Client.pStage.setScene( new Scene( root, 1280, 720 ) );
		Client.pStage.show();
	}
}
