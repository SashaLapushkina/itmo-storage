public class Main {
    public static void main(String[] args) {
        Storage storage = new Storage();
        new Producer(storage);
        new Consumer(storage);
        new Consumer(storage);
    }
}
