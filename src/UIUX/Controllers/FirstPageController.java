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

//	VaghT taraf dokme-e login ro mizane, in taabe' sedaa zade mishe
	public void doLoginStuff() {
		if ( loginUsernameField.getText().isEmpty() || loginPasswordField.getText().isEmpty() ) { //Age yeki az fieldHaa-e mored-e niaaz khaali boodan
			this.showFillRequiredFieldsDialog();	//Be taraf befahmoon ke baayad poreshoon kone!
			return;
		}
//		ChizHaaE ro ke taraf vaared karde, dar ghaaleb-e ye LoginInformation negah midaarim
		LoginInformation loginInformation = new LoginInformation( loginUsernameField.getText(), loginPasswordField.getText() );
		if ( ! Server.isLoginInformationValid( loginInformation ) ){	//Age ettelaa'aati ke taraf vaared karde bood ghalat boodan
			this.showInvalidLoginDialog();	//Be taraf befahmoon ke chi shode!
			return;
		}
//		age be injaa-e taabe reside baashim ya'ni hamechiz ok boode... boro soraagh-e menu!
		this.loadPage( "MainMenu" );
	}

//	VaghT taraf dokme-e signup ro mizare, in taabe' sedaa zade mishe
	public void doSignupStuff() {
//		Age yeki az fieldHaa-e mored-e niaaz khaali boodan
		if ( signupUsernameField.getText().isEmpty()
				|| signupPasswordField.getText().isEmpty()
				|| signupConfirmPasswordField.getText().isEmpty()
				|| signupNameField.getText().isEmpty() ) {
			this.showFillRequiredFieldsDialog();	//Be taraf befahmoon ke baayad poreshoon kone!
			return;
		}
//		Age paswoordi ke vaared shode bood, ba comfirmationesh fargh daasht
		if ( ! signupPasswordField.getText().equals( signupConfirmPasswordField.getText() ) ) {
			this.showMismatchPasswordsDialog();	//Be taraf begoo ke paswoordHaash be ham nemikhoran!
			return;
		}
//		Khob, hamechi OK e... profili ke taraf khaaste besaaze ro besaaz va berizesh too-e justCreatedProfile
		Profile justCreatedProfile = this.makeProfileFromPageContent();
//		System.out.println( justCreatedProfile );
		Server.profiles.add( justCreatedProfile );	//ProfileE ke saakhT ro too-e Server add kon!
		this.showProfileCreatedDialog();	//Begoo ke profile ro saakhT baa movaffaghiat
		this.clearFields();	//FieldHaa ro paak kon... kaaresh tamoom shode Dge mikhaaymeshoon chikar?

/*		System.out.println();
		for ( Profile temp : Server.profiles )
			System.out.println( temp );
		System.out.println();*/

	}

//	FieldHaa-e marboot be ghesmat-e signup ro paak mikone
	private void clearFields() {
		this.signupUsernameField.setText( null );
		this.signupPasswordField.setText( null );
		this.signupConfirmPasswordField.setText( null );
		this.signupNameField.setText( null );
		Image temp = new Image( "/Assets/FirstPage/unknown-contact.png" );
		this.profilePicture.setImage( temp );
	}

//	Az chizHaaE ke tooye safhe hastan, ye profile misaaze!
	private Profile makeProfileFromPageContent() {
		Profile returnValue = new Profile();
		returnValue.setUserName( signupUsernameField.getText() );
		returnValue.setPassword( signupPasswordField.getText() );
		returnValue.setName( signupNameField.getText() );
		return returnValue;
	}

//	VaghT taraf roo-e profile picturesh click mikone, ye safheE baaz mishe ke komak mikone ye profile picture entekhaab kone!
	public void chooseProfilePicture() {
		FileChooser fileChooser = new FileChooser();
		File file = fileChooser.showOpenDialog( Client.pStage.getScene().getWindow() );
		if ( file != null ) {
			Image image = new Image( file.toURI().toString() );
			this.profilePicture.setImage( image );
		}
	}

//	Title va Matn-e badane ro migire, va ye alert baa oon mohtaviaat neshoon mide!
	public void makeAndShowInformationDialog( String title, String contentText ) {
		Alert alert = new Alert( Alert.AlertType.INFORMATION );
		alert.setTitle( title );
		alert.setHeaderText( null );
		alert.setContentText( contentText );
		alert.showAndWait();
	}

//	AlertHaa-e mokhtalef ro injaa misaazim... bad baa komak-e makeAndShowInformationDialog neshooneshoon midim!

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
