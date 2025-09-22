import java.util.concurrent.Semaphore;

public class ThreadDoExercicio extends Thread {

    private int id;
    private static Semaphore semaforoParaTransacaoComBD;

    public ThreadDoExercicio(int id, Semaphore semaforoParaTransacaoComBD) {
        this.id = id;
        ThreadDoExercicio.semaforoParaTransacaoComBD = semaforoParaTransacaoComBD;
    }

    @Override
    public void run() {
        try {
            int qtdOperacoes;
            long tempoTransacao;
            int minCalculo, maxCalculo;

            if (id % 3 == 1) {
                qtdOperacoes = 2;
                tempoTransacao = 1000;
                minCalculo = 200;
                maxCalculo = 1000;
            } else if (id % 3 == 2) {
                qtdOperacoes = 3;
                tempoTransacao = 1500;
                minCalculo = 500;
                maxCalculo = 1500;
            } else {
                qtdOperacoes = 3;
                tempoTransacao = 1500;
                minCalculo = 1000;
                maxCalculo = 2000;
            }

            for (int i = 1; i <= qtdOperacoes; i++) {
                // cálculo
                long tempoCalculo = (long) (Math.random() * (maxCalculo - minCalculo) + minCalculo);
                System.out.println("Thread " + id + " fazendo o " + i + "° cálculo");
                sleep(tempoCalculo);

                // transação
                semaforoParaTransacaoComBD.acquire();
                System.out.println("Thread " + id + " fazendo a " + i + "° transação de banco de dados");
                sleep(tempoTransacao);
                semaforoParaTransacaoComBD.release();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
