package edu.luc.etl.cs313.android.simplestopwatch.model.state;

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
    }

    @Override
    public void onLapReset() {
        sm.toRunningState();
        sm.actionUpdateView();
    }

    @Override
    public void onTick() {
        sm.actionInc();
        sm.toReadyToRunState();
    }
    //FIXME: No longer need Laptime
    @Override
    public void updateView() {
        sm.updateUILaptime();
    }

    //FIXME: I'm not entirely sure if I added this resource correctly, but we can check
    @Override
    public int getId() {
        return R.string.READY_TO_RUN;
    }
}
