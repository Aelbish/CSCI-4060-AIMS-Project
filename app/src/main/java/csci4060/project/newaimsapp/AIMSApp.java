package csci4060.project.newaimsapp;

import android.app.Application;
import android.content.Context;
import android.provider.ContactsContract;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import csci4060.project.newaimsapp.database.AppDatabase;

/**
 * This is an extension of Application to allow for our database and repository to be able to be accessed from any class.
 * If you need to access the repository then just call AIMSapp.repository and you can do any method within the repository
 */
public class AIMSApp extends Application {
    private final int NUM_OF_EXECUTOR_THREADS = 4;

    public static Context appContext;
    public static AppDatabase database;
    public static DataRepository repository;

    public ExecutorService executorService = Executors.newFixedThreadPool(NUM_OF_EXECUTOR_THREADS);

    @Override
    public void onCreate() {
        super.onCreate();

        appContext = getApplicationContext();
        database = getDatabase();
        repository = getRepository();
    }

    private AppDatabase getDatabase() {
        return AppDatabase.getDatabase(this);
    }

    public DataRepository getRepository() {
        return DataRepository.getInstance(database);
    }
}





/*
TODO Display the trips to the driver in a nice format
TODO send a status update identifying the trip the driver selected
TODO enter required information for source and site and store it locally
TODO transmit the stored data
 */