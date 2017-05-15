package util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class SchedulerFactory {

    public static ScheduledExecutorService createTaskScheduler() {
        return Executors.newScheduledThreadPool(1);
    }

}
