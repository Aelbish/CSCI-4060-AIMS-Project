package csci4060.project.newaimsapp.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity//(foreignKeys = {@ForeignKey(entity = Load.class, parentColumns = "sequence_number", childColumns = "sequence_number")})
public class Customer {
    @PrimaryKey @NonNull
    String destination_code;
    String destination_name;
    String site_container_code;
    String site_container_description;
    String address_1;
    String address_2;
    String city;
    String state_short;
    int postal_code;

    //Foreign Key
    int sequence_number;

    public Customer(@NonNull String destination_code, String destination_name, String site_container_code, String site_container_description, String address_1, String address_2, String city, String state_short, int postal_code, int sequence_number) {
        this.destination_code = destination_code;
        this.destination_name = destination_name;
        this.site_container_code = site_container_code;
        this.site_container_description = site_container_description;
        this.address_1 = address_1;
        this.address_2 = address_2;
        this.city = city;
        this.state_short = state_short;
        this.postal_code = postal_code;
        this.sequence_number = sequence_number;
    }

    public String getDestination_code() {
        return destination_code;
    }

    public void setDestination_code(String destination_code) {
        this.destination_code = destination_code;
    }

    public String getDestination_name() {
        return destination_name;
    }

    public void setDestination_name(String destination_name) {
        this.destination_name = destination_name;
    }

    public String getSite_container_code() {
        return site_container_code;
    }

    public void setSite_container_code(String site_container_code) {
        this.site_container_code = site_container_code;
    }

    public String getSite_container_description() {
        return site_container_description;
    }

    public void setSite_container_description(String site_container_description) {
        this.site_container_description = site_container_description;
    }

    public String getAddress_1() {
        return address_1;
    }

    public void setAddress_1(String address_1) {
        this.address_1 = address_1;
    }

    public String getAddress_2() {
        return address_2;
    }

    public void setAddress_2(String address_2) {
        this.address_2 = address_2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState_short() {
        return state_short;
    }

    public void setState_short(String state_short) {
        this.state_short = state_short;
    }

    public int getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(int postal_code) {
        this.postal_code = postal_code;
    }

    public int getSequence_number() {
        return sequence_number;
    }

    public void setSequence_number(int sequence_number) {
        this.sequence_number = sequence_number;
    }
}
