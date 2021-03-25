package csci4060.project.newaimsapp;

import android.app.Application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AIMSApp extends Application {
    private final int NUM_OF_EXECUTOR_THREADS = 4;

    ExecutorService executorService = Executors.newFixedThreadPool(NUM_OF_EXECUTOR_THREADS);
}
