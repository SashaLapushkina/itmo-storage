public class Producer implements Runnable {
    Storage storage;

    Producer(Storage storage) {
        this.storage = storage;
        new Thread(this, "Producer").start();
    }

    public void run() {
        try {
            int val = 0; //Добавляемое значение
            while (true) {
                if (storage.wasConsumed) {
                    val++; //Увеличиваем, если элемент был извлечен
                    storage.wasConsumed = false;
                }
                storage.put(val);
            }
        } catch (InterruptedException e) {

        }
    }
}
