public class Consumer implements Runnable {
    Storage storage;

    Consumer(Storage storage) {
        this.storage = storage;
        new Thread(this, "Consumer").start();
    }

    public void run() {
        try {
            while (true) {
                storage.get();
            }
        } catch (InterruptedException e) {

        }
    }
}
