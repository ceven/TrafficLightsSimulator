package intersection;

import light.Direction;
import light.SynchronisedTrafficLight;

import java.util.List;
import java.util.Optional;

import static com.google.common.collect.Lists.newArrayList;
import static light.TrafficLightsFactory.createSyncTrafficLight;

public final class FourWayTrafficIntersection extends TrafficIntersection {

    private SynchronisedTrafficLight northSouthLights;
    private SynchronisedTrafficLight eastWestLights;

    /**
     * A 4-way traffic intersection is a set of opposite (North-South) and (East-West) traffic lights
     */
    public FourWayTrafficIntersection() {
        northSouthLights = createAndAddSyncTrafficLight(newArrayList(Direction.NORTH, Direction.SOUTH));
        eastWestLights = createAndAddSyncTrafficLight(newArrayList(Direction.EAST, Direction.WEST));
        northSouthLights.addObservingLight(eastWestLights);
        eastWestLights.addObservingLight(northSouthLights);
    }

    private SynchronisedTrafficLight createAndAddSyncTrafficLight(final List<Direction> lightsDirections) {
        SynchronisedTrafficLight synchronisedTrafficLight = createSyncTrafficLight(lightsDirections);
        addSynchronisedTrafficLight(synchronisedTrafficLight);
        return synchronisedTrafficLight;
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
