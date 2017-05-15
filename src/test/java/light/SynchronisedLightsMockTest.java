package light;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import state.State;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class SynchronisedLightsMockTest {

    private SynchronisedTrafficLight synchronisedTrafficLight;

    @Before
    public void setUp(){
        synchronisedTrafficLight = Mockito.spy(new SynchronisedTrafficLight());
    }

    @Test
    public void shouldNotifySelfWhenUpdatingStateToGreen() {
        State newState = State.GREEN_LIGHTS;
        synchronisedTrafficLight.updateStateAndNotify(newState);
        verify(synchronisedTrafficLight).scheduleColourChange(newState);
    }

    @Test
    public void shouldNotifySelfWhenUpdatingStateToYellow() {
        State newState = State.YELLOW_LIGHTS;
        synchronisedTrafficLight.updateStateAndNotify(newState);
        verify(synchronisedTrafficLight).scheduleColourChange(newState);
    }

    @Test
    public void shouldNotifySelfWhenUpdatingStateToRedWithNoObservingLights() {
        Assert.assertEquals(0,synchronisedTrafficLight.getObservingLights().size());
        State newState = State.RED_LIGHTS;
        synchronisedTrafficLight.updateStateAndNotify(newState);
        verify(synchronisedTrafficLight).scheduleColourChange(newState);
    }

    @Test
    public void shouldNotifyObservingLightsWhenUpdatingStateToRed() {
        SynchronisedTrafficLight observingLight = mock(SynchronisedTrafficLight.class);
        synchronisedTrafficLight.addObservingLight(observingLight);
        Assert.assertEquals(1,synchronisedTrafficLight.getObservingLights().size());
        State newState = State.RED_LIGHTS;
        synchronisedTrafficLight.updateStateAndNotify(newState);
        verify(synchronisedTrafficLight, times(0)).scheduleColourChange(newState);
        verify(observingLight).scheduleColourChange(newState);
    }
}
