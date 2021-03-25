package csci4060.project.newaimsapp.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import csci4060.project.newaimsapp.database.entity.Container;

@Dao
public interface ContainerDao {
    @Insert
    void addContainer(Container container);

    @Delete
    void deleteContainer(Container container);

    @Update
    void updateContainer(Container container);

    @Query("SELECT customer_id FROM Container WHERE container_id = :container_id")
    LiveData<Integer> getCustomerId(int container_id);

    @Query("SELECT location FROM Container WHERE container_id = :container_id")
    LiveData<String> getLocation(int container_id);

    @Query("SELECT description FROM Container WHERE container_id = :container_id")
    LiveData<String> getDescription(int container_id);

    @Query("SELECT capacity FROM Container WHERE container_id = :container_id")
    LiveData<Double> getCapacity(int container_id);

    @Query("SELECT special_instructions FROM Container WHERE container_id = :container_id")
    LiveData<String> getSpecialInstructions(int container_id);
}
