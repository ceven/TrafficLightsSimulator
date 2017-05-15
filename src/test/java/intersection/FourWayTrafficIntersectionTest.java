package intersection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;

public class FourWayTrafficIntersectionTest {

    private FourWayTrafficIntersection intersection;

    @Before
    public void setUp() {
        intersection = new FourWayTrafficIntersection();
    }

    @Test
    public void firstLightShouldBeNorthWestLight() {
        Assert.assertNotNull(intersection.getNorthSouthLights());
        if  (intersection.getFirstSimulationLight().isPresent()){
            Assert.assertEquals(intersection.getNorthSouthLights(), intersection.getFirstSimulationLight().get());
        }
        else {
            fail("No first light configured for FourWayTrafficIntersection");
        }
    }
}
