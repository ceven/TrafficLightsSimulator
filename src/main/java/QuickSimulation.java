import intersection.FourWayTrafficIntersection;

import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

import static state.State.GREEN_LIGHTS;
import static state.State.RED_LIGHTS;
import static state.State.YELLOW_LIGHTS;

public class QuickSimulation {

    public static void main(String[] args) {
        GREEN_LIGHTS.setDuration(5, ChronoUnit.SECONDS);
        YELLOW_LIGHTS.setDuration(3, ChronoUnit.SECONDS);
        RED_LIGHTS.setDuration(1, ChronoUnit.SECONDS);
        FourWayTrafficIntersection intersection = new FourWayTrafficIntersection();
        intersection.startSimulation();
        intersection.stopSimulationIn(30, TimeUnit.SECONDS);
    }

}
