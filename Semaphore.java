package enterprise3p1;

/**
 * Created by Дима on 24.03.2016.
 */
public interface Semaphore {
    public void acquire() throws InterruptedException;
    public void acquire(int permits) throws InterruptedException;
    public void release();
    public void release(int permits);
    public int getAvailablePermits();
}
