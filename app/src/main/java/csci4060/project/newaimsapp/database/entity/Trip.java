package csci4060.project.newaimsapp.database.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity//(foreignKeys = {@ForeignKey(entity = Driver.class, parentColumns = "driver_code", childColumns = "driver_code")})
public class Trip {
    //Attributes for this table
    @PrimaryKey
    int trip_id;
    String trip_name;
    String trip_date;

    //Foreign Key
    String driver_code;

    public Trip(int trip_id, String trip_name, String trip_date, String driver_code) {
        this.trip_id = trip_id;
        this.trip_name = trip_name;
        this.trip_date = trip_date;
        this.driver_code = driver_code;
    }


    public int getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }

    public String getTrip_name() {
        return trip_name;
    }

    public void setTrip_name(String trip_name) {
        this.trip_name = trip_name;
    }

    public String getTrip_date() {
        return trip_date;
    }

    public void setTrip_date(String trip_date) {
        this.trip_date = trip_date;
    }

    public String getDriver_code() {
        return driver_code;
    }

    public void setDriver_code(String driver_code) {
        this.driver_code = driver_code;
    }
}
