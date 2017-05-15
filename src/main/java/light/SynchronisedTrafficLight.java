package light;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import state.State;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;
import static java.time.LocalDateTime.now;
import static light.TrafficLightsFactory.createRedTrafficLight;
import static util.SchedulerFactory.createTaskScheduler;

public class SynchronisedTrafficLight {

    private static final Logger LOG = LoggerFactory.getLogger(SynchronisedTrafficLight.class);

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final List<TrafficLight> trafficLights;

    private final List<SynchronisedTrafficLight> observingLights;

    private State state;

    private ScheduledExecutorService taskScheduler;

    public SynchronisedTrafficLight() {
        this.trafficLights = newArrayList();
        this.observingLights = newArrayList();
        this.state = State.RED_LIGHTS;
        taskScheduler = createTaskScheduler();
    }

    public void updateStateAndNotify(final State newState) {
        if (newState == null) {
            throw new IllegalArgumentException("State cannot be null");
        }

        LOG.info("[{}]: Changing synchronised lights [{}] colour from [{}] to [{}]",
                now().format(DATE_TIME_FORMATTER), getDirections(), this.getColour(), newState.getColour());

        this.state = newState;
        if (newState == State.RED_LIGHTS && !observingLights.isEmpty()) {
            // Schedule a change of state for observing lights
            observingLights.forEach(light -> light.scheduleColourChange(newState));
        } else {
            // Schedule a change of state for this light
            this.scheduleColourChange(newState);
        }
    }

    private void scheduleColourChange(State newState) {
        taskScheduler.schedule(() -> this.state.updateState(this),
                newState.getDuration().getSeconds(), TimeUnit.SECONDS);
    }

    public TrafficLight addRedLight(final Direction direction) {
        if (direction == null) {
            throw new IllegalArgumentException("Direction cannot be null");
        }
        TrafficLight trafficLight = createRedTrafficLight(direction);
        this.trafficLights.add(trafficLight);
        return trafficLight;
    }

    public void addObservingLight(final SynchronisedTrafficLight light) {
        if (light == null) {
            throw new IllegalArgumentException("Light cannot be null");
        }
        this.observingLights.add(light);
    }

    public List<TrafficLight> getTrafficLights() {
        return Collections.unmodifiableList(trafficLights);
    }

    public Colour getColour() {
        return this.state.getColour();
    }

    private String getDirections() {
        return trafficLights.stream().map(l -> l.getDirection().name()).collect(Collectors.joining(", "));
    }

    public void stopLights() {
        LOG.info("Stopping {}", toString());
        taskScheduler.shutdownNow();
    }

    @Override
    public String toString() {
        return String.format("Synchronised lights - direction: [%s], colour: [%s]", getDirections(), state.getColour());
    }
}
