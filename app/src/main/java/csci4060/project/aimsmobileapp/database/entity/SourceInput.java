package csci4060.project.aimsmobileapp.database.entity;

import androidx.room.Entity;

@Entity(primaryKeys = {"trip_id", "sequence_id"})
public class SourceInput {
    int trip_id;
    int sequence_id;
    String product_type;
    String start_date;
    String start_time;
    String end_date;
    String end_time;
    double trailer_gross_quantity;
    double trailer_net_quantity;
    double start_meter_reading;
    double end_meter_reading;
    double pickup_gross_quantity;
    double pickup_net_quantity;
    int bol_number;
    double pickup_ratio;

    public SourceInput(int trip_id, int sequence_id) {
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

    public double getTrailer_gross_quantity() {
        return trailer_gross_quantity;
    }

    public void setTrailer_gross_quantity(double trailer_gross_quantity) {
        this.trailer_gross_quantity = trailer_gross_quantity;
    }

    public double getTrailer_net_quantity() {
        return trailer_net_quantity;
    }

    public void setTrailer_net_quantity(double trailer_net_quantity) {
        this.trailer_net_quantity = trailer_net_quantity;
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

    public double getPickup_gross_quantity() {
        return pickup_gross_quantity;
    }

    public void setPickup_gross_quantity(double pickup_gross_quantity) {
        this.pickup_gross_quantity = pickup_gross_quantity;
    }

    public double getPickup_net_quantity() {
        return pickup_net_quantity;
    }

    public void setPickup_net_quantity(double pickup_net_quantity) {
        this.pickup_net_quantity = pickup_net_quantity;
    }

    public int getBol_number() {
        return bol_number;
    }

    public void setBol_number(int bol_number) {
        this.bol_number = bol_number;
    }

    public double getPickup_ratio() {
        return pickup_ratio;
    }

    public void setPickup_ratio(double pickup_ratio) {
        this.pickup_ratio = pickup_ratio;
    }
}
