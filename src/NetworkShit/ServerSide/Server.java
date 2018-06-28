
package NetworkShit.ServerSide;

import Enums.Ports;
import Game.Match;
import Game.Profile;
import NetworkShit.ServerSide.Handlers.ChatHandler;
import NetworkShit.ServerSide.Handlers.GameHandler;
import NetworkShit.ServerSide.Handlers.JoinGameHandler;
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
			userSocket = new ServerSocket(Ports.USER_PORT );
			chatSocket=new ServerSocket(Ports.CHAT_PORT);
			joinSocket=new ServerSocket(Ports.JOINGAME_PORT);
			gameSocket=new ServerSocket(Ports.GAME_PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}

		while ( Server.isIsServerUp() ){
			Socket currentuserSocket = null;
			Socket currentChatSocket=null;
			Socket currentJoinSocket=null;
			Socket currentGameSocket=null;
			try {
				System.out.println( "Waiting for a client..." );
				currentuserSocket = userSocket.accept();
				System.out.println( "waiting for the clients chatsocket" );
				currentChatSocket=chatSocket.accept();
				System.out.println("waiting for the clients joinsocket");
				currentJoinSocket=joinSocket.accept();
				System.out.println("waiting for the clients gamesocket");
				currentGameSocket=gameSocket.accept();
				System.out.println("got all the sockets nigga");

				UserHandler userHandler = new UserHandler( currentuserSocket );
				ChatHandler chatHandler=new ChatHandler(currentChatSocket);
				JoinGameHandler joinGameHandler=new JoinGameHandler(currentJoinSocket);
				GameHandler gameHandler=new GameHandler(currentGameSocket);

				new Thread( userHandler ).start();
				new Thread(chatHandler).start();
				new Thread(joinGameHandler).start();
				new Thread(gameHandler).start();

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
