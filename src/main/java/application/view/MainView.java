package application.view;

import application.model.TrafficLightsModel;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class MainView {

    private BorderPane rootPane;
    private SimulationControlView simulationControlView;
    private TrafficLightsView trafficLightsView;

    public MainView(final TrafficLightsModel trafficLightsModel) {
        rootPane = new BorderPane();
        simulationControlView = new SimulationControlView();
        trafficLightsView = new TrafficLightsView(trafficLightsModel);
        rootPane.setTop(simulationControlView.getPane());
        rootPane.setCenter(trafficLightsView.getPane());
    }

    public Pane getRootPane() {
        return rootPane;
    }

    public SimulationControlView getSimulationControlView() {
        return simulationControlView;
    }

    public TrafficLightsView getTrafficLightsView() {
        return trafficLightsView;
    }
}
