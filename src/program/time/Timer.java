package program.time;

public class Timer {
    private long last_time = System.nanoTime();
    private final long delay;

    public Timer(long delay) {
        this.delay = delay;
    }

    public long getDelay() {
        return this.delay;
    }

    public boolean checkDelay() {
        long current_time = System.nanoTime();

        if (current_time - last_time >= delay) {
            last_time = current_time;

            return true;
        }

        return false;
    }

    public static long getDelay(long tps) {
        return 1_000_000_000 / tps;
    }

    public static long getDelayInSeconds(float sec) {
        return (long)(1_000_000_000 * sec);
    }
}
