package state;

import light.Colour;
import light.SynchronisedTrafficLight;

import java.time.Duration;
import java.time.temporal.TemporalUnit;

public interface ILightsState {

    default void changeColour(final SynchronisedTrafficLight light) {
        light.updateStateAndNotify(getNextState());
    }

    Colour getColour();

    Duration getDuration();

    void setDuration(final long duration, final TemporalUnit unit);

    State getNextState();
}
