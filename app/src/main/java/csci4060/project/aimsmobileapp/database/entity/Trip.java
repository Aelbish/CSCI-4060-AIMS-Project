package csci4060.project.aimsmobileapp.database.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/**
 * This is the trip class that Room uses to make a SQLite Table
 * THE ONLY VALUES IN THESE ENTITY CLASSES ARE VALUES TAKEN FROM THE GET API CALL WHERE WE RECEIVE A BUNCH OF INFORMATION
 * FOR STORING DELIVERY INFORMATION TO SEND BACK TO AIMS, SEE ****HAVE NOT MADE THESE CLASSES YET****
 */
@Entity//(foreignKeys = {@ForeignKey(entity = Driver.class, parentColumns = "driver_code", childColumns = "driver_code")})
public class Trip {
    //Attributes for this table
    @PrimaryKey
    int trip_id;
    String trip_name;
    String trip_date;
    int is_selected;

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

    public int getIs_selected() {
        return is_selected;
    }

    public void setIs_selected(int is_selected) {
        this.is_selected = is_selected;
    }
}
