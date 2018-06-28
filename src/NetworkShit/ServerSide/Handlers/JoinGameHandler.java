package NetworkShit.ServerSide.Handlers;

import ClientAndHandlerCommunication.Commands.Command;
import ClientAndHandlerCommunication.Commands.JoinGameCommand;
import ClientAndHandlerCommunication.Responses.JoinedGameResponse;
import ClientAndHandlerCommunication.Responses.Response;
import Game.Profile;
import NetworkShit.ServerSide.Server;

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
            oos=new ObjectOutputStream(joinGameSocket.getOutputStream());
            ois=new ObjectInputStream(joinGameSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    @Override
    public void run() {

        while (true){

            Command clientCommand = this.getCommandFromClient();
            Response response = this.produceResponse(clientCommand);
            this.sendResponseToClient(response);

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

    private void sendResponseToClient( Response response) {
        try {
            JoinedGameResponse joinedGameResponse=(JoinedGameResponse) response;
            //System.out.println(joinedGameResponse.getMatch().getHostProfile().getUserName());
            //System.out.println(Server.userHandlers.get(joinedGameResponse.getMatch().getHostProfile()));
            JoinGameHandler targetHandler=Server.joinGameHandlers.get(Server.userHandlers.get(joinedGameResponse.getMatch().getHostProfile()));
            targetHandler.oos.writeObject(joinedGameResponse);
        } catch (IOException e) {
                e.printStackTrace();
        }
    }

    private Response produceResponse( Command command ) {
        Response returnValue = null;
        if (command instanceof JoinGameCommand){
            JoinGameCommand thiscommand=(JoinGameCommand) command;
            returnValue=new JoinedGameResponse(thiscommand.getMatch());
        }
        return returnValue;
    }


}
