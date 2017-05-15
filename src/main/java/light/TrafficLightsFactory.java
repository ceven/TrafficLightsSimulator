package light;

public class TrafficLightsFactory {

    public static TrafficLight createRedTrafficLight(final Direction direction) {
        return new TrafficLight.TrafficLightBuilder()
                .addColour(Colour.RED)
                .addDirection(direction)
                .build();
    }

}
