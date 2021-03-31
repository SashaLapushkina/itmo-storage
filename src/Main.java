public class Main {
    public static void main(String[] args) {
        Storage stor = new Storage();
        new Producer(stor);
        new Consumer(stor);
    }
}
