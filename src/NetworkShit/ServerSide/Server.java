
package NetworkShit.ServerSide;

import Game.Match;
import Game.Profile;
import NetworkShit.ServerSide.Handlers.UserHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
Server :))
 */

public class Server {

//	Is server running?
	private static boolean isServerUp = true;

//	ProfileHaaE ke ta alaan saakhte shodan ro injaa zakhire mikonim!
//	public static List<Profile> profiles = Collections.synchronizedList( new ArrayList<Profile>() );
	public static Map<String, Profile> profiles = new ConcurrentHashMap<String, Profile>();
// challenge haaE ke ta alan saakhte shodan
	public static List<Match> challenges=new ArrayList<>();
//	Ye map dREm az profileHaa be HandlerHaa
	public static Map<Profile, UserHandler> chandlers = new ConcurrentHashMap<Profile, UserHandler>();

	public static void main(String[] args) {

		ServerSocket userSocket = null;
		ServerSocket chatSocket=null;
		ServerSocket joinSocket=null;
		ServerSocket gameSocket=null;
		try {
			userSocket = new ServerSocket( 1958 );
		} catch (IOException e) {
			e.printStackTrace();
		}

		while ( Server.isIsServerUp() ){
			Socket currentuserSocket = null;
			try {
				System.out.println( "Waiting for a client..." );
				currentuserSocket = userSocket.accept();
				System.out.println( "Found a Client!" );

				UserHandler temp = new UserHandler( currentuserSocket );
				Thread handlerThread = new Thread( temp );
				handlerThread.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

/*

//	Behesh ye chiZ az jens-e LoginInformation pass midim, behemoon mige ke aayaa useri baa in moshakhasaat vojood daare yaa na!
	public static boolean isLoginInformationValid( LoginInformation loginInformation ) {
		for ( Profile currentProfile : Server.profiles ) {
			if (currentProfile.getUserName().equals(loginInformation.getUsername()))
				if (currentProfile.getPassword().equals(loginInformation.getPassword()))
					return true;
		}
		return false;
	}
*/

//	Getters and Setters

	public static boolean isIsServerUp() {
		return isServerUp;
	}

	public static void setIsServerUp(boolean isServerUp) {
		Server.isServerUp = isServerUp;
	}

}
