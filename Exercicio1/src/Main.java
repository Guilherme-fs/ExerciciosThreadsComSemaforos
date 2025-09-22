import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {

        Semaphore semaforoParaTansacaoComBD = new Semaphore(1);

        for (int i = 1; i <= 21; i++) {
            Thread t = new ThreadDoExercicio(i, semaforoParaTansacaoComBD);
            t.start();
        }
    }
}