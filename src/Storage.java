
public class Storage {
    int [] box = new int[10];
    int start = 9;
    int end = 0;
    //boolean key = true;

    Storage() {}

    void put(int val){
        start = (start + 1) % 10;
        box[start] = val;
        print();
    }

    int get(){
        int x = box[end];
        end = (end + 1) % 10;
        print();
        return x;
    }

    boolean full() {
        return ((end - start) % 9) == 2;
    }

    boolean empty() {
        return ((end - start) % 9) == 1;
    }

    private void print(){
        if (start >= end) {
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
