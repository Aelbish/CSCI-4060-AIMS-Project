package csci4060.project.newaimsapp.database.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = User.class, parentColumns = "driver_id", childColumns = "driver_id"), @ForeignKey(entity = Load.class, parentColumns = "load_id", childColumns = "load_id")})
public class Trip {
    @PrimaryKey
    int trip_id;
    int driver_id;
    int load_id;
    String delivery_sequence;
    String route;

    public Trip(int trip_id, int driver_id)
    {
        this.trip_id = trip_id;
        this.driver_id = driver_id;
    }

    public int getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }

    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    public int getLoad_id() {
        return load_id;
    }

    public void setLoad_id(int load_id) {
        this.load_id = load_id;
    }

    public String getDelivery_sequence() {
        return delivery_sequence;
    }

    public void setDelivery_sequence(String delivery_sequence) {
        this.delivery_sequence = delivery_sequence;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }
}
