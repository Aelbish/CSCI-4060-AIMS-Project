package csci4060.project.aimsmobileapp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private double longitude;

    @NonNull
    private double latitude;

    private String text_box;
    private int radio;

    public User(int id, @NonNull double longitude, @NonNull double latitude, String text_box, int radio)
    {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.text_box = text_box;
        this.radio = radio;
    }

    public int getId() {
        return id;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getText_box() {
        return text_box;
    }

    public int getRadio() {
        return radio;
    }
}
