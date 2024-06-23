import java.util.Arrays;

class SynchronizeShow {
    private final boolean[] isEating;
    private final boolean[] hasLeftFork;
    private final boolean[] hasRightFork;
    private final int[] eatCount;
    private final int[] thinkCount;
    public SynchronizeShow(int philosopherCount) {
        isEating = new boolean[philosopherCount];
        hasLeftFork = new boolean[philosopherCount];
        hasRightFork = new boolean[philosopherCount];
        eatCount = new int[philosopherCount];
        thinkCount = new int[philosopherCount];
    }
    public synchronized void philEats(int id, boolean eating) {
        isEating[id] = eating;
    }
    public synchronized void philLFork(int id, boolean hasFork) {
        hasLeftFork[id] = hasFork;
    }
    public synchronized void philRFork(int id, boolean hasFork) {
        hasRightFork[id] = hasFork;
    }
    public synchronized void incrEatStatus(int id) {
        eatCount[id]++;
    }
    public synchronized void incrThinkStatus(int id) {
        thinkCount[id]++;
    }
    public synchronized boolean[] Eating() {
        return Arrays.copyOf(isEating, isEating.length);
    }
    public synchronized boolean[] hasLFork() {
        return Arrays.copyOf(hasLeftFork, hasLeftFork.length);
    }
    public synchronized boolean[] hasRFork() {
        return Arrays.copyOf(hasRightFork, hasRightFork.length);
    }
    public synchronized int[] hasEatingStatusCount() {
        return Arrays.copyOf(eatCount, eatCount.length);
    }
    public synchronized int[] hasThinkingStatusCount() {
        return Arrays.copyOf(thinkCount, thinkCount.length);
    }
}
