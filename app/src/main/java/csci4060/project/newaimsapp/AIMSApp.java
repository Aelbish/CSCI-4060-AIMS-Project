package csci4060.project.newaimsapp;

import android.app.Application;
import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import csci4060.project.newaimsapp.database.AppDatabase;

public class AIMSApp extends Application {
    private final int NUM_OF_EXECUTOR_THREADS = 4;

    public static Context appContext;

    ExecutorService executorService = Executors.newFixedThreadPool(NUM_OF_EXECUTOR_THREADS);

    @Override
    public void onCreate() {
        super.onCreate();

        appContext = getApplicationContext();
    }

    private AppDatabase getDatabase() {
        return AppDatabase.getDatabase(this);
    }

    public DataRepository getRepository() {
        return DataRepository.getInstance(getDatabase());
    }
}
