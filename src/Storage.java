import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Storage {
    private int [] box = new int[10];
    private int start = 9;
    private int end = 0;
    private Lock storageLock;
    private Condition condition;
    public boolean wasConsumed = true;

    Storage() {
        storageLock = new ReentrantLock();
        condition = storageLock.newCondition();
    }

    public void put(int val) throws InterruptedException {
        storageLock.lock();
        try {
            while (full()) condition.await();
                start = (start + 1) % 10;
            if (wasConsumed) {
                val++;
            }
                box[start] = val;
                print();
                condition.signalAll();
        } finally {
            storageLock.unlock();
        }
    }

    public int get() throws InterruptedException{
        storageLock.lock();
        try {
            while (empty()) condition.await();
            int x = box[end];
            end = (end + 1) % 10;
            print();
            condition.signalAll();
            wasConsumed = true;
            return x;
        } finally {
            storageLock.unlock();
        }
    }

    private boolean full() {
        return ((10 + end - start) % 10) == 2;
    }

    private boolean empty() {
        return ((10 + end - start) % 10) == 1;
    }

    private void print(){
        if (empty()) System.out.println("");
        else if (start >= end) {
            for (int i = end; i <= start; i++) System.out.print(box[i] + " ");
            System.out.println();
        }
        else {
            for (int i = end; i <= 9; i++) System.out.print(box[i] + " ");
            for (int i = 0; i <= start; i++) System.out.print(box[i] + " ");
            System.out.println();
        }
    }
}
