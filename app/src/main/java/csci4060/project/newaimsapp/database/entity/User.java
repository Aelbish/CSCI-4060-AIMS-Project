package csci4060.project.newaimsapp.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey
    int driver_id;
    String full_name;

    public User(int driver_id, String full_name)
    {
        this.driver_id = driver_id;
        this.full_name = full_name;
    }

    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }
}
