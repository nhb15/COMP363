package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;

//Nate's
class RunningState implements StopwatchState {

    public RunningState(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    private final StopwatchSMStateView sm;

    /**
     * onStartStop is the onClick method adapted to the timer.
     * In the running state, we want to completely reset the timer and send it to the Stopped State on a click.
     */
    @Override
    public void onStartStop() {
        sm.actionStop();
        sm.actionReset();
        sm.toStoppedState();
    }

    /**
     * onTick decrements the timer by one every second until we reach the minimum (0), when we send it to the alarm state
     */
    @Override
    public void onTick() {

        if (isMinimum()){

            sm.toAlarmState(); //Once we reach minimum, we send program to the alarm state.
        }
        else {
            sm.actionDec();
        }

    }

    /**
     * Updates the view of the timer
     */
    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }

    @Override
    public int getId() {
        return R.string.RUNNING;
    }

    /**
     * isMinimum checks if we are at the minimum value of the timer (0)
     * @return true or false
     */
    public boolean isMinimum(){
        if (sm.getRuntime() == 0){
            return true;
        }
        else {
            return false;
        }

    }

}
