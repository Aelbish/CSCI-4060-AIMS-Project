package csci4060.project.aimsmobileapp.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import csci4060.project.aimsmobileapp.database.entity.DeliveredProduct;

@Dao
public interface DeliveredProductDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addDeliveredProduct(DeliveredProduct deliveredProduct);

    @Query("SELECT bol_number FROM deliveredproduct WHERE sequence_number = :sequence_num")
    public int getBol_number(int sequence_num);

    @Query("UPDATE deliveredproduct SET bol_number = :bol_number WHERE sequence_number = :sequence_num")
    public void setBol_number(int bol_number, int sequence_num);

    @Query("SELECT fuel_product FROM deliveredproduct WHERE sequence_number = :sequence_num")
    public String getFuel_product(int sequence_num);

    @Query("UPDATE deliveredproduct SET fuel_product = :fuel_product WHERE sequence_number = :sequence_num")
    public void setFuel_product(String fuel_product, int sequence_num);

    @Query("SELECT start_load FROM deliveredproduct WHERE sequence_number = :sequence_num")
    public long getStart_load(int sequence_num);

    @Query("UPDATE deliveredproduct SET start_load = :start_load WHERE sequence_number = :sequence_num")
    public void setStart_load(long start_load, int sequence_num);

    @Query("SELECT end_load FROM deliveredproduct WHERE sequence_number = :sequence_num")
    public long getEnd_load(int sequence_num);

    @Query("UPDATE deliveredproduct SET end_load = :end_load WHERE sequence_number = :sequence_num")
    public void setEnd_load(long end_load, int sequence_num);

    @Query("SELECT gross_picked_up FROM deliveredproduct WHERE sequence_number = :sequence_num")
    public double getGross_picked_up(int sequence_num);

    @Query("UPDATE deliveredproduct SET gross_picked_up = :gross_picked_up WHERE sequence_number = :sequence_num")
    public void setGross_picked_up(double gross_picked_up, int sequence_num);

    @Query("SELECT net_picked_up FROM deliveredproduct WHERE sequence_number = :sequence_num")
    public double getNet_picked_up(int sequence_num);

    @Query("UPDATE deliveredproduct SET net_picked_up = :net_picked_up WHERE sequence_number = :sequence_num")
    public void setNet_picked_up(double net_picked_up, int sequence_num);

    @Query("SELECT product_name FROM deliveredproduct WHERE sequence_number = :sequence_num")
    public String getProduct_name(int sequence_num);

    @Query("UPDATE deliveredproduct SET product_name = :product_name WHERE sequence_number = :sequence_num")
    public void setProduct_name(String product_name, int sequence_num);

    @Query("SELECT gross_delivered FROM deliveredproduct WHERE sequence_number = :sequence_num")
    public double getGross_delivered(int sequence_num);

    @Query("UPDATE deliveredproduct SET gross_delivered = :gross_delivered WHERE sequence_number = :sequence_num")
    public void setGross_delivered(double gross_delivered, int sequence_num);

    @Query("SELECT net_delivered FROM deliveredproduct WHERE sequence_number = :sequence_num")
    public double getNet_delivered(int sequence_num);

    @Query("UPDATE deliveredproduct SET net_delivered = :net_delivered WHERE sequence_number = :sequence_num")
    public void setNet_delivered(double net_delivered, int sequence_num);

    @Query("SELECT ticket_num FROM deliveredproduct WHERE sequence_number = :sequence_num")
    public int getTicket_num(int sequence_num);

    @Query("UPDATE deliveredproduct SET ticket_num = :ticket_num WHERE sequence_number = :sequence_num")
    public void setTicket_num(int ticket_num, int sequence_num);

    @Query("SELECT start_truck_meter FROM deliveredproduct WHERE sequence_number = :sequence_num")
    public double getStart_truck_meter(int sequence_num);

    @Query("UPDATE deliveredproduct SET start_truck_meter = :start_truck_meter WHERE sequence_number = :sequence_num")
    public void setStart_truck_meter(double start_truck_meter, int sequence_num);

    @Query("SELECT end_truck_meter FROM deliveredproduct WHERE sequence_number = :sequence_num")
    public double getEnd_truck_meter(int sequence_num);

    @Query("UPDATE deliveredproduct SET end_truck_meter = :end_truck_meter WHERE sequence_number = :sequence_num")
    public void setEnd_truck_meter(double end_truck_meter, int sequence_num);

    @Query("SELECT start_stick_meter FROM deliveredproduct WHERE sequence_number = :sequence_num")
    public double getStart_stick_meter(int sequence_num);

    @Query("UPDATE deliveredproduct SET start_stick_meter = :start_stick_meter WHERE sequence_number = :sequence_num")
    public void setStart_stick_meter(double start_stick_meter, int sequence_num);

    @Query("SELECT end_stick_meter FROM deliveredproduct WHERE sequence_number = :sequence_num")
    public double getEnd_stick_meter(int sequence_num);

    @Query("UPDATE deliveredproduct SET end_stick_meter = :end_stick_meter WHERE sequence_number = :sequence_num")
    public void setEnd_stick_meter(double end_stick_meter, int sequence_num);

    @Query("SELECT delivery_comments FROM deliveredproduct WHERE sequence_number = :sequence_num")
    public String getDelivery_comments(int sequence_num);

    @Query("UPDATE deliveredproduct SET delivery_comments = :delivery_comments WHERE sequence_number = :sequence_num")
    public void setDelivery_comments(String delivery_comments, int sequence_num);
}
