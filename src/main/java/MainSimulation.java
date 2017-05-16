import intersection.FourWayTrafficIntersection;

import java.util.concurrent.TimeUnit;

/**
 * Use this class to run the traffic simulation as per the problem description
 * To modify the lights duration, call `setDuration(...)` on the corresponding State enum prior to
 * starting the simulation: e.g. GREEN_LIGHTS.setDuration(5, ChronoUnit.SECONDS);
 */
public class MainSimulation {

    public static void main(String[] args) {
        FourWayTrafficIntersection intersection = new FourWayTrafficIntersection();
        intersection.startSimulation();
        intersection.stopSimulationIn(30, TimeUnit.MINUTES);
    }
}
