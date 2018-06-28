package NetworkShit.ServerSide.Handlers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ChatHandler implements Runnable {
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    private Socket chatSocket;

    public ChatHandler(Socket chatSocket){
        this.chatSocket=chatSocket;
        try {
            ois=new ObjectInputStream(chatSocket.getInputStream());
            oos=new ObjectOutputStream(chatSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void run() {

    }
}