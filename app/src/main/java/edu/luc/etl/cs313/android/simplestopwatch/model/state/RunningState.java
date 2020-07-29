package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;

//Nate's
class RunningState implements StopwatchState {

    public RunningState(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    private final StopwatchSMStateView sm;

    @Override
    public void onStartStop() {
        sm.actionStop();
        sm.toStoppedState();
    }
/**
 * J/N
 * Let's get rid of onLapReset as an "onClick" as we only need one on Click action
    @Override
    public void onLapReset() {
        sm.actionLap();
        //sm.toLapRunningState();
    }
 */

    @Override
    public void onTick() {
        sm.actionDec();

        if (isMinimum()){
            sm.toAlarmState();
        }
        sm.toRunningState();

        //decrement by one, check if at zero
    }

    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }

    @Override
    public int getId() {
        return R.string.RUNNING;
    }

    public boolean isMinimum(){

        return false;
    }



}
