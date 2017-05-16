package application.view;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import light.TrafficLight;

import static application.ModelMappingUtilities.getColor;
import static application.TrafficLightsApp.SCENE_HEIGHT;

public class TrafficLightFxComponent {

    private final Shape light;
    private final Text text;
    private final Pane pane;

    public TrafficLightFxComponent(final TrafficLight trafficLight) {
        light = new Circle(SCENE_HEIGHT / 10, getColor(trafficLight.getColour()));
        text = new Text(trafficLight.getDirection().name());
        text.setBoundsType(TextBoundsType.VISUAL);
        pane = new StackPane();
        pane.getChildren().addAll(light, text);
    }

    public Shape getLight() {
        return light;
    }

    public Text getText() {
        return text;
    }

    public Pane getPane() {
        return pane;
    }
}
