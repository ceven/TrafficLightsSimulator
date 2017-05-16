package light;

import java.util.List;

public final class TrafficLightsFactory {

    private TrafficLightsFactory() {

    }

    public static TrafficLight createRedTrafficLight(final Direction direction) {
        return new TrafficLight.TrafficLightBuilder()
                .addColour(Colour.RED)
                .addDirection(direction)
                .build();
    }

    public static SynchronisedTrafficLight createSyncTrafficLight(final List<Direction> trafficLightsDirection){
        SynchronisedTrafficLight synchronisedTrafficLight = new SynchronisedTrafficLight();
        trafficLightsDirection.forEach(synchronisedTrafficLight::addRedLight);
        return synchronisedTrafficLight;
    }

}
