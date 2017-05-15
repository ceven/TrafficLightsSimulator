package light;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import state.State;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(MockitoJUnitRunner.class)
public class SynchronisedLightsMockTest {

    @Spy
    @InjectMocks
    private SynchronisedTrafficLight synchronisedTrafficLight;

    @Mock
    private ScheduledExecutorService scheduledExecutorService;

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

    @Test
    public void shouldCallTaskSchedulerWhenChangingColour() {
        final State newState = State.GREEN_LIGHTS;
        synchronisedTrafficLight.scheduleColourChange(newState);
        verify(scheduledExecutorService).schedule(
                any(Runnable.class), eq(newState.getDuration().getSeconds()), eq(TimeUnit.SECONDS)
        );
    }

    @Test
    public void taskSchedulerShouldShutdownNowWhenStoppingLights() {
        synchronisedTrafficLight.stopLights();
        verify(scheduledExecutorService).shutdownNow();
        verifyNoMoreInteractions(scheduledExecutorService);
    }
}
