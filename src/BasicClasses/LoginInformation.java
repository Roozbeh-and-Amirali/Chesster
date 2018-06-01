
package BasicClasses;

public class LoginInformation {

    private String username, password;

    public LoginInformation(String username, String password) {
        this.setUsername( username );
        this.setPassword( password );
    }

    @Override
    public String toString() {
        return username + " " + password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
