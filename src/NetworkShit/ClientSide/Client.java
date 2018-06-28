package NetworkShit.ClientSide;

import Enums.Ports;
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

	public static Socket userSocket;
	public static ObjectInputStream userIn;
	public static ObjectOutputStream userOut;

	public static Socket chatSocket;
	public static ObjectInputStream chatIn;
	public static ObjectOutputStream chatOut;

	public static Socket joinGameSocket;
	public static ObjectInputStream joinGameIn;
	public static ObjectOutputStream joinGameOut;

	public static Socket gameSocket;
	public static ObjectInputStream gameIn;
	public static ObjectOutputStream gameOut;



	public static void main(String[] args) {
		try {
			Client.userSocket = new Socket( "localhost", Ports.USER_PORT);
			Client.userIn = new ObjectInputStream( Client.userSocket.getInputStream() );
			Client.userOut = new ObjectOutputStream( Client.userSocket.getOutputStream() );

			Client.chatSocket=new Socket("localhost",Ports.CHAT_PORT);
			Client.chatIn=new ObjectInputStream(Client.chatSocket.getInputStream());
			Client.chatOut=new ObjectOutputStream(Client.chatSocket.getOutputStream());

			Client.joinGameSocket=new Socket("localhost",Ports.JOINGAME_PORT);
			Client.joinGameIn=new ObjectInputStream(Client.joinGameSocket.getInputStream());
			Client.joinGameOut=new ObjectOutputStream(Client.joinGameSocket.getOutputStream());

			Client.gameSocket=new Socket("localhost",Ports.GAME_PORT);
			Client.gameIn=new ObjectInputStream(Client.gameSocket.getInputStream());
			Client.gameOut=new ObjectOutputStream(Client.gameSocket.getOutputStream());


			System.out.println( "Created userIn and userOut!" );
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
