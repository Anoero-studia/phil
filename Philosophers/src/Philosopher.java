import java.util.concurrent.locks.Lock;

class Philosopher extends Thread {
    private final int id;
    private final Lock lFork;
    private final Lock rFork;
    private final SynchronizeShow synchronizeShow;

    public Philosopher(int id, Lock lFork, Lock rFork, SynchronizeShow synchronizeShow) {
        this.id = id;
        this.lFork = lFork;
        this.rFork = rFork;
        this.synchronizeShow = synchronizeShow;
    }
    @Override
    public void run() {
        try {
            while (true) {
                thinkingWork();
                dealWithForks();
                eatingWork();
                setDownForks();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    private void thinkingWork() throws InterruptedException {
        synchronizeShow.philEats(id, false);
        synchronizeShow.incrThinkStatus(id);
        Thread.sleep((long) (Math.random() * 1000));
    }
    private void dealWithForks() {
        lFork.lock();
        synchronizeShow.philLFork(id, true);
        rFork.lock();
        synchronizeShow.philRFork(id, true);
    }
    private void setDownForks() {
        lFork.unlock();
        synchronizeShow.philLFork(id, false);
        rFork.unlock();
        synchronizeShow.philRFork(id, false);
    }
    private void eatingWork() throws InterruptedException {
        synchronizeShow.philEats(id, true);
        synchronizeShow.incrEatStatus(id);
        Thread.sleep((long) (Math.random() * 1000));
    }
}
