package state;

import light.Colour;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public final class GreenState implements ILightsState {

    private static final long DEFAULT_DURATION_SECONDS = 270;
    private Duration duration;

    GreenState() {
        duration = Duration.of(DEFAULT_DURATION_SECONDS, ChronoUnit.SECONDS);
    }

    @Override
    public Colour getColour() {
        return Colour.GREEN;
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
        return State.YELLOW_LIGHTS;
    }
}
