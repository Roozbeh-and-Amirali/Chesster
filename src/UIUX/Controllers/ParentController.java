package UIUX.Controllers;

import ClientAndHandlerCommunication.Commands.Command;
import ClientAndHandlerCommunication.Commands.ParentCommands.UsernameExistenceCommand;
import ClientAndHandlerCommunication.Responses.ParentResponds.UsernameExistenceRespond;
import ClientAndHandlerCommunication.Responses.Response;
import NetworkShit.ClientSide.Client;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

import java.io.IOException;
import java.util.Optional;

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

//	Title va Matn-e badane ro migire, va ye alert baa oon mohtaviaat neshoon mide!
	public void makeAndShowInformationDialog( String title, String contentText ) {
		Alert alert = new Alert( Alert.AlertType.INFORMATION );
		alert.setTitle( title );
		alert.setHeaderText( null );
		alert.setContentText( contentText );
		alert.showAndWait();
	}

//	Title va MatnHaa-e badane ro migire, baahaashoon ye TextInputDialog misaaze!
	public String makeAndShowTextInputDialog( String title, String headerText, String contentText ) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle( title );
		dialog.setHeaderText( headerText );
		dialog.setContentText( contentText );

		Optional<String> result = dialog.showAndWait();
		return ( result.isPresent() ? result.get() : null );
	}

}
