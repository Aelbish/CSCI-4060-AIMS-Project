package csci4060.project.newaimsapp.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity//(foreignKeys = {@ForeignKey(entity = Load.class, parentColumns = "sequence_number", childColumns = "sequence_number")})
public class Vendor {
    @PrimaryKey @NonNull
    String destination_code;
    String destination_name;
    String address_1;
    String address_2;
    String city;
    String state_short;
    int postal_code;

    //Foreign Key
    private int number;

    public Vendor(@NonNull String destination_code, String destination_name, String address_1, String address_2, String city, String state_short, int postal_code, int number) {
        this.destination_code = destination_code;
        this.destination_name = destination_name;
        this.address_1 = address_1;
        this.address_2 = address_2;
        this.city = city;
        this.state_short = state_short;
        this.postal_code = postal_code;
        this.number = number;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int sequence_number) {
        this.number = sequence_number;
    }
}