package state;

import light.Colour;

import java.time.Duration;
import java.time.temporal.TemporalUnit;

public interface ILightsState {

    Colour getColour();

    Duration getDuration();

    void setDuration(final long duration, final TemporalUnit unit);

    State getNextState();
}
