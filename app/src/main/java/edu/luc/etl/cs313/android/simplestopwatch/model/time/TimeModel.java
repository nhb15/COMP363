package edu.luc.etl.cs313.android.simplestopwatch.model.time;

/**
 * The passive data model of the stopwatch.
 * It does not emit any events.
 *
 * @author laufer
 */
public interface TimeModel {
    void resetRuntime();
    void incRuntime();
    void decRuntime();
    int getRuntime();

    // Lap-related tests are no longer needed.
    /*void setLaptime();
    int getLaptime();*/
}
