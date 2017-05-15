package intersection;

import light.Direction;
import light.SynchronisedTrafficLight;

import java.util.Optional;

public final class FourWayTrafficIntersection extends TrafficIntersection {

    private SynchronisedTrafficLight northSouthLights;
    private SynchronisedTrafficLight eastWestLights;

    /**
     * A 4-way traffic intersection is a set of opposite (North-South) and (East-West) traffic lights
     */
    public FourWayTrafficIntersection() {
        northSouthLights = new SynchronisedTrafficLight();
        northSouthLights.addRedLight(Direction.NORTH);
        northSouthLights.addRedLight(Direction.SOUTH);
        eastWestLights = new SynchronisedTrafficLight();
        eastWestLights.addRedLight(Direction.EAST);
        eastWestLights.addRedLight(Direction.WEST);
        northSouthLights.addObservingLight(eastWestLights);
        eastWestLights.addObservingLight(northSouthLights);
        addSynchronisedTrafficLight(northSouthLights);
        addSynchronisedTrafficLight(eastWestLights);
    }

    public SynchronisedTrafficLight getNorthSouthLights() {
        return northSouthLights;
    }

    public SynchronisedTrafficLight getEastWestLights() {
        return eastWestLights;
    }

    @Override
    public Optional<SynchronisedTrafficLight> getFirstSimulationLight() {
        return Optional.of(northSouthLights);
    }
}
