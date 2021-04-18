import java.util.Random;

public class Consumer implements Runnable {
    Storage storage;

    Consumer(Storage storage) {
        this.storage = storage;
        new Thread(this, "Consumer").start();
    }

    public void run() {
        try {
            while (true) {
                Random random = new Random();
                for (int i = random.nextInt() % 5; i > 0; i--)
                    storage.get();
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {

        }
    }
}
