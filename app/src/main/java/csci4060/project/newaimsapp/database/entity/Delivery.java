package csci4060.project.newaimsapp.database.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = Load.class, parentColumns = "load_id", childColumns = "load_id"), @ForeignKey(entity = BillOfLading.class, parentColumns = "bill_of_lading_id", childColumns = "bill_of_lading_id")})
public class Delivery {
    @PrimaryKey
    int delivery_id;
    int load_id;
    int bill_of_lading_id;
    String product_delivered_name;
    double product_delivered_gross;
    double product_delivered_net;
    int delivery_ticket_number;
    double trailer_meter_readings;
    double tank_reading_before;
    double tank_reading_after;

    public Delivery(int delivery_id, int load_id) {
        this.delivery_id = delivery_id;
        this.load_id = load_id;
    }

    public int getDelivery_id() {
        return delivery_id;
    }

    public void setDelivery_id(int delivery_id) {
        this.delivery_id = delivery_id;
    }

    public int getLoad_id() {
        return load_id;
    }

    public void setLoad_id(int load_id) {
        this.load_id = load_id;
    }

    public int getBill_of_lading_id() {
        return bill_of_lading_id;
    }

    public void setBill_of_lading_id(int bill_of_lading_id) {
        this.bill_of_lading_id = bill_of_lading_id;
    }

    public String getProduct_delivered_name() {
        return product_delivered_name;
    }

    public void setProduct_delivered_name(String product_delivered_name) {
        this.product_delivered_name = product_delivered_name;
    }

    public double getProduct_delivered_gross() {
        return product_delivered_gross;
    }

    public void setProduct_delivered_gross(double product_delivered_gross) {
        this.product_delivered_gross = product_delivered_gross;
    }

    public double getProduct_delivered_net() {
        return product_delivered_net;
    }

    public void setProduct_delivered_net(double product_delivered_net) {
        this.product_delivered_net = product_delivered_net;
    }

    public int getDelivery_ticket_number() {
        return delivery_ticket_number;
    }

    public void setDelivery_ticket_number(int delivery_ticket_number) {
        this.delivery_ticket_number = delivery_ticket_number;
    }

    public double getTrailer_meter_readings() {
        return trailer_meter_readings;
    }

    public void setTrailer_meter_readings(double trailer_meter_readings) {
        this.trailer_meter_readings = trailer_meter_readings;
    }

    public double getTank_reading_before() {
        return tank_reading_before;
    }

    public void setTank_reading_before(double tank_reading_before) {
        this.tank_reading_before = tank_reading_before;
    }

    public double getTank_reading_after() {
        return tank_reading_after;
    }

    public void setTank_reading_after(double tank_reading_after) {
        this.tank_reading_after = tank_reading_after;
    }
}
