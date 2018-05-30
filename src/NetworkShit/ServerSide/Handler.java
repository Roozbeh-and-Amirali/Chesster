
package NetworkShit.ServerSide;

import NetworkShit.SharedStuff.GameState;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
			this.ois = new ObjectInputStream( this.socket.getInputStream() );
			this.oos = new ObjectOutputStream( this.socket.getOutputStream() );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	Getters and Setters

	private void setSocket( Socket socket ) {
		this.socket = socket;
	}

    @Override
    public void run() {

    }
}
