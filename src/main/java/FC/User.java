package FC;

import java.util.ArrayList;
import java.util.Objects;

public class User {

    Argon2 argon2 = new Argon2();

    private boolean bStatus;
    private int iStatus = 0;
    private String email;
    private String password;

    ArrayList<User> users = new ArrayList<>();

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean register(String email, String password) {

        this.email = email;
        this.password = password;

        for (User user : users) {
            if (Objects.equals(email, user.getEmail())) {
                bStatus = true;
                break;
            } else {
                bStatus = false;
            }
        }
        return bStatus;
    }

    public int login(String email, String password) {
        this.email = email;
        this.password = password;

        for (User user : users) {

            if (Objects.equals(email, user.getEmail()) && argon2.verifyPassword(password, user.getPassword())) {
                iStatus = 1;
                break;
            }
            if (Objects.equals(email, user.getEmail()) && !argon2.verifyPassword(password, user.getPassword())) {
                iStatus = -2;
                break;
            }
            if (!Objects.equals(email, user.getEmail())) {
                iStatus = -1;
            }
        }
        return iStatus;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
