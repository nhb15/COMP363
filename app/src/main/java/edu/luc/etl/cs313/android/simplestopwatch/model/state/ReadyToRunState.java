package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import android.media.AudioManager;
import android.media.ToneGenerator;

import edu.luc.etl.cs313.android.simplestopwatch.R;

class ReadyToRunState implements StopwatchState {

    public ReadyToRunState(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    private final StopwatchSMStateView sm;

    @Override
    public void onStartStop() {
        sm.actionStop();
        sm.toStoppedState();

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

        /**
         * Guard condition that checks if 3 seconds have elapsed using threeSecondsElapsed method
         */
        if (threeSecondsElapsed()) {
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
        sm.updateUILaptime();
    }

    //FIXME: I'm not entirely sure if I added this resource correctly, but we can check
    @Override
    public int getId() {
        return R.string.READY_TO_RUN;
    }

    /**
     * threeSecondsElapsed method borrowed from Nate's isMinimum method
     * Checks if runtime equals 3, if so returns true, else false
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
