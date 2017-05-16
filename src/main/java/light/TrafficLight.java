package light;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;

public final class TrafficLight {

    private final Direction direction;
    private Property<Colour> colour;

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
        this.colour = new SimpleObjectProperty<>(builder.builderColour);
    }

    public Direction getDirection() {
        return direction;
    }

    public Property<Colour> getColourProperty() {
        return colour;
    }

    public Colour getColour() {
        return colour.getValue();
    }

    public void setColour(Colour colour) {
        this.colour.setValue(colour);
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
