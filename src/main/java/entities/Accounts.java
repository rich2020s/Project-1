package entities;

public class Accounts {
    // id: serial, primary key
    // username: unique, not null
    // password: not null
    // user_type: VARCHAR(1) E for employee or M for manager
    private int id;
    private String username;
    private String password;
    private String user_type;



    public Accounts() {

    }

    public Accounts(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Accounts(int id, String username, String password, String user_type) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.user_type = user_type;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    @Override
    public String toString() {
        return "Accounts{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", user_type='" + user_type + '\'' +
                '}';
    }
}
