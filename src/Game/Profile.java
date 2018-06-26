
package Game;

import BasicClasses.Rating;

import java.io.Serializable;

/*
Profile Dge... ettelaa'aat-e shakhs ro toosh daarim! ChizHaaE mesl-e
username o password o inaa...
 */

public class Profile implements Serializable {

    private String userName, password;
    private String name;
    private String imageAddress;
    private Rating rating;
    private int myChallenges; //number of challenges this user crated

    public Profile() {

        this.rating = new Rating( Rating.DEFAULT_RATING);
        this.myChallenges=0;

    }

    @Override
    public String toString() {
        return "[" + userName + ": " + name + " " + rating + "]";
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    public long getRating() {
        return rating.getValue();
    }

    public int getMyChallenges() {
        return myChallenges;
    }

    public void setMyChallenges(int myChallenges) {
        this.myChallenges = myChallenges;
    }

    private void setRating(long rating) {
        this.rating.setValue( rating );
    }


}
