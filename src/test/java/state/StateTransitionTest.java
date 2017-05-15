package state;

import org.junit.Assert;
import org.junit.Test;

import static state.State.GREEN_LIGHTS;
import static state.State.RED_LIGHTS;
import static state.State.YELLOW_LIGHTS;

public class StateTransitionTest {

    @Test
    public void yellowLightsShouldBeNextStateOfGreenLights() {
        Assert.assertEquals(YELLOW_LIGHTS, GREEN_LIGHTS.getNextState());
    }

    @Test
    public void redLightsShouldBeNextStateOfYellowLights() {
        Assert.assertEquals(RED_LIGHTS, YELLOW_LIGHTS.getNextState());
    }

    @Test
    public void greenLightsShouldBeNextStateOfRedLights() {
        Assert.assertEquals(GREEN_LIGHTS, RED_LIGHTS.getNextState());
    }
}
