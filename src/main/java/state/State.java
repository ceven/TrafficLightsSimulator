package state;

import light.Colour;
import light.SynchronisedTrafficLight;

import java.time.Duration;
import java.time.temporal.TemporalUnit;

public enum State {

    GREEN_LIGHTS(new GreenState()), YELLOW_LIGHTS(new YellowState()), RED_LIGHTS(new RedState());

    private ILightsState lightsState;

    State(ILightsState state) {
        this.lightsState =state;
    }

    public void updateState(final SynchronisedTrafficLight synchronisedTrafficLight) {
        synchronisedTrafficLight.updateStateAndNotify(getNextState());
    }

    public State getNextState(){
        return this.lightsState.getNextState();
    }

    public Colour getColour() {
        return this.lightsState.getColour();
    }

    public Duration getDuration() {
        return this.lightsState.getDuration();
    }

    public void setDuration(final long time, final TemporalUnit unit) {
        this.lightsState.setDuration(time, unit);
    }
}
