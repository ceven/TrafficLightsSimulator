package intersection;

import light.SynchronisedTrafficLight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import state.State;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.google.common.collect.Lists.newArrayList;
import static util.SchedulerFactory.createTaskScheduler;

/**
 * A general model for a traffic intersection, which may contain as many lights as desired.
 * (For the intersection corresponding to the given problem, see subclass FourWayTrafficIntersection)
 */
public class TrafficIntersection {

    private static final Logger LOG = LoggerFactory.getLogger(TrafficIntersection.class);

    // A traffic intersection is composed of a set of traffic lights "in sync",
    // i.e. lights which change of colour at the same time
    private final List<SynchronisedTrafficLight> synchronisedTrafficLights;

    // This executor service can schedule tasks at given times.
    // It is used to asynchronously update the state of traffic lights
    // after a specific time.
    private ScheduledExecutorService scheduledExecutorService;

    public TrafficIntersection() {
        synchronisedTrafficLights = newArrayList();
        scheduledExecutorService = createTaskScheduler();
    }

    public void addSynchronisedTrafficLight(final SynchronisedTrafficLight light) {
        if (light == null) {
            throw new IllegalArgumentException("Light cannot be null");
        }
        synchronisedTrafficLights.add(light);
    }

    public Optional<SynchronisedTrafficLight> getFirstSimulationLight() {
        return synchronisedTrafficLights.isEmpty() ? Optional.empty() : Optional.of(synchronisedTrafficLights.get(0));
    }

    /**
     * Starts the traffic simulation by setting the first set of synchronised traffic lights to green.
     * The lights will then self-notify themselves to update their own state, or the state of the next set
     * of traffic lights.
     */
    public void startSimulation() {
        LOG.info("Starting Traffic Lights Simulation");
        SynchronisedTrafficLight firstSimulationLight = getFirstSimulationLight().orElseThrow(
                () -> new IllegalStateException("This intersection has no lights, cannot start simulation")
        );
        firstSimulationLight.updateStateAndNotify(State.GREEN_LIGHTS);
    }

    /**
     * Schedule to stop the entire simulation after a given time (asynchronous call)
     */
    public void stopSimulationIn(final int time, final TimeUnit unit) {
        scheduledExecutorService.schedule(this::stopSimulation, time, unit);
    }

    /**
     * Stop the simulation now. An attempt is made to stop the processing tasks immediately (best effort).
     * No more tasks will be accepted by the scheduler.
     */
    private void stopSimulation() {
        LOG.info("Stopping Simulation");
        synchronisedTrafficLights.forEach(SynchronisedTrafficLight::stopLights);
        scheduledExecutorService.shutdownNow();
    }
}
