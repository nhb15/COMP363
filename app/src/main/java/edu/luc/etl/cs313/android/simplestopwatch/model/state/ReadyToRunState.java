package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Handler;

import edu.luc.etl.cs313.android.simplestopwatch.R;

class ReadyToRunState implements StopwatchState {

    public ReadyToRunState(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    private final StopwatchSMStateView sm;

    /**
     * Variable to count how many times the button has been pushed
     */
    private int clickCounter = 0; //initially, this was used to track click events. We removed in favor
                                  //of get.Runtime()
    private int tickCounter = 0; // keeps track of tick events


    /**
     * Overrides stopwatch's onStartStop method. Has an initial guard condition that checks
     * if the runTime, that is the time set displayed on the stopwatch model, is equal to 99. If yes
     * a handler is created to create a second long pause before a beep plays and the state machine
     * transitions to the runningState. if no, the statemachine Increment action is called
     */
    @Override
    public void onStartStop() {
        tickCounter = 0;
        if(sm.getRuntime() == 99){
            Handler handler = new Handler();//object that allows us to specify when we want an action to occur
            handler.postDelayed(new Runnable() {
                public void run() {//method that will run following the specified delays
                    beep();
                    //clickCounter = 0;
                    tickCounter = 0;
                    sm.toRunningState();
                }
            }, 1000);   //1 second
        }
        //if clock model has not yet reached 99, updates the clock model by one
        //and increments click counter
        else{
            sm.actionInc();
            //clickCounter++;
        }

    }

    /**
     * J/N
     * Let's get rid of onLapReset as an "onClick" as we only need one on Click action

    @Override
    public void onLapReset() {
        sm.toRunningState();
        sm.actionUpdateView();
    }
     */

    /**
     * Overrides stopwatch's onTick method. Initially increments tickCounter for every tick event,
     * then hits a guard condition that checks if the internal tickCounter has reached three. If yes,
     * a beep plays, tickCounter is reset, and the state machine transitions to the running state.
     */
    @Override
    public void onTick() {
        tickCounter++;//increment of tickCounter per every onTick event in the internal clockmodel

        /**
         * Guard condition that checks if 3 seconds have elapsed using threeSecondsElapsed method
         */
        //if (threeSecondsElapsed()) {
        if (tickCounter == 3){
            //TODO add single beep before transition
            beep();
            tickCounter = 0;
            sm.toRunningState();
        }

        }


    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }



    /**
     * getId returns the resource ReadyToRuin
     * @return
     */
    @Override
    public int getId() {
        return R.string.READY_TO_RUN;
    }

    /**
     * threeSecondsElapsed method borrowed from Nate's isMinimum method
     * Checks if runtime equals 3, if so returns true, else false
     */

     /**
     * threeSecondsElapsed checks if three seconds has passed. This method was used initially,
     * but has been replaced with a guard condition in the onTick method and an incrementing tickCounter
     * that increments per tick event.
     * @return
     */
    public boolean threeSecondsElapsed(){
        if (sm.getRuntime() == 3){
            return true;
        }
        else {
            return false;
        }

    }


    /**
     * Method that creates a simple tone generator to play a short beep.
     */
    public void beep(){
        /**
         * Taking thoughts from here:
         * https://developer.android.com/reference/android/media/ToneGenerator
         * and here
         * https://stackoverflow.com/questions/29509010/how-to-play-a-short-beep-to-android-phones-loudspeaker-programmatically
         * Below is a tone that should sound for 150 milliseconds, I still want to test it and maybe toy with the
         * actual tone
         */
        ToneGenerator transitionTone = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
        transitionTone.startTone(ToneGenerator.TONE_CDMA_PIP,150);

        /**We tried "toolkit beep" but it didn't work
         *
         * ADD TO INTERFACE

        ToneGenerator toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
        toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP, 150);

         * COULD ALSO TRY
         *
         * / send the tone to the "alarm" stream (classic beeps go there) with 50% volume
         ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 50);
         if (val >= taux_max) {
         taux_text.setTextColor(warnning_col);
         toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200); // 200 is duration in ms
         }
         */
    }
}
