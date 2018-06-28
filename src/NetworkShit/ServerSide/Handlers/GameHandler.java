package NetworkShit.ServerSide.Handlers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class GameHandler implements Runnable{

    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    private Socket gameSocket;

    public GameHandler(Socket gameSocket){
        this.gameSocket =gameSocket;
        try {

            oos=new ObjectOutputStream(gameSocket.getOutputStream());
            ois=new ObjectInputStream(gameSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }














    @Override
    public void run() {

    }
}
