package state;

import light.Colour;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public class YellowState implements ILightsState {

    private static final long DEFAULT_DURATION_SECONDS = 30;
    private Duration duration;

    YellowState() {
        duration = Duration.of(DEFAULT_DURATION_SECONDS, ChronoUnit.SECONDS);
    }

    @Override
    public Colour getColour() {
        return Colour.YELLOW;
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
        return State.RED_LIGHTS;
    }
}
