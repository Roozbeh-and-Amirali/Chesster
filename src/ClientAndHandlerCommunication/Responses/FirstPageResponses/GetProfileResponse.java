package ClientAndHandlerCommunication.Responses.FirstPageResponses;

import ClientAndHandlerCommunication.Responses.Response;
import Game.Profile;

public class GetProfileResponse implements Response {

	private Profile profile;

    public GetProfileResponse(Profile profile) {
        this.profile = profile;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
