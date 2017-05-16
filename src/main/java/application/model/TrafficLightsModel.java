package application.model;

import intersection.FourWayTrafficIntersection;
import light.Direction;
import light.SynchronisedTrafficLight;
import light.TrafficLight;

public class TrafficLightsModel {

    private final FourWayTrafficIntersection trafficIntersection;

    public TrafficLightsModel() {
        trafficIntersection = new FourWayTrafficIntersection();
    }

    public FourWayTrafficIntersection getTrafficIntersection() {
        return trafficIntersection;
    }

    public TrafficLight getNorthLight() {
        return getLight(trafficIntersection.getNorthSouthLights(), Direction.NORTH);
    }

    public TrafficLight getSouthLight() {
        return getLight(trafficIntersection.getNorthSouthLights(), Direction.SOUTH);
    }

    public TrafficLight getEastLight() {
        return getLight(trafficIntersection.getEastWestLights(), Direction.EAST);
    }

    public TrafficLight getWestLight() {
        return getLight(trafficIntersection.getEastWestLights(), Direction.WEST);
    }

    private static TrafficLight getLight(SynchronisedTrafficLight synchronisedTrafficLight, Direction direction) {
        return synchronisedTrafficLight.getTrafficLights()
                .stream()
                .filter(l -> l.getDirection().equals(direction))
                .findFirst()
                .orElse(null);
    }
}
