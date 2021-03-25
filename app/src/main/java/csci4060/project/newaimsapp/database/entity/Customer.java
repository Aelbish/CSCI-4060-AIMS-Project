package csci4060.project.newaimsapp.database.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = Container.class, parentColumns = "container_id", childColumns = "container_id")})
public class Customer {
    @PrimaryKey
    int customer_id;
    int container_id;
    String full_name;
    String site_name;
    String site_address;

    public Customer(int customer_id, String full_name, String site_name, String site_address) {
        this.customer_id = customer_id;
        this.full_name = full_name;
        this.site_name = site_name;
        this.site_address = site_address;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getContainer_id() {
        return container_id;
    }

    public void setContainer_id(int container_id) {
        this.container_id = container_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getSite_name() {
        return site_name;
    }

    public void setSite_name(String site_name) {
        this.site_name = site_name;
    }

    public String getSite_address() {
        return site_address;
    }

    public void setSite_address(String site_address) {
        this.site_address = site_address;
    }
}
