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
    //FIXME: Do we need this anymore? Probably not
    @Override
    public void onLapReset() {
        sm.toStoppedState();
        sm.actionUpdateView();
    }

    @Override
    public void onTick() {
        throw new UnsupportedOperationException("onTick");
    }

    //FIXME:  No longer need laptime
    @Override
    public void updateView() {
        sm.updateUILaptime();
    }


    //FIXME: I'm not entirely sure if I added this resource correctly, but we can check
    @Override
    public int getId() {
        return R.string.ALARM;
    }
}
