package project;

public class StreamSemaphore {
    private Boolean canRead;
    private Object lock = new Object();

    public StreamSemaphore() {
        canRead = true;
    }

    public synchronized void lockForWrite() {
        canRead = false;
    }

    public synchronized void relaseWriteLock() {
        canRead = true;
        notifyAll();
    }

    public synchronized void waitToRead() {
        try {
            while (!canRead)
                wait();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
