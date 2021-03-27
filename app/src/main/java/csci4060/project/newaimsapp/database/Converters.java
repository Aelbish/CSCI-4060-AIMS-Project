package csci4060.project.newaimsapp.database;

import androidx.room.TypeConverter;

import java.util.Date;

/**
 * This class is used by the database to convert java Date type to longs for the SQLite database to be able to store and vice versa
 */
public class Converters {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}