package light;

import state.State;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

import static com.google.common.collect.Lists.newArrayList;
import static light.TrafficLightsFactory.createRedTrafficLight;
import static util.SchedulerFactory.createTaskScheduler;

public class SynchronisedTrafficLight {

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

    public void updateStateAndNotify(final State state) {
        if (state == null) {
            throw new IllegalArgumentException("State cannot be null");
        }
        this.state = state;
        if (state == State.RED_LIGHTS && !observingLights.isEmpty()) {
            // Schedule a change of state for observing lights

        } else {
            // Schedule a change of state for this light
        }
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
}
