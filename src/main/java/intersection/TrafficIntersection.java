package intersection;

import light.SynchronisedTrafficLight;
import state.State;

import java.util.List;
import java.util.Optional;

import static com.google.common.collect.Lists.newArrayList;

public class TrafficIntersection {

    private final List<SynchronisedTrafficLight> synchronisedTrafficLights;

    public TrafficIntersection() {
        synchronisedTrafficLights = newArrayList();
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
        SynchronisedTrafficLight firstSimulationLight = getFirstSimulationLight().orElseThrow(
                () -> new IllegalStateException("This intersection has no lights, cannot start simulation")
        );
        firstSimulationLight.updateStateAndNotify(State.GREEN_LIGHTS);
    }
}
