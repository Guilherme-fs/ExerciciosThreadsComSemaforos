import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Prato extends Thread {
    private final int id;
    private final Semaphore entrega;
    private final String nome;
    private final double tempoTotalSeg;

    Prato(int id, Semaphore entrega) {
        this.id = id;
        this.entrega = entrega;
        this.nome = (id % 2 == 1) ? "Sopa de Cebola" : "Lasanha à Bolonhesa";
        if (id % 2 == 1) {
            this.tempoTotalSeg = ThreadLocalRandom.current().nextDouble(0.5, 0.8);
        } else {
            this.tempoTotalSeg = ThreadLocalRandom.current().nextDouble(0.6, 1.2);
        }
        setName("Prato-" + id);
    }

    @Override
    public void run() {
        try {
            System.out.printf("[%s] Iniciou %s (id=%d). Tempo para finalizar: %.3fs%n", getName(), nome, id, tempoTotalSeg);
            long tempoTotalMs = Math.round(tempoTotalSeg * 1000);
            long passoMs = 100; // 0.1s
            long elapsed = 0;
            while (elapsed < tempoTotalMs) {
                Thread.sleep(passoMs);
                elapsed += passoMs;
                double perc = Math.min(100.0, (elapsed / (double) tempoTotalMs) * 100.0);
                System.out.printf("[%s] Cozimento %.1f%% (%d/%d ms)%n", getName(), perc, elapsed, tempoTotalMs);
            }
            System.out.printf("[%s] PRONTO: %s (id=%d)%n", getName(), nome, id);

            // Entrega (apenas 1 por vez)
            entrega.acquire();
            try {
                System.out.printf("[%s] Iniciando ENTREGA do prato id=%d (duração 0.5s)%n", getName(), id);
                Thread.sleep(500);
                System.out.printf("[%s] ENTREGA CONCLUÍDA id=%d%n", getName(), id);
            } finally {
                entrega.release();
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
