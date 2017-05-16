package application.controller;

import application.model.TrafficLightsModel;
import application.view.TrafficLightFxComponent;
import application.view.TrafficLightsView;
import light.TrafficLight;

import static application.ModelMappingUtilities.getColor;

public class TrafficLightsController {

    private TrafficLightsModel model;
    private TrafficLightsView view;
    private boolean initialised;

    public TrafficLightsController(final TrafficLightsModel model, final TrafficLightsView view) {
        this.model = model;
        this.view = view;
        this.initialised = false;
    }

    public void init() {
        if (!initialised) {
            addLightObserver(model.getNorthLight(), view.getNorthLight());
            addLightObserver(model.getSouthLight(), view.getSouthLight());
            addLightObserver(model.getEastLight(), view.getEastLight());
            addLightObserver(model.getWestLight(), view.getWestLight());
            initialised = true;
        }
    }

    private void addLightObserver(final TrafficLight trafficLight,
                                  final TrafficLightFxComponent trafficLightFxComponent) {
        trafficLight.getColourProperty().addListener((observable, oldValue, newValue) ->
                trafficLightFxComponent.getLight().setFill(getColor(newValue)));

    }
}
