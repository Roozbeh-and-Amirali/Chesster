package UIUX.Controllers;

import ClientAndHandlerCommunication.Commands.Command;
import ClientAndHandlerCommunication.Commands.ParentCommands.UsernameExistenceCommand;
import ClientAndHandlerCommunication.Responses.ParentResponds.UsernameExistenceRespond;
import ClientAndHandlerCommunication.Responses.Response;
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

	public Response sendCommand( Command command ) {
		try {
			Client.oos.writeObject( command );
		} catch (IOException e) {
			e.printStackTrace();
		}
		Response response = null;
		try {
			response = (Response) Client.ois.readObject();
		} catch (IOException|ClassNotFoundException e) {
			e.printStackTrace();
		}
		return response;
	}

	public boolean doesUsernameExist( String username ) {

		UsernameExistenceRespond respond = (UsernameExistenceRespond) this.sendCommand( new UsernameExistenceCommand( username ) );

		return respond.isAnswer();

	}

}
