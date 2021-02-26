package csci4060.project.aimsmobileapp.Database;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "delivery")
public class Delivery {

    @PrimaryKey
    @ColumnInfo(name = "trip_id")
    private int tripID;

    @NonNull
    @ColumnInfo(name = "vendor_name")
    private String vendorName;

    @NonNull
    @ColumnInfo(name = "terminal_name")
    private String sourceTerminal;

    @ColumnInfo(name = "bill_of_lading_number")
    private int billOfLadingNumber;

    @ColumnInfo(name = "bill_of_lading_product")
    private String billOfLadingProduct;

    @ColumnInfo(name = "bill_of_lading_start_load")
    private Date billOfLadingStartLoadTime;

    @ColumnInfo(name = "bill_of_lading_stop_load")
    private Date billOfLadingStopLoadTime;

    @ColumnInfo(name = "bill_of_lading_gross")
    private double billOfLadingGrossPickedUp;

    @ColumnInfo(name = "bill_of_lading_net")
    private double billOfLadingNetPickedUp;

    @ColumnInfo(name = "product")
    private String productDelivered;

    @ColumnInfo(name = "product_gross_delivered")
    private double productDeliveredGross;

    @ColumnInfo(name = "product_net_delivered")
    private double productDeliveredNet;

    @ColumnInfo(name = "delivery_ticket_number")
    private long deliveryTicketNumber;

    @NonNull
    @ColumnInfo(name = "customer_name")
    private String customer;

    @NonNull
    @ColumnInfo(name = "customer_address")
    private String customerAddress;

    @NonNull
    @ColumnInfo(name = "customer_site_name")
    private String customerSiteName;

    @ColumnInfo(name = "meter_reading_before")
    private double meterReadingBeginning;

    @ColumnInfo(name = "meter_reading_after")
    private double meterReadingEnd;

    @ColumnInfo(name = "stick_reading_before")
    private double stickReadingBeginning;

    @ColumnInfo(name = "stick_reading_after")
    private double stickReadingEnd;

    @ColumnInfo(name = "delivery_comments")
    private String deliveryComments;

    public Delivery(int tripID, @NonNull String vendorName, @NonNull String sourceTerminal,
                    @NonNull String customer, @NonNull String customerAddress, @NonNull String customerSiteName) {
        this.tripID = tripID;
        this.vendorName = vendorName;
        this.sourceTerminal = sourceTerminal;
        this.customer = customer;
        this.customerAddress = customerAddress;
        this.customerSiteName = customerSiteName;
    }

    public int getTripID() {
        return tripID;
    }

    @NonNull
    public String getVendorName() {
        return vendorName;
    }

    @NonNull
    public String getSourceTerminal() {
        return sourceTerminal;
    }

    public int getBillOfLadingNumber() {
        return billOfLadingNumber;
    }

    public String getBillOfLadingProduct() {
        return billOfLadingProduct;
    }

    public Date getBillOfLadingStartLoadTime() {
        return billOfLadingStartLoadTime;
    }

    public Date getBillOfLadingStopLoadTime() {
        return billOfLadingStopLoadTime;
    }

    public double getBillOfLadingGrossPickedUp() {
        return billOfLadingGrossPickedUp;
    }

    public double getBillOfLadingNetPickedUp() {
        return billOfLadingNetPickedUp;
    }

    public String getProductDelivered() {
        return productDelivered;
    }

    public double getProductDeliveredGross() {
        return productDeliveredGross;
    }

    public double getProductDeliveredNet() {
        return productDeliveredNet;
    }

    public long getDeliveryTicketNumber() {
        return deliveryTicketNumber;
    }

    @NonNull
    public String getCustomer() {
        return customer;
    }

    @NonNull
    public String getCustomerAddress() {
        return customerAddress;
    }

    @NonNull
    public String getCustomerSiteName() {
        return customerSiteName;
    }

    public double getMeterReadingBeginning() {
        return meterReadingBeginning;
    }

    public double getMeterReadingEnd() {
        return meterReadingEnd;
    }

    public double getStickReadingBeginning() {
        return stickReadingBeginning;
    }

    public double getStickReadingEnd() {
        return stickReadingEnd;
    }

    public String getDeliveryComments() {
        return deliveryComments;
    }
}
