package UIUX.Controllers;

import ClientAndHandlerCommunication.Commands.NewChallengeCommands.GetChallengesCommand;
import ClientAndHandlerCommunication.Responses.NewChallengeResponse.GetChallengesResponse;
import Game.Match;
import NetworkShit.ClientSide.ChallengesReceiver;
import NetworkShit.ClientSide.Client;
import NetworkShit.ServerSide.Server;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ChallengesPageController extends ParentController implements Initializable {

	@FXML
	 VBox challengesVBox;


	private List<Match> challenges=new ArrayList<>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
/*		Service<Void> service = new Service<Void>() {
			@Override
			protected Task<Void> createTask() {
				return new Task<Void>() {
					@Override
					protected Void call() {
						final CountDownLatch latch = new CountDownLatch(1);
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								try {
									while ( true ) {
										//Thread.sleep( 10000 );
										Client.oos.writeObject(new GetChallengesCommand());
										GetChallengesResponse response = (GetChallengesResponse) Client.ois.readObject();
										ChallengesPageController.this.createHbox(response.getChallenges());
									}
								} catch (IOException|ClassNotFoundException ioexception) {
									System.exit( 3 );
								} finally {
									latch.countDown();
								}
							}
						});
						try {
							latch.await();
						} catch (InterruptedException e) {
							System.exit( 4 );
						}
						return null;
					}
				};
			}
		};
		service.start();*/
/*		new Thread(new Runnable() {
			@Override
			public void run() {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						while ( true ) {
							try {
								Thread.sleep(500);
                                System.out.println( "Getttting challenges lists!" );
								Client.oos.writeObject(new GetChallengesCommand());
								GetChallengesResponse response = (GetChallengesResponse) Client.ois.readObject();
                                System.out.println( response.getChallenges() );
								createHbox(response.getChallenges());
                                System.out.println( "HBox meghdRdehi shod" );
							} catch ( InterruptedException|IOException|ClassNotFoundException e ) {
								System.exit( 5 );
							}
						}
					}
				});
			}
		}).start();*/
/*        Task task = new Task < Void > () {
            @Override
            protected Void call() {
                Platform.runLater( () -> {
                    while ( true ) {
                        try {
                            //Thread.sleep(500);
                            Client.oos.writeObject(new GetChallengesCommand());
                            GetChallengesResponse response = (GetChallengesResponse) Client.ois.readObject();
                            createHbox(response.getChallenges());
                        } catch (IOException|ClassNotFoundException e ){
                            System.exit( 5 );
                        }
                    }
                });
                return null;
            }
        };
        new Thread( task ).start();*/
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                //while ( true ) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Client.oos.writeObject(new GetChallengesCommand());
                                GetChallengesResponse response = (GetChallengesResponse) Client.ois.readObject();
                                createHbox(response.getChallenges());
                            } catch ( Exception e ) {
                                System.exit( 5 );
                            }
                        }
                    });
                //}
            }
        }, 1, 500, TimeUnit.MILLISECONDS );
        //System.out.println( "Thread started!" );
	}

	public void createNewChallenge(){
		this.loadPage("NewChallengePage");

	}

	public void backToMenu(){
		this.loadPage("MainMenu");
	}

	public  void createHbox(List<Match> challenges){

	    challengesVBox.getChildren().clear();

		for (Match match: challenges) {
			HBox matchBox=match.getMatchTile();
			String hostName=match.getHostProfile().getUserName();
			matchBox.setStyle("-fx-border-style: solid inside ;" + "-fx-border-width: 2px;"+"-fx-border-color: black;");
			matchBox.setOnMouseEntered((event1)-> {
				matchBox.setEffect(new DropShadow());
			});
			matchBox.setOnMouseExited((event2)-> {
				matchBox.setEffect(null);
			});
			matchBox.setOnMouseClicked((event3) -> {
				joinMatchAlert(hostName);

			});
			 challengesVBox.getChildren().add(matchBox);
		}
	}

	public  void joinMatchAlert(String hostName){

		Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Join Match");
		alert.setHeaderText("Do you Want to join "+hostName+"'s challenge?");
		alert.setContentText("Join as...");

		ButtonType asAudienceButton=new ButtonType("Audience");
		ButtonType asContestandButton=new ButtonType("Contestant");
		ButtonType cancel=new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(asAudienceButton,asContestandButton);
		Optional<ButtonType> as=alert.showAndWait();
		if (as.get().equals(asAudienceButton)){
			System.out.println("asAudience");
		}
		else if (as.get().equals(asContestandButton)){

		}
		else {

		}

	}




	public void setChallenges(List<Match> challenges) {
		this.challenges = challenges;
	}
}
