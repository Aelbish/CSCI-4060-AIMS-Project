package csci4060.project.newaimsapp.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Driver {
    //Attributes for this table
    @PrimaryKey @NonNull
    String driver_code;
    String driver_name;
    int truck_id;
    String truck_code;
    String truck_description;
    int trailer_id;
    String trailer_code;
    String trailer_description;
    //TODO Potentially make a truck/trailer class entity


    public Driver(@NonNull String driver_code, String driver_name, int truck_id, String truck_code, String truck_description, int trailer_id, String trailer_code, String trailer_description) {
        this.driver_code = driver_code;
        this.driver_name = driver_name;
        this.truck_id = truck_id;
        this.truck_code = truck_code;
        this.truck_description = truck_description;
        this.trailer_id = trailer_id;
        this.trailer_code = trailer_code;
        this.trailer_description = trailer_description;
    }


    public String getDriver_code() {
        return driver_code;
    }

    public void setDriver_code(String driver_code) {
        this.driver_code = driver_code;
    }

    public String getDriver_name() {
        return driver_name;
    }

    public void setDriver_name(String driver_name) {
        this.driver_name = driver_name;
    }

    public int getTruck_id() {
        return truck_id;
    }

    public void setTruck_id(int truck_id) {
        this.truck_id = truck_id;
    }

    public String getTruck_code() {
        return truck_code;
    }

    public void setTruck_code(String truck_code){
        this.truck_code = truck_code;
    }

    public String getTruck_description() {
        return truck_description;
    }

    public void setTruck_description(String truck_description) {
        this.truck_description = truck_description;
    }

    public int getTrailer_id() {
        return trailer_id;
    }

    public void setTrailer_id(int trailer_id) {
        this.trailer_id = trailer_id;
    }

    public String getTrailer_code() {
        return trailer_code;
    }

    public void setTrailer_code(String trailer_code) {
        this.trailer_code = trailer_code;
    }

    public String getTrailer_description() {
        return trailer_description;
    }

    public void setTrailer_description(String trailer_description) {
        this.trailer_description = trailer_description;
    }
}
