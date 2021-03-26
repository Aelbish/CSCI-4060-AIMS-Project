package csci4060.project.newaimsapp.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import csci4060.project.newaimsapp.database.entity.Delivery;

@Dao
public interface DeliveryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addDelivery(Delivery delivery);

    @Delete
    void removeDelivery(Delivery delivery);

    @Update
    void updateDelivery(Delivery delivery);

}
