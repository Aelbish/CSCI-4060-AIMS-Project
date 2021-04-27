package csci4060.project.aimsmobileapp.database.entity;

import androidx.room.Entity;

@Entity(primaryKeys = {"trip_id", "sequence_id"})
public class SiteInput {
    int trip_id;
    int sequence_id;
    String product_type;
    String start_date;
    String start_time;
    String end_date;
    String end_time;
    double begin_site_container_reading;
    double end_site_container_reading;
    double start_meter_reading;
    double end_meter_reading;
    double delivered_gross_quantity;
    double delivered_net_quantity;
    int delivery_ticket_number;
    String deliveryComment;
    double pickup_ratio;

    public SiteInput(int trip_id, int sequence_id) {
        this.trip_id = trip_id;
        this.sequence_id = sequence_id;
    }

    public int getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }

    public int getSequence_id() {
        return sequence_id;
    }

    public void setSequence_id(int sequence_id) {
        this.sequence_id = sequence_id;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public double getBegin_site_container_reading() {
        return begin_site_container_reading;
    }

    public void setBegin_site_container_reading(double begin_site_container_reading) {
        this.begin_site_container_reading = begin_site_container_reading;
    }

    public double getEnd_site_container_reading() {
        return end_site_container_reading;
    }

    public void setEnd_site_container_reading(double end_site_container_reading) {
        this.end_site_container_reading = end_site_container_reading;
    }

    public double getStart_meter_reading() {
        return start_meter_reading;
    }

    public void setStart_meter_reading(double start_meter_reading) {
        this.start_meter_reading = start_meter_reading;
    }

    public double getEnd_meter_reading() {
        return end_meter_reading;
    }

    public void setEnd_meter_reading(double end_meter_reading) {
        this.end_meter_reading = end_meter_reading;
    }

    public double getDelivered_gross_quantity() {
        return delivered_gross_quantity;
    }

    public void setDelivered_gross_quantity(double delivered_gross_quantity) {
        this.delivered_gross_quantity = delivered_gross_quantity;
    }

    public double getDelivered_net_quantity() {
        return delivered_net_quantity;
    }

    public void setDelivered_net_quantity(double delivered_net_quantity) {
        this.delivered_net_quantity = delivered_net_quantity;
    }

    public int getDelivery_ticket_number() {
        return delivery_ticket_number;
    }

    public void setDelivery_ticket_number(int delivery_ticket_number) {
        this.delivery_ticket_number = delivery_ticket_number;
    }

    public String getDeliveryComment() {
        return deliveryComment;
    }

    public void setDeliveryComment(String deliveryComment) {
        this.deliveryComment = deliveryComment;
    }

    public double getPickup_ratio() {
        return pickup_ratio;
    }

    public void setPickup_ratio(double pickup_ratio) {
        this.pickup_ratio = pickup_ratio;
    }
}
