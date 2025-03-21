package diningphilosophers;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ChopStick {

    private Lock csLock = new ReentrantLock();
    private static int stickCount = 0;
    private final int myNumber;

    public ChopStick() {
        myNumber = ++stickCount;
    }

    public boolean tryTake(int delay) throws InterruptedException {
        if (!csLock.tryLock(delay, TimeUnit.MILLISECONDS)) {
            return false; // Echec
        }
        // Successfully locked chopstick
        return true; // Succès
    }

    public void release() {
        csLock.unlock();
        System.out.println("Stick " + myNumber + " Released");
    }

    @Override
    public String toString() {
        return "Stick#" + myNumber;
    }
}
