import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        Semaphore numArmas = new Semaphore(5);

        // Lista para armazenar os resultados
        List<Resultado> resultados = Collections.synchronizedList(new ArrayList<>());

        // Criar as threads
        Thread[] competidores = new Thread[3];
        for (int x = 0; x < 3; x++) {
            competidores[x] = new Prova(x, numArmas, resultados);
            competidores[x].start();
        }

        // Esperar todos terminarem
        for (Thread t : competidores) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Ordenar do maior para o menor
        resultados.sort(Comparator.comparingInt(r -> -r.pontos));

        // Mostrar ranking final
        System.out.println("\n=== Ranking Final ===");
        for (int i = 0; i < resultados.size(); i++) {
            System.out.println((i+1) + "º lugar -> " + resultados.get(i));
        }
    }
}