package intersection;

import light.Direction;
import light.SynchronisedTrafficLight;

import java.util.Optional;

public class FourWayTrafficIntersection extends TrafficIntersection {

    private SynchronisedTrafficLight northSouthLights;
    private SynchronisedTrafficLight eastWestLights;

    public FourWayTrafficIntersection() {
        northSouthLights = new SynchronisedTrafficLight();
        northSouthLights.addRedLight(Direction.NORTH);
        northSouthLights.addRedLight(Direction.SOUTH);
        eastWestLights = new SynchronisedTrafficLight();
        eastWestLights.addRedLight(Direction.EAST);
        eastWestLights.addRedLight(Direction.WEST);
        addSynchronisedTrafficLight(northSouthLights);
        addSynchronisedTrafficLight(eastWestLights);
    }

    @Override
    public Optional<SynchronisedTrafficLight> getFirstSimulationLight() {
        return Optional.of(northSouthLights);
    }
}
