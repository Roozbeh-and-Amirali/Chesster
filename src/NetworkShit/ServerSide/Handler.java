
package NetworkShit.ServerSide;

import BasicClasses.LoginInformation;
import ClientAndHandlerCommunication.Commands.Command;
import ClientAndHandlerCommunication.Commands.FirstPageCommands.CheckLoginValidnessCommand;
import ClientAndHandlerCommunication.Commands.FirstPageCommands.CreateProfileCommand;
import ClientAndHandlerCommunication.Commands.FirstPageCommands.GetProfileCommand;
import ClientAndHandlerCommunication.Commands.FirstPageCommands.SetProfileCommand;
import ClientAndHandlerCommunication.Commands.ParentCommands.UsernameExistenceCommand;
import ClientAndHandlerCommunication.Responses.FirstPageResponses.GetProfileResponse;
import ClientAndHandlerCommunication.Responses.FirstPageResponses.LoginIsValidResponse;
import ClientAndHandlerCommunication.Responses.FirstPageResponses.ProfileCreationResponse;
import ClientAndHandlerCommunication.Responses.ParentResponds.UsernameExistenceRespond;
import ClientAndHandlerCommunication.Responses.Response;
import Enums.GameState;
import Game.Profile;

import java.io.*;
import java.net.Socket;
import java.util.Map;

//Client Handler
public class Handler implements Runnable {

	private GameState gameState; //current state of game!
	private Socket socket;	//Socket that refers to client-e marboote

	private Profile profile;	//Profile-e marboot be Client-e Marboote :))

//	iosHaa-e Mored-e Niaaz
	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	public Handler( Socket socket ) {
	    this.setSocket( socket );	//Socket ro set mikonim!
	    gameState = GameState.MAIN_MENU; //Avvalesh too-e MainMenuEm!
		try {
			this.oos = new ObjectOutputStream( this.socket.getOutputStream() );
			this.ois = new ObjectInputStream( this.socket.getInputStream() );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    @Override
    public void run() {
		while ( true ) {
			Command clientCommand = this.getCommandFromClient();
			Response response = this.produceResponse( clientCommand );
			this.sendResponseToClient( response );
		}
    }

    private Command getCommandFromClient() {
		Command returnValue = null;
		try {
			returnValue = (Command) this.ois.readObject();
		} catch (IOException|ClassNotFoundException e) {
			e.printStackTrace();
		}
		return returnValue;
	}

	private void sendResponseToClient( Response response ) {
		try {
			this.oos.writeObject( response );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Response produceResponse( Command command ) {
		Response returnValue = null;
		if ( command instanceof CheckLoginValidnessCommand ){
			LoginInformation temp = ((CheckLoginValidnessCommand) command).getLoginInformation();
			boolean answer = this.isLoginValid( temp );
			returnValue = new LoginIsValidResponse( answer );
//			return ( new LoginIsValidResponse( answer ) );
		}
		else if ( command instanceof CreateProfileCommand ) {
			Profile newProfile = ((CreateProfileCommand) command).getProfile();
			returnValue = this.addProfile( newProfile );
		}
		else if ( command instanceof GetProfileCommand ){
		    Profile profile = Server.profiles.get( ((GetProfileCommand) command).getUserName() );
		    returnValue = new GetProfileResponse( profile );
		}
		else if ( command instanceof SetProfileCommand ){
			this.setProfile( ((SetProfileCommand) command).getProfile() );
		}
		else if ( command instanceof UsernameExistenceCommand){
			if ( Server.profiles.get( ((UsernameExistenceCommand) command).getUsername() ) == null )
				returnValue = new UsernameExistenceRespond( false );
			else returnValue = new UsernameExistenceRespond( true );
		}
		return returnValue;
	}

	private ProfileCreationResponse addProfile( Profile profile ) {
		ProfileCreationResponse returnValue;
		if ( this.isLoginValid( new LoginInformation( profile.getUserName(), profile.getPassword() ) ) )
			returnValue = new ProfileCreationResponse( false, "Username already exists" );
		else if ( this.profileExists( profile ) )
			returnValue = new ProfileCreationResponse( false, "Try another username and password" );
		else {
			returnValue = new ProfileCreationResponse(true, "User created successfully");
			Server.profiles.put( profile.getUserName(), profile );
			for (Map.Entry<String,Profile> iterator : Server.profiles.entrySet() )
				System.out.print ( iterator.getKey() + " " + iterator.getValue()+ "     " );
			System.out.println();
		}
		return returnValue;
	}

	private boolean profileExists( Profile profile ) {
		if ( this.isLoginValid( new LoginInformation( profile.getUserName(), profile.getPassword() ) ) )
			if ( Server.profiles.get( profile.getUserName() ).getPassword().equals( profile.getPassword() ) )
				return true;
		return false;

	}

	private boolean isLoginValid( LoginInformation loginInformation ) {
		return ( Server.profiles.get( loginInformation.getUsername() ) != null );
	}

    //	Getters and Setters

	private void setSocket( Socket socket ) {
		this.socket = socket;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

}
