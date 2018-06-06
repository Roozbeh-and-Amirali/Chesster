package NetworkShit.ClientSide;

import Game.Profile;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class Client extends Application {

//	Stage-e Asli-e Barnaame!
	public static Stage pStage;

	private static Profile profile;

	public static Socket socket;
	public static ObjectInputStream ois;
	public static ObjectOutputStream oos;

	public static void main(String[] args) {
		try {
			Client.socket = new Socket( "localhost", 1958 );

			Client.ois = new ObjectInputStream( Client.socket.getInputStream() );
			Client.oos = new ObjectOutputStream( Client.socket.getOutputStream() );
			System.out.println( "Created ois and oos!" );
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println( "Launching..." );
		launch( args );
	}

	@Override
	public void start(Stage primaryStage) { // Avvalesh, MainMenu ro load mikonim!
		Client.pStage = primaryStage;
		Parent root = null;
		try {
			root = FXMLLoader.load( getClass().getResource( "/UIUX/FXMLs/FirstPage.fxml" ) );
		} catch (IOException e) {
			e.printStackTrace();
		}
		Client.pStage.setTitle( "Main Menu" );
		Client.pStage.setScene( new Scene( root, 1280, 720 ) );
		Client.pStage.show();
	}

	public static Profile getProfile() {
		return profile;
	}

	public static void setProfile(Profile profile) {
		Client.profile = profile;
	}

}
