package csci4060.project.aimsmobileapp.database.entity;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/**
 * This is the delivery class that Room uses to make a SQLite Table
 * THE ONLY VALUES IN THESE ENTITY CLASSES ARE VALUES TAKEN FROM THE GET API CALL WHERE WE RECEIVE A BUNCH OF INFORMATION
 * FOR STORING DELIVERY INFORMATION TO SEND BACK TO AIMS, SEE ****HAVE NOT MADE THESE CLASSES YET****
 */
@Entity//(foreignKeys = {@ForeignKey(entity = Load.class, parentColumns = "sequence_number", childColumns = "sequence_number")})
public class Delivery {
    @PrimaryKey
    int number;
    int delivery_req_number;
    int delivery_req_line_number;
    int product_id;
    String product_code;
    String product_description;
    double requested_quantity;
    String unit_of_measurement;
    String fill;

    public Delivery(int number, int delivery_req_number, int delivery_req_line_number, int product_id, String product_code, String product_description, double requested_quantity, String unit_of_measurement, String fill) {
        this.number = number;
        this.delivery_req_number = delivery_req_number;
        this.delivery_req_line_number = delivery_req_line_number;
        this.product_id = product_id;
        this.product_code = product_code;
        this.product_description = product_description;
        this.requested_quantity = requested_quantity;
        this.unit_of_measurement = unit_of_measurement;
        this.fill = fill;
    }


    public int getDelivery_req_number() {
        return delivery_req_number;
    }

    public void setDelivery_req_number(int delivery_req_number) {
        this.delivery_req_number = delivery_req_number;
    }

    public int getDelivery_req_line_number() {
        return delivery_req_line_number;
    }

    public void setDelivery_req_line_number(int delivery_req_line_number) {
        this.delivery_req_line_number = delivery_req_line_number;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public double getRequested_quantity() {
        return requested_quantity;
    }

    public void setRequested_quantity(double requested_quantity) {
        this.requested_quantity = requested_quantity;
    }

    public String getUnit_of_measurement() {
        return unit_of_measurement;
    }

    public void setUnit_of_measurement(String unit_of_measurement) {
        this.unit_of_measurement = unit_of_measurement;
    }

    public String getFill() {
        return fill;
    }

    public void setFill(String fill) {
        this.fill = fill;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
