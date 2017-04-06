package control_classes;

import java.util.TimerTask;

/**
 * Created by Jilada on 5/04/17.
 */
public class ClockTick extends TimerTask {
    // This class will set the clock and tick when something occurs
    public int currentTime;

    public void run() {
        this.currentTime = this.currentTime + 1;
    }

    public void resetCurrentTime() {
        this.currentTime = 0;
    }

    public int getCurrentTime() {
        return this.currentTime;
    }
}
