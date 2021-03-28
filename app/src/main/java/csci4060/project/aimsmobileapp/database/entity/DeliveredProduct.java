package csci4060.project.aimsmobileapp.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DeliveredProduct {
    @PrimaryKey
    int sequence_number;
    int bol_number;
    String fuel_product;
    long start_load;
    long end_load;
    double gross_picked_up;
    double net_picked_up;
    String product_name;
    double gross_delivered;
    double net_delivered;
    int ticket_num;
    double start_truck_meter;
    double end_truck_meter;
    double start_stick_meter;
    double end_stick_meter;
    String delivery_comments;

    public DeliveredProduct(int sequence_number, int bol_number, String fuel_product, long start_load, long end_load, double gross_picked_up, double net_picked_up, String product_name, double gross_delivered, double net_delivered, int ticket_num, double start_truck_meter, double end_truck_meter, double start_stick_meter, double end_stick_meter, String delivery_comments) {
        this.sequence_number = sequence_number;
    }

    public int getSequence_number() {
        return sequence_number;
    }

    public void setSequence_number(int sequence_number) {
        this.sequence_number = sequence_number;
    }

    public int getBol_number() {
        return bol_number;
    }

    public void setBol_number(int bol_number) {
        this.bol_number = bol_number;
    }

    public String getFuel_product() {
        return fuel_product;
    }

    public void setFuel_product(String fuel_product) {
        this.fuel_product = fuel_product;
    }

    public long getStart_load() {
        return start_load;
    }

    public void setStart_load(long start_load) {
        this.start_load = start_load;
    }

    public long getEnd_load() {
        return end_load;
    }

    public void setEnd_load(long end_load) {
        this.end_load = end_load;
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

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public double getGross_delivered() {
        return gross_delivered;
    }

    public void setGross_delivered(double gross_delivered) {
        this.gross_delivered = gross_delivered;
    }

    public double getNet_delivered() {
        return net_delivered;
    }

    public void setNet_delivered(double net_delivered) {
        this.net_delivered = net_delivered;
    }

    public int getTicket_num() {
        return ticket_num;
    }

    public void setTicket_num(int ticket_num) {
        this.ticket_num = ticket_num;
    }

    public double getStart_truck_meter() {
        return start_truck_meter;
    }

    public void setStart_truck_meter(double start_truck_meter) {
        this.start_truck_meter = start_truck_meter;
    }

    public double getEnd_truck_meter() {
        return end_truck_meter;
    }

    public void setEnd_truck_meter(double end_truck_meter) {
        this.end_truck_meter = end_truck_meter;
    }

    public double getStart_stick_meter() {
        return start_stick_meter;
    }

    public void setStart_stick_meter(double start_stick_meter) {
        this.start_stick_meter = start_stick_meter;
    }

    public double getEnd_stick_meter() {
        return end_stick_meter;
    }

    public void setEnd_stick_meter(double endStickMeter, double end_stick_meter) {
        this.end_stick_meter = end_stick_meter;
    }

    public String getDelivery_comments() {
        return delivery_comments;
    }

    public void setDelivery_comments(String delivery_comments, int sequence_id) {
        this.delivery_comments = delivery_comments;
    }
}
