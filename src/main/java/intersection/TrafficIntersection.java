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

public class TrafficIntersection {

    private static final Logger LOG = LoggerFactory.getLogger(TrafficIntersection.class);

    private final List<SynchronisedTrafficLight> synchronisedTrafficLights;
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

    public void startSimulation() {
        LOG.info("Starting Traffic Lights Simulation");
        SynchronisedTrafficLight firstSimulationLight = getFirstSimulationLight().orElseThrow(
                () -> new IllegalStateException("This intersection has no lights, cannot start simulation")
        );
        firstSimulationLight.updateStateAndNotify(State.GREEN_LIGHTS);
    }

    public void stopSimulationIn(final int time, final TimeUnit unit) {
        scheduledExecutorService.schedule(this::stopSimulation, time, unit);
    }

    private void stopSimulation() {
        LOG.info("Stopping Simulation");
        synchronisedTrafficLights.forEach(SynchronisedTrafficLight::stopLights);
        scheduledExecutorService.shutdownNow();
    }
}
