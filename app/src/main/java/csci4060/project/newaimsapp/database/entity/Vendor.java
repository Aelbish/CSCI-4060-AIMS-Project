package csci4060.project.newaimsapp.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Vendor {
    @PrimaryKey
    int vendor_id;
    String full_name;
    String terminal_name;
    String terminal_address;

    public Vendor(int vendor_id, String full_name, String terminal_name, String terminal_address) {
        this.vendor_id = vendor_id;
        this.full_name = full_name;
        this.terminal_name = terminal_name;
        this.terminal_address = terminal_address;
    }

    public int getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(int vendor_id) {
        this.vendor_id = vendor_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getTerminal_name() {
        return terminal_name;
    }

    public void setTerminal_name(String terminal_name) {
        this.terminal_name = terminal_name;
    }

    public String getTerminal_address() {
        return terminal_address;
    }

    public void setTerminal_address(String terminal_address) {
        this.terminal_address = terminal_address;
    }
}
