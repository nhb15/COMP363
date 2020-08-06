package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;

class StoppedState implements StopwatchState {

    public StoppedState(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    private final StopwatchSMStateView sm;

    /**
     * In the stoppedState, press the Start/Stop button once to not only enter the ReadyToRunState
     * and but also increase the timer by one.
     */
    @Override
    public void onStartStop() {
        sm.actionStart();
        sm.toReadyToRunState();
        sm.actionInc();
    }

    @Override
    public void onTick() {
        throw new UnsupportedOperationException("onTick");
    }

    /** updateView updates the Runtime user interface
     *
     */
    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }

    /** getId returns the name of the Runtime state
     *
     * @return
     */
    @Override
    public int getId() {
        return R.string.STOPPED;
    }
}
