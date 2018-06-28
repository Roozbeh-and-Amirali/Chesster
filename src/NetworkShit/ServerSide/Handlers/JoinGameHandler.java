package NetworkShit.ServerSide.Handlers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class JoinGameHandler implements Runnable {



    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    private Socket joinGameSocket;

    public JoinGameHandler(Socket joinGameSocket){
        this.joinGameSocket=joinGameSocket;
        try {
            ois=new ObjectInputStream(joinGameSocket.getInputStream());
            oos=new ObjectOutputStream(joinGameSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }














    @Override
    public void run() {

    }
}
