public class Producer implements Runnable {
    Storage stor;
    static int val = 0;

    Producer(Storage stor) {
        this.stor = stor;
        new Thread(this, "Producer").start();
    }

    public void run() {
        val++;
        while (!stor.full())
            stor.put(val);
    }
}
