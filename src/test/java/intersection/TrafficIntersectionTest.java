package intersection;

import light.SynchronisedTrafficLight;
import org.junit.Before;
import org.junit.Test;
import state.State;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TrafficIntersectionTest {

    private TrafficIntersection trafficIntersection;

    @Before
    public void setUp() {
        trafficIntersection = new TrafficIntersection();
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowErrorWhenStartingSimulationWithNoLights() {
        trafficIntersection.startSimulation();
    }

    @Test
    public void shouldStartSimulationWithOneLight() {
        SynchronisedTrafficLight synchronisedLight = mock(SynchronisedTrafficLight.class);
        trafficIntersection.addSynchronisedTrafficLight(synchronisedLight);

        trafficIntersection.startSimulation();

        verify(synchronisedLight).updateStateAndNotify(State.GREEN_LIGHTS);
    }

}
