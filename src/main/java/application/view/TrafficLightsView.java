package application.view;

import application.model.TrafficLightsModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class TrafficLightsView {

    private TrafficLightFxComponent northLight, southLight, eastLight, westLight;
    private TrafficLightsModel model;
    private Pane pane;

    public TrafficLightsView(TrafficLightsModel trafficLightsModel) {
        this.model = trafficLightsModel;
        northLight = new TrafficLightFxComponent(model.getNorthLight());
        eastLight = new TrafficLightFxComponent(model.getEastLight());
        southLight = new TrafficLightFxComponent(model.getSouthLight());
        westLight = new TrafficLightFxComponent(model.getWestLight());

        pane = createLightIntersection(northLight, eastLight, southLight, westLight);
    }

    private Pane createLightIntersection(final TrafficLightFxComponent nLight, final TrafficLightFxComponent eLight,
                                         final TrafficLightFxComponent sLight, final TrafficLightFxComponent wLight) {
        GridPane pane = new GridPane();
        pane.add(nLight.getPane(), 2, 1);
        pane.add(wLight.getPane(), 1, 2);
        pane.add(eLight.getPane(), 3, 2);
        pane.add(sLight.getPane(), 2, 3);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(20, 10, 20, 10));
        return pane;
    }

    public Pane getPane() {
        return pane;
    }

    public TrafficLightFxComponent getNorthLight() {
        return northLight;
    }

    public TrafficLightFxComponent getSouthLight() {
        return southLight;
    }

    public TrafficLightFxComponent getEastLight() {
        return eastLight;
    }

    public TrafficLightFxComponent getWestLight() {
        return westLight;
    }

}
