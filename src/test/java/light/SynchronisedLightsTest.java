package light;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import state.State;

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

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotUpdateStateWhenStateIsNull() {
        synchronisedTrafficLight.updateStateAndNotify(null);
    }

    @Test
    public void shouldUpdateStateFromRedToGreen() {
        synchronisedTrafficLight.addRedLight(Direction.NORTH);
        Assert.assertEquals(Colour.RED, synchronisedTrafficLight.getColour());
        synchronisedTrafficLight.updateStateAndNotify(State.GREEN_LIGHTS);
        Assert.assertEquals(Colour.GREEN, synchronisedTrafficLight.getColour());
    }

}
