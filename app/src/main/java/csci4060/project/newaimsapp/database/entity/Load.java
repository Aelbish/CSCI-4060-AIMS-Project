package csci4060.project.newaimsapp.database.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity//(foreignKeys = {@ForeignKey(entity = Trip.class, parentColumns = "trip_id", childColumns = "trip_id")})
public class Load {
    @PrimaryKey
    int sequence_number;
    String waypoint_description;
    double latitude;
    double longitude;

    //Foreign Key
    int trip_id;

    public Load(int sequence_number, String waypoint_description, double latitude, double longitude, int trip_id) {
        this.sequence_number = sequence_number;
        this.waypoint_description = waypoint_description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.trip_id = trip_id;
    }


    public int getSequence_number() {
        return sequence_number;
    }

    public void setSequence_number(int sequence_number) {
        this.sequence_number = sequence_number;
    }

    public String getWaypoint_description() {
        return waypoint_description;
    }

    public void setWaypoint_description(String waypoint_description) {
        this.waypoint_description = waypoint_description;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }
}
