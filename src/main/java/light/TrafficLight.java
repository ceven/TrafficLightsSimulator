package light;

public final class TrafficLight {

    private final Direction direction;
    private Colour colour;

    private TrafficLight(TrafficLightBuilder builder) {
        if (builder == null) {
            throw new IllegalArgumentException("Builder cannot be null");
        }
        if (builder.builderDirection == null) {
            throw new IllegalArgumentException("Direction cannot be null");
        }
        if (builder.builderColour == null) {
            throw new IllegalArgumentException("Colour cannot be null");
        }
        this.direction = builder.builderDirection;
        this.colour = builder.builderColour;
    }

    public Direction getDirection() {
        return direction;
    }

    public Colour getColour() {
        return colour;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }

    public static class TrafficLightBuilder {

        private Direction builderDirection;
        private Colour builderColour;

        public TrafficLightBuilder() {
        }

        public TrafficLightBuilder addDirection(Direction direction) {
            this.builderDirection = direction;
            return this;
        }

        public TrafficLightBuilder addColour(Colour colour) {
            this.builderColour = colour;
            return this;
        }

        public TrafficLight build() {
            return new TrafficLight(this);
        }

    }
}
