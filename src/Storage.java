import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Storage implements Queue {
    private int [] box = new int[10];
    private int start = 9;
    private int end = 0;
    private Lock storageLock;
    private Condition full;
    private Condition empty;
    public boolean wasConsumed = true; //Если последнее дейтсвие извлечение - true, иначе false

    Storage() {
        storageLock = new ReentrantLock();
        full = storageLock.newCondition();
        empty = storageLock.newCondition();
    }

    //Добавление элемента
    public void put(int val) throws InterruptedException {
        storageLock.lock();
        try {
            while (full()) full.await();
                start = step(start);
            if (wasConsumed) {
                val++;
            }
                box[start] = val;
                print();
                empty.signalAll();
        } finally {
            storageLock.unlock();
        }
    }

    //Извлечение элемента
    public int get() throws InterruptedException{
        storageLock.lock();
        try {
            while (empty()) empty.await();
            int x = box[end];
            end = step(end);
            print();
            full.signalAll();
            wasConsumed = true;
            return x;
        } finally {
            storageLock.unlock();
        }
    }

    //Места нет
    public boolean full() {
        return step(step(start)) == end;
    }

    //Пусто
    public boolean empty() {
        return step(start) == end;
    }

    //Сдвинуть индекс на 1 в очереди
    private int step(int val) {
        return (val + 1) % 10;
    }

    //Напечатать
    private void print(){
        for (int i = end; i != step(start); i = step(i)) System.out.print(box[i] + " ");
        System.out.println("");
    }
}
