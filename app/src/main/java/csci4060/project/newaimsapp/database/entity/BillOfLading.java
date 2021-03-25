package csci4060.project.newaimsapp.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity
public class BillOfLading {
    @PrimaryKey
    int bill_of_lading_id;
    String product_picked_up;
    Date time_started;
    Date time_stopped;
    double gross_picked_up;
    double net_picked_up;

    public BillOfLading(int bill_of_lading_id) {
        this.bill_of_lading_id = bill_of_lading_id;
    }

    public int getBill_of_lading_id() {
        return bill_of_lading_id;
    }

    public void setBill_of_lading_id(int bill_of_lading_id) {
        this.bill_of_lading_id = bill_of_lading_id;
    }

    public String getProduct_picked_up() {
        return product_picked_up;
    }

    public void setProduct_picked_up(String product_picked_up) {
        this.product_picked_up = product_picked_up;
    }

    public Date getTime_started() {
        return time_started;
    }

    public void setTime_started(Date time_started) {
        this.time_started = time_started;
    }

    public Date getTime_stopped() {
        return time_stopped;
    }

    public void setTime_stopped(Date time_stopped) {
        this.time_stopped = time_stopped;
    }

    public double getGross_picked_up() {
        return gross_picked_up;
    }

    public void setGross_picked_up(double gross_picked_up) {
        this.gross_picked_up = gross_picked_up;
    }

    public double getNet_picked_up() {
        return net_picked_up;
    }

    public void setNet_picked_up(double net_picked_up) {
        this.net_picked_up = net_picked_up;
    }
}
