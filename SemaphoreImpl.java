package enterprise3p1;


public class SemaphoreImpl implements Semaphore {

    private int  permits;
    private int availablePermits;
    final private Object lock;


    public SemaphoreImpl(int permits) {
        this.permits = permits;
        this.availablePermits = permits;
        lock = new Object();
    }

    @Override
    public synchronized void acquire() throws InterruptedException {
        if (this.availablePermits > 0) {

            this.availablePermits--;

        } else {
            synchronized (lock) {
                lock.wait();
            }
        }
    }

    @Override
    public synchronized void acquire(int permits) throws InterruptedException {
        if (this.availablePermits >= permits) {
            this.availablePermits -= permits;
        }else {
            synchronized (lock){
                lock.wait();
            }
        }
    }

    @Override
    public synchronized void release() {
        if (this.permits > this.availablePermits) {
            this.availablePermits++;
            lock.notifyAll();
        }
    }

    @Override
    public synchronized void  release(int permits) {
        if ((this.permits - this.availablePermits) >= permits) {
            this.availablePermits+= permits;
            lock.notifyAll();
        }
        else {
            this.availablePermits = this.permits;
        }
    }

    @Override
    public int getAvailablePermits() {
        return this.availablePermits;
    }
}
