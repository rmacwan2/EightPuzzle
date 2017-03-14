/**
 * Created by Ryan on 1/16/2017.
 */
public class StopWatch {
    private long startTime;

    StopWatch() {
        startTime = System.currentTimeMillis();
    }

    public long stopStopWatch() {
        return System.currentTimeMillis() - startTime;
    }
}
