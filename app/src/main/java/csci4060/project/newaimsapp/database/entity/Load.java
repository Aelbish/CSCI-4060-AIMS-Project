package csci4060.project.newaimsapp.database.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = Trip.class, parentColumns = "trip_id", childColumns = "trip_id"), @ForeignKey(entity = Customer.class, parentColumns = "customer_id", childColumns = "customer_id"), @ForeignKey(entity = Vendor.class, parentColumns = "vendor_id", childColumns = "vendor_id")})
public class Load {
    @PrimaryKey
    int load_id;
    int trip_id;
    int customer_id;
    int vendor_id;
    String product_requested;
    double requested_quantity;

    public Load(int load_id, int trip_id, int customer_id, int vendor_id)
    {
        this.load_id = load_id;
        this.trip_id = trip_id;
        this.customer_id = customer_id;
        this.vendor_id = vendor_id;
    }

    public int getLoad_id() {
        return load_id;
    }

    public void setLoad_id(int load_id) {
        this.load_id = load_id;
    }

    public int getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(int vendor_id) {
        this.vendor_id = vendor_id;
    }

    public String getProduct_requested() {
        return product_requested;
    }

    public void setProduct_requested(String product_requested) {
        this.product_requested = product_requested;
    }

    public double getRequested_quantity() {
        return requested_quantity;
    }

    public void setRequested_quantity(double requested_quantity) {
        this.requested_quantity = requested_quantity;
    }
}
