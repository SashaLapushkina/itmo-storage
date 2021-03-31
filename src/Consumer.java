public class Consumer implements Runnable {
    Storage stor;

    Consumer(Storage stor) {
        this.stor = stor;
        new Thread(this, "Consumer").start();
    }

    public void run() {
        while (!stor.empty())
            stor.get();
    }
}
