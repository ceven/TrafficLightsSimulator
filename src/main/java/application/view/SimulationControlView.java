package application.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import static application.TrafficLightsApp.SCENE_HEIGHT;
import static application.TrafficLightsApp.SCENE_WIDTH;

public class SimulationControlView {

    private HBox pane;
    private Button start, stop;

    public SimulationControlView() {
        pane = createHBoxPane();
        start = createStartSimulationButton();
        stop = createResetSimulationButton();
        pane.getChildren().addAll(start, stop);
    }

    private HBox createHBoxPane() {
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(20, 10, 10, 10));
        return hBox;
    }

    public Pane getPane() {
        return pane;
    }

    public Button getStart() {
        return start;
    }

    public Button getStop() {
        return stop;
    }

    private Button createStartSimulationButton() {
        Button start = new Button("Start simulation");
        start.setPrefSize(SCENE_WIDTH / 2, SCENE_HEIGHT / 15);
        return start;
    }

    private Button createResetSimulationButton() {
        Button stop = new Button("Stop simulation");
        stop.setPrefSize(SCENE_WIDTH / 2, SCENE_HEIGHT / 15);
        stop.setDisable(true);
        return stop;
    }

}
