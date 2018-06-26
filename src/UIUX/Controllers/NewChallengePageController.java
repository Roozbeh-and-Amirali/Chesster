package UIUX.Controllers;

import BasicClasses.Time;
import ClientAndHandlerCommunication.Commands.FirstPageCommands.GetProfileCommand;
import ClientAndHandlerCommunication.Commands.NewChallengeCommands.CreateMatchCommand;
import ClientAndHandlerCommunication.Responses.FirstPageResponses.GetProfileResponse;
import Exceptions.IllegalTimeInput;
import Game.ClockNiggas.Clock;
import Game.ClockNiggas.Clockability;
import Game.ClockNiggas.Clocked;
import Game.ClockNiggas.UnClocked;
import Game.Match;
import Game.RateNiggas.Ratability;
import Game.RateNiggas.Rated;
import Game.RateNiggas.UnRated;
import NetworkShit.ClientSide.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class NewChallengePageController extends ParentController implements Initializable {
    @FXML
    RadioButton ratedRadio;
    @FXML
    RadioButton unratedRadio;
    @FXML
    RadioButton clockedRadio;
    @FXML
    RadioButton unClockedRadio;
    @FXML
    HBox clockBox;
    @FXML
    TextField minutes;
    @FXML
    TextField seconds;
    @FXML
    Label clockWarning;


    public void goBack() {
        loadPage("ChallengesPage");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ToggleGroup ratingRadios = new ToggleGroup();
        ratedRadio.setToggleGroup(ratingRadios);
        unratedRadio.setToggleGroup(ratingRadios);
        unratedRadio.setSelected(true);

        ToggleGroup clockRadio = new ToggleGroup();
        clockedRadio.setToggleGroup(clockRadio);
        unClockedRadio.setToggleGroup(clockRadio);
        clockedRadio.setSelected(true);


    }

    public void hideClock() {
        clockBox.setVisible(false);
    }

    public void showClock() {
        clockBox.setVisible(true);
    }

    public void createChallenge() {

        try {
            Time temptime = getTimeInput();

            GetProfileResponse profileGET= (GetProfileResponse) this.sendCommand(new GetProfileCommand(Client.getProfile().getUserName()));

            Match match=new Match(profileGET.getProfile());
            Clock matchclock = new Clock(temptime);
            Clockability gameClockability;
            Ratability gameratabillity;


            if (unratedRadio.isSelected())
                gameratabillity = new UnRated();
            else
                gameratabillity = new Rated();

            if (clockedRadio.isSelected())
                gameClockability=new Clocked();
            else
                gameClockability=new UnClocked();


            match.setClock(matchclock);
            match.setClockability(gameClockability);
            match.setRatability(gameratabillity);


            this.sendCommand(new CreateMatchCommand(match));

        } catch (Exception e) {
           // e.printStackTrace();
            clockWarning.setText(new IllegalTimeInput().toString());
        }
        loadPage("ChallengesPage");


    }

    public void disableClock(){
        unClockedRadio.setDisable(true);
        clockedRadio.setSelected(true);
    }

    public void enableClock(){
        unClockedRadio.setDisable(false);
    }

    private Time getTimeInput() throws Exception {
        int tempSeconds = 0;
        int tempMinutes = 0;
        tempSeconds = Integer.parseInt(seconds.getText());
        tempMinutes = Integer.parseInt(minutes.getText());
        if (ratedRadio.isSelected() && tempMinutes==0 && tempSeconds==0)
            throw new IllegalTimeInput();
        if (tempMinutes > 59 || tempMinutes < 0 || tempSeconds > 59 || tempSeconds < 0) {
            throw new IllegalTimeInput();
        }
        tempSeconds += tempMinutes * 60;
        return new Time(tempSeconds);


    }


}
