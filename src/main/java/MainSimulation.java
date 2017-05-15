import intersection.FourWayTrafficIntersection;

import java.util.concurrent.TimeUnit;

public class MainSimulation {

    public static void main(String[] args) {
        FourWayTrafficIntersection intersection = new FourWayTrafficIntersection();
        intersection.startSimulation();
        intersection.stopSimulationIn(30, TimeUnit.MINUTES);
    }
}
