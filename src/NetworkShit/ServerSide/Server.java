
package NetworkShit.ServerSide;

import BasicClasses.LoginInformation;
import Game.Profile;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server {

//	Is server running?
	private static boolean isServerUp = true;

	public static List<Profile> profiles = Collections.synchronizedList( new ArrayList<Profile>() );

	public static void main(String[] args) {

		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket( 1958 );
		} catch (IOException e) {
			e.printStackTrace();
		}

		while ( Server.isIsServerUp() ){
			Socket currentSocket = null;
			try {
				currentSocket = serverSocket.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static boolean isLoginInformationValid( LoginInformation loginInformation ) {
		for ( Profile currentProfile : Server.profiles ) {
			if (currentProfile.getUserName().equals(loginInformation.getUsername()))
				if (currentProfile.getPassword().equals(loginInformation.getPassword()))
					return true;
		}
		return false;
	}

//	Getters and Setters

	public static boolean isIsServerUp() {
		return isServerUp;
	}

	public static void setIsServerUp(boolean isServerUp) {
		Server.isServerUp = isServerUp;
	}

}
