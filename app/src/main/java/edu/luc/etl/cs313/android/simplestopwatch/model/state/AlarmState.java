package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;

class AlarmState implements StopwatchState {

    public AlarmState(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    private final StopwatchSMStateView sm;

    @Override
    public void onStartStop() {
        sm.actionStart();
        sm.toStoppedState();
    }
/**
 * J/N
 * Let's get rid of onLapReset as an "onClick" as we only need one on Click action

    @Override
    public void onLapReset() {
        sm.toStoppedState();
        sm.actionUpdateView();
    }
 */

    @Override
    public void onTick() {
        throw new UnsupportedOperationException("onTick");
    }

    //FIXME:  No longer need laptime, but we do need to update the view...
    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }


    //FIXME: I'm not entirely sure if I added this resource correctly, but we can check
    @Override
    public int getId() {
        return R.string.ALARM;
    }

}
