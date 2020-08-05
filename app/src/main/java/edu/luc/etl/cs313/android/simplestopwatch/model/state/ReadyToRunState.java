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

    /**
     * from Nate: I think if you use the sm.getRuntime() for the "click's" since the clicks update the runtime already it would be easier - alternatively, keep clickcounter but try having clickCounter == 98
     * since it seems like one extra click is required to send it to running state
     */
    private int clickCounter = 0;
    private int tickCounter = 0; // keeps track of tics once


    @Override
    public void onStartStop() {
        tickCounter = 0;

        //Guard condition that checks if the clock model has reached 99
        //by checking if the clickCounter has increased up to 99
        //then resets clickCounter to 0 for future purposes
        //if(clickCounter == 99){
        if(sm.getRuntime() == 99){
            //TODO add single beep before transition

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    beep();
                    clickCounter = 0;
                    tickCounter = 0;
                    sm.toRunningState();
                }
            }, 1000);   //5 seconds



        }
        //if clock model has not yet reached 99, updates the clock model by one
        //and increments click counter
        else{
            sm.actionInc();
            clickCounter++;
        }
        //sm.actionStop();
        //sm.toStoppedState();

        /**
         * J/N
         * Do we need to start the clock ticks in order to count them here?
         *
         * Name it onClick or onButtonEvent
         */
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

    @Override
    public void onTick() {
        tickCounter++;

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

            /**
             * Can we count the ticks somehow in this state in a separate method for the 3 tick check?
             * See below, threeSecondsElapsed method
             */
        }

    //FIXME: No longer need Laptime - update name or change method
    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }

    //FIXME: I'm not entirely sure if I added this resource correctly, but we can check

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
     * from Nate: This is grabbing when the timer is incremented to 3 seconds and it's immediately sending it to running state -
     * I think if you use a tickCounter in onTick it might work?
     */

    /**
     * threeSecondsElapsed checks if three seconds has passed 
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
