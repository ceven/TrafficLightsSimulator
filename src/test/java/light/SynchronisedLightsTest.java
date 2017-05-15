package light;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SynchronisedLightsTest {

    private SynchronisedTrafficLight synchronisedTrafficLight;

    @Before
    public void setUp() {
        synchronisedTrafficLight = new SynchronisedTrafficLight();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAddTrafficLightWithNullDirection() {
        synchronisedTrafficLight.addRedLight(null);
    }

    @Test
    public void shouldAddTrafficLightWithDirection() {
        synchronisedTrafficLight.addRedLight(Direction.NORTH);
        Assert.assertEquals(1, synchronisedTrafficLight.getTrafficLights().size());
        TrafficLight light = synchronisedTrafficLight.getTrafficLights().get(0);
        Assert.assertEquals(Direction.NORTH, light.getDirection());
        Assert.assertEquals(Colour.RED, light.getColour());
    }
}
