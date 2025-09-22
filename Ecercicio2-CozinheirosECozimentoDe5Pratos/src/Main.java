import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        int pratos = 5;
        Semaphore entregaSem = new Semaphore(1, true);
        for (int i = 1; i <= pratos; i++) {
            Prato p = new Prato(i, entregaSem);
            p.start();
        }
    }
}