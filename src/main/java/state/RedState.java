package state;

import light.Colour;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public class RedState implements ILightsState {

    private static final long DEFAULT_DURATION_SECONDS = 0;
    private Duration duration;

    RedState() {
        duration = Duration.of(DEFAULT_DURATION_SECONDS, ChronoUnit.SECONDS);
    }

    @Override
    public Colour getColour() {
        return Colour.RED;
    }

    @Override
    public Duration getDuration() {
        return this.duration;
    }

    @Override
    public void setDuration(long duration, TemporalUnit unit) {
        this.duration = Duration.of(duration, unit);
    }

    @Override
    public State getNextState() {
        return State.GREEN_LIGHTS;
    }
}
