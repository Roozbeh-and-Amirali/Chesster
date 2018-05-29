
package NetworkShit.ServerSide;

import NetworkShit.SharedStuff.GameState;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Handler implements Runnable {

	private GameState gameState;
	private Socket socket;

	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	public Handler( Socket socket ) {
	    this.setSocket( socket );
	    gameState = GameState.MAIN_MENU;
		try {
			this.ois = new ObjectInputStream( this.socket.getInputStream() );
			this.oos = new ObjectOutputStream( this.socket.getOutputStream() );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setSocket( Socket socket ) {
		this.socket = socket;
	}

    @Override
    public void run() {

    }
}
