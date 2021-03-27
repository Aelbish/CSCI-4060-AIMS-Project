package csci4060.project.newaimsapp.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import csci4060.project.newaimsapp.database.entity.Delivery;

/**
 * This class communicates directly with the database using methods with SQL queries specified in the @query annotations
 * To get or store more data, you will need to create a method like below and annotate it with @query then write your query in SQL
 */
@Dao
public interface DeliveryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addDelivery(Delivery delivery);

    @Delete
    void removeDelivery(Delivery delivery);

    @Update
    void updateDelivery(Delivery delivery);

}
