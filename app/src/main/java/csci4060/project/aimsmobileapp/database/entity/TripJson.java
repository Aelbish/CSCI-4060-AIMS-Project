package csci4060.project.aimsmobileapp.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TripJson {

    @PrimaryKey
    int id;
    String data;

    public TripJson(int id, String data) {
        this.id = id;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
