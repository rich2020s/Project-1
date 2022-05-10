package entities;


import java.sql.Timestamp;
import java.time.LocalDateTime;


public class Tickets {
    // id: serial primary key
    // date: TIMESTAMPTZ DEFAULT NOW()
    // user_id: foreign key from accounts table
    // price: DECIMAL (15,2)

    private int id;
    private Timestamp created_at;
    private int user_id;
    private double price;
    private String description;
    private String state;

    public Tickets() {

    }
    public Tickets(int user_id, double price, String description) {
        this.user_id = user_id;
        this.price = price;
        this.description = description;
    }

    public Tickets(int id, Timestamp created_at, int user_id, double price, String description, String state) {
        this.id = id;
        this.created_at = created_at;
        this.user_id = user_id;
        this.price = price;
        this.description = description;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp date) {
        this.created_at = created_at;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Tickets{" +
                "id=" + id +
                ", created_at=" + created_at +
                ", user_id=" + user_id +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
