import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Timer;
import java.util.TimerTask;

public class DiningPhilosophers {
    public static void main(String[] args) {
        int philosopherCount = 5;
        Philosopher[] philosophers = new Philosopher[philosopherCount];
        Lock[] forks = new ReentrantLock[philosopherCount];
        SynchronizeShow synchronizeShow = new SynchronizeShow(philosopherCount);

        for (int i = 0; i < philosopherCount; i++) {
            forks[i] = new ReentrantLock();
        }

        for (int i = 0; i < philosopherCount; i++) {
            Lock leftFork = forks[i];
            Lock rightFork = forks[(i + 1) % philosopherCount];
            philosophers[i] = new Philosopher(i, leftFork, rightFork, synchronizeShow);
            philosophers[i].start();
        }

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                showingOfTheStatus(synchronizeShow, philosopherCount);
            }
        }, 0, 1000);

        for (Philosopher philosopher : philosophers) {
            try {
                philosopher.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private static void showingOfTheStatus(SynchronizeShow synchronizeShow, int philosopherCount) {
        boolean[] eating = synchronizeShow.Eating();
        boolean[] hasLeftFork = synchronizeShow.hasLFork();
        boolean[] hasRightFork = synchronizeShow.hasRFork();
        int[] eatCounts = synchronizeShow.hasEatingStatusCount();
        int[] thinkCounts = synchronizeShow.hasThinkingStatusCount();

        for (int i = 0; i < philosopherCount; i++) {
            System.out.println("Philosopher " + i + ": "
                    + (eating[i] ? "Eating" : "Thinking")
                    + " --- Right Fork: " + (hasRightFork[i] ? "Yes" : "No")
                    + " --- Left Fork: " + (hasLeftFork[i] ? "Yes" : "No")
                    + " --- Think Count: " + thinkCounts[i]
                    + " --- Eat Count: " + eatCounts[i]);
        }
        System.out.println("-------------------------------");
    }
}
