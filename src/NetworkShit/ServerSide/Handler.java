
package NetworkShit.ServerSide;

import BasicClasses.LoginInformation;
import ClientAndHandlerCommunication.Commands.Command;
import ClientAndHandlerCommunication.Commands.FirstPageCommands.CheckLoginValidnessCommand;
import ClientAndHandlerCommunication.Responses.FirstPageResponses.LoginIsValidResponse;
import ClientAndHandlerCommunication.Responses.Response;
import Enums.GameState;
import Game.Profile;

import java.io.*;
import java.net.Socket;

//Client Handler
public class Handler implements Runnable {

	private GameState gameState; //current state of game!
	private Socket socket;	//Socket that refers to client-e marboote

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
			System.out.println( "Client Command: " + clientCommand );
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
			return ( new LoginIsValidResponse( answer ) );
		}
		return returnValue;
	}

	private boolean isLoginValid( LoginInformation loginInformation ) {
		for ( Profile current : Server.profiles )
			if ( current.getUserName().equalsIgnoreCase( loginInformation.getUsername() ) )
				if ( current.getPassword().equals( loginInformation.getPassword() ) )
					return true;
		return false;
	}

    //	Getters and Setters

	private void setSocket( Socket socket ) {
		this.socket = socket;
	}

}
