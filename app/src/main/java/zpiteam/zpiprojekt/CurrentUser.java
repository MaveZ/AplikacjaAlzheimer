package zpiteam.zpiprojekt;

/**
 * Created by Maciej on 2015-06-13.
 */
public class CurrentUser {

    private static final CurrentUser currentUser = new CurrentUser();

    private int id;
    private String login;

    public CurrentUser () {}

    public static CurrentUser getInstance () {
        return currentUser;
    }

    public void setID (int id) {this.id = id;}
    public int getID() {return this.id;}

    public void setLogin(String login) {this.login = login;}
    public String getLogin() {return this.login;}



}
