package UIUX.Controllers;

import BasicClasses.LoginInformation;
import Game.Profile;
import NetworkShit.ClientSide.Client;
import NetworkShit.ServerSide.Server;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;

public class FirstPageController extends ParentController{

	@FXML
	TextField loginUsernameField;
	@FXML
	PasswordField loginPasswordField;
	@FXML
	TextField signupUsernameField;
	@FXML
	PasswordField signupPasswordField;
	@FXML
	PasswordField signupConfirmPasswordField;
	@FXML
	ImageView profilePicture;
	@FXML
	TextField signupNameField;

	public void doLoginStuff() {
		if ( loginUsernameField.getText().isEmpty() || loginPasswordField.getText().isEmpty() ) {
			this.showFillRequiredFieldsDialog();
			return;
		}
		LoginInformation loginInformation = new LoginInformation( loginUsernameField.getText(), loginPasswordField.getText() );
//		System.out.println( "FIRST PAGE: " + loginInformation );
		if ( ! Server.isLoginInformationValid( loginInformation ) ){
			this.showInvalidLoginDialog();
			return;
		}
		this.loadPage( "MainMenu" );
	}

	public void doSignupStuff() {
		if ( signupUsernameField.getText().isEmpty() || signupPasswordField.getText().isEmpty() || signupConfirmPasswordField.getText().isEmpty() ) {
			this.showFillRequiredFieldsDialog();
			return;
		}
		if ( ! signupPasswordField.getText().equals( signupConfirmPasswordField.getText() ) ) {
			this.showMismatchPasswordsDialog();
			return;
		}
		Profile justCreatedProfile = this.makeProfileFromPageContent();
//		System.out.println( justCreatedProfile );
		Server.profiles.add( justCreatedProfile );
		this.showProfileCreatedDialog();
		this.clearFields();

		System.out.println();
		for ( Profile temp : Server.profiles )
			System.out.println( temp );
		System.out.println();

	}

	private void clearFields() {
		this.signupUsernameField.setText( null );
		this.signupPasswordField.setText( null );
		this.signupConfirmPasswordField.setText( null );
		this.signupNameField.setText( null );
		Image temp = new Image( "/Assets/FirstPage/unknown-contact.png" );
		this.profilePicture.setImage( temp );
	}

	private Profile makeProfileFromPageContent() {
		Profile returnValue = new Profile();
		returnValue.setUserName( signupUsernameField.getText() );
		returnValue.setPassword( signupPasswordField.getText() );
		returnValue.setName( signupNameField.getText() );
		return returnValue;
	}

	public void chooseProfilePicture() {
		FileChooser fileChooser = new FileChooser();
		File file = fileChooser.showOpenDialog( Client.pStage.getScene().getWindow() );
		if ( file != null ) {
			Image image = new Image( file.toURI().toString() );
			this.profilePicture.setImage( image );
		}
	}

	public void makeAndShowInformationDialog( String title, String contentText ) {
		Alert alert = new Alert( Alert.AlertType.INFORMATION );
		alert.setTitle( title );
		alert.setHeaderText( null );
		alert.setContentText( contentText );
		alert.showAndWait();
	}

	public void showProfileCreatedDialog(){
		String title = "Success";
		String contentText = "Profile Created Successfully, now you can login!";
		this.makeAndShowInformationDialog( title, contentText );
	}

	public void showFillRequiredFieldsDialog(){
		String title = "Incomplete information";
		String contentText = "Please fill all of the required fields";
		this.makeAndShowInformationDialog( title, contentText );
	}

	public void showInvalidLoginDialog() {
	    String title = "Error in login";
	    String contentText = "Can not find a user with this information\nTry again or sign up";
	    this.makeAndShowInformationDialog( title, contentText );
	}

	public void showMismatchPasswordsDialog() {
		String title = "Error in sign up";
		String contentText = "Passwords don't match";
		this.makeAndShowInformationDialog( title, contentText );
	}

}
