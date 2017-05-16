package application.controller;

import application.model.TrafficLightsModel;
import application.view.SimulationControlView;

import java.util.concurrent.TimeUnit;

public class SimulationController {

    private final TrafficLightsModel model;
    private SimulationControlView view;
    private boolean initialised = false;

    public SimulationController(TrafficLightsModel model, SimulationControlView view) {
        this.view = view;
        this.model = model;
    }

    public void init() {
        if (!initialised) {
            view.getStart().setOnAction(event -> {
                model.getTrafficIntersection().startSimulation();
                view.getStart().setDisable(true);
                view.getStop().setDisable(false);
            });
            view.getStop().setOnAction(event -> {
                model.getTrafficIntersection().stopSimulationIn(0, TimeUnit.SECONDS);
                view.getStop().setDisable(true);
                view.getStart().setDisable(false);
            });
            initialised = true;
        }
    }
}
