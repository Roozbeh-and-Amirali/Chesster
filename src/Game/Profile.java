
package Game;

import BasicClasses.Rating;

public class Profile {

    private String userName, password;
    private String name;
    private String imageAddress;
    private Rating rating;

    public Profile() {
        this.rating = new Rating( Rating.DEFAULT_RATING );
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

    private void setRating(long rating) {
        this.rating.setValue( rating );
    }
}
