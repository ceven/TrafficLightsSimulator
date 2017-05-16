package application;

import application.controller.SimulationController;
import application.controller.TrafficLightsController;
import application.model.TrafficLightsModel;
import application.view.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import state.State;

import java.time.temporal.ChronoUnit;

public class TrafficLightsApp extends Application {

    public static final double SCENE_WIDTH = 400;
    public static final double SCENE_HEIGHT = 350;

    @Override
    public void start(Stage primaryStage) throws Exception {
        State.GREEN_LIGHTS.setDuration(2, ChronoUnit.SECONDS);
        State.YELLOW_LIGHTS.setDuration(1, ChronoUnit.SECONDS);
        State.RED_LIGHTS.setDuration(500, ChronoUnit.MILLIS);
        primaryStage.setTitle("Traffic light intersection");
        TrafficLightsModel model = new TrafficLightsModel();
        MainView mainView = new MainView(model);
        TrafficLightsController trafficLightsController = new TrafficLightsController(model, mainView.getTrafficLightsView());
        trafficLightsController.init();
        SimulationController simulationController = new SimulationController(model, mainView.getSimulationControlView());
        simulationController.init();
        primaryStage.setScene(new Scene(mainView.getRootPane(), SCENE_WIDTH, SCENE_HEIGHT));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
