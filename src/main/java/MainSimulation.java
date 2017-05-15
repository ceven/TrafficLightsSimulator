import intersection.FourWayTrafficIntersection;

import java.util.concurrent.TimeUnit;

/**
 * The class used to run the traffic simulation as per the problem description
 */
public class MainSimulation {

    public static void main(String[] args) {
        FourWayTrafficIntersection intersection = new FourWayTrafficIntersection();
        intersection.startSimulation();
        intersection.stopSimulationIn(30, TimeUnit.MINUTES);
    }
}
