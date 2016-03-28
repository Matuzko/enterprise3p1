package enterprise3p1;


public class SemaphoreImpl implements Semaphore {

    int permits;
    int availablePermits;
    final private Object lock;

    public SemaphoreImpl(int permits) {
        this.permits = permits;
        this.availablePermits = permits;
        lock = new Object();
    }

    @Override
    public void acquire() throws InterruptedException {
        if (this.availablePermits >= permits) {

            this.availablePermits--;

        } else {
            synchronized (lock) {
                lock.wait();
            }
        }
    }

    @Override
    public void acquire(int permits) throws InterruptedException {
        if ((this.permits - this.availablePermits) >= permits) {
            this.availablePermits = this.availablePermits - permits;
        }else {
            synchronized (lock){
                lock.wait();
            }
        }
    }

    @Override
    public void release() {
        if (!(this.availablePermits <= this.permits)) {
            this.permits++;
        }
    }

    @Override
    public void release(int permits) {
        if (permits < (this.availablePermits - this.permits)) {
            this.permits+= permits;
        }
    }

    @Override
    public int getAvailablePermits() {
        return this.availablePermits - this.permits;
    }
}
