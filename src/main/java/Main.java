import intersection.FourWayTrafficIntersection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        LOG.info("Starting Traffic Lights Simulation");
        FourWayTrafficIntersection intersection = new FourWayTrafficIntersection();
        intersection.startSimulation();
    }
}
