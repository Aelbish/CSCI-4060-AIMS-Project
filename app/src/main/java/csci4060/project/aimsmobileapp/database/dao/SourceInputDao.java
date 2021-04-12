package csci4060.project.aimsmobileapp.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import csci4060.project.aimsmobileapp.database.entity.SourceInput;

@Dao
public interface SourceInputDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addSourceInput(SourceInput sourceInput);

    @Update
    void updateSourceInput(SourceInput sourceInput);

    @Query("SELECT product_type FROM SourceInput WHERE trip_id = :trip_id AND sequence_id = :sequence_id")
    String getProduct_type(int trip_id, int sequence_id);

    @Query("UPDATE SourceInput SET product_type = :product_type WHERE trip_id = :trip_id AND sequence_id = :sequence_id")
    void setProduct_type(String product_type, int trip_id, int sequence_id);

    @Query("SELECT start_date FROM SourceInput WHERE trip_id = :trip_id AND sequence_id = :sequence_id")
    String getStart_date(int trip_id, int sequence_id);

    @Query("UPDATE SourceInput SET start_date = :start_date WHERE trip_id = :trip_id AND sequence_id = :sequence_id")
    void setStart_date(String start_date, int trip_id, int sequence_id);

    @Query("SELECT start_time FROM SourceInput WHERE trip_id = :trip_id AND sequence_id = :sequence_id")
    String getStart_time(int trip_id, int sequence_id);

    @Query("UPDATE SourceInput SET start_time = :start_time WHERE trip_id = :trip_id AND sequence_id = :sequence_id")
    void setStart_time(String start_time, int trip_id, int sequence_id);

    @Query("SELECT end_date FROM SourceInput WHERE trip_id = :trip_id AND sequence_id = :sequence_id")
    String getEnd_date(int trip_id, int sequence_id);

    @Query("UPDATE SourceInput SET end_date = :end_date WHERE trip_id = :trip_id AND sequence_id = :sequence_id")
    void setEnd_date(String end_date, int trip_id, int sequence_id);

    @Query("SELECT end_time FROM SourceInput WHERE trip_id = :trip_id AND sequence_id = :sequence_id")
    String getEnd_time(int trip_id, int sequence_id);

    @Query("UPDATE SourceInput SET end_time = :end_time WHERE trip_id = :trip_id AND sequence_id = :sequence_id")
    void setEnd_time(String end_time, int trip_id, int sequence_id);

    @Query("SELECT trailer_gross_quantity FROM SourceInput WHERE trip_id = :trip_id AND sequence_id = :sequence_id")
    double getTrailer_gross_quantity(int trip_id, int sequence_id);

    @Query("UPDATE SourceInput SET trailer_gross_quantity = :trailer_gross_quantity WHERE trip_id = :trip_id AND sequence_id = :sequence_id")
    void setTrailer_gross_quantity(double trailer_gross_quantity, int trip_id, int sequence_id);

    @Query("SELECT trailer_net_quantity FROM SourceInput WHERE trip_id = :trip_id AND sequence_id = :sequence_id")
    double getTrailer_net_quantity(int trip_id, int sequence_id);

    @Query("UPDATE SourceInput SET trailer_net_quantity = :trailer_net_quantity WHERE trip_id = :trip_id AND sequence_id = :sequence_id")
    void setTrailer_net_quantity(double trailer_net_quantity, int trip_id, int sequence_id);

    @Query("SELECT start_meter_reading FROM SourceInput WHERE trip_id = :trip_id AND sequence_id = :sequence_id")
    double getStart_meter_reading(int trip_id, int sequence_id);

    @Query("UPDATE SourceInput SET start_meter_reading = :start_meter_reading WHERE trip_id = :trip_id AND sequence_id = :sequence_id")
    void setStart_meter_reading(double start_meter_reading, int trip_id, int sequence_id);

    @Query("SELECT end_meter_reading FROM SourceInput WHERE trip_id = :trip_id AND sequence_id = :sequence_id")
    double getEnd_meter_reading(int trip_id, int sequence_id);

    @Query("UPDATE SourceInput SET end_meter_reading = :end_meter_reading WHERE trip_id = :trip_id AND sequence_id = :sequence_id")
    void setEnd_meter_reading(double end_meter_reading, int trip_id, int sequence_id);

    @Query("SELECT pickup_gross_quantity FROM SourceInput WHERE trip_id = :trip_id AND sequence_id = :sequence_id")
    double getPickup_gross_quantity(int trip_id, int sequence_id);

    @Query("UPDATE SourceInput SET pickup_gross_quantity = :pickup_gross_quantity WHERE trip_id = :trip_id AND sequence_id = :sequence_id")
    void setPickup_gross_quantity(double pickup_gross_quantity, int trip_id, int sequence_id);

    @Query("SELECT pickup_net_quantity FROM SourceInput WHERE trip_id = :trip_id AND sequence_id = :sequence_id")
    double getPickup_net_quantity(int trip_id, int sequence_id);

    @Query("UPDATE SourceInput SET pickup_net_quantity = :pickup_net_quantity WHERE trip_id = :trip_id AND sequence_id = :sequence_id")
    void setPickup_net_quantity(double pickup_net_quantity, int trip_id, int sequence_id);

    @Query("SELECT bol_number FROM SourceInput WHERE trip_id = :trip_id AND sequence_id = :sequence_id")
    int getBol_number(int trip_id, int sequence_id);

    @Query("UPDATE SourceInput SET bol_number = :bol_number WHERE trip_id = :trip_id AND sequence_id = :sequence_id")
    void setBol_number(int bol_number, int trip_id, int sequence_id);

    @Query("SELECT pickup_ratio FROM SourceInput WHERE trip_id = :trip_id AND sequence_id = :sequence_id")
    double getPickup_ratio(int trip_id, int sequence_id);

    @Query("UPDATE SourceInput SET pickup_ratio = :pickup_ratio WHERE trip_id = :trip_id AND sequence_id = :sequence_id")
    void setPickup_ratio(double pickup_ratio, int trip_id, int sequence_id);
}
