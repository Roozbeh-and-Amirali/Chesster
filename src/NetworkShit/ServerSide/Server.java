
package NetworkShit.ServerSide;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

//	Is server running?
	private static boolean isServerUp = true;

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

//	Getters and Setters

	public static boolean isIsServerUp() {
		return isServerUp;
	}

	public static void setIsServerUp(boolean isServerUp) {
		Server.isServerUp = isServerUp;
	}

}
