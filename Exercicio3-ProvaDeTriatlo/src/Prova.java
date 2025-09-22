import java.util.List;
import java.util.concurrent.Semaphore;

public class Prova extends Thread{

    private int id;
    private Semaphore numArmas;
    private int pontuacaoFinal;
    private static int pontuacaoOrdemDeChegada = 250;
    private List<Resultado> resultados;

    public Prova(int id, Semaphore numArmas, List<Resultado> resultados){
        this.id = id;
        this.numArmas = numArmas;
        this.resultados = resultados;
    }

    @Override
    public void run() {
        int distanciaTotalPercursoCorrida = 300;
        int numDisparosNoAoAlvo = 3;
        int distanciaPercursoCiclismo = 500;
        int distanciaPercorridaNaCorrida=0;
        int pontosTiroAoAlvo=0;
        int distanciaPercorridaNoCiclismo=0;

        //Corrida
        while(distanciaPercorridaNaCorrida<distanciaTotalPercursoCorrida){
            try {
                distanciaPercorridaNaCorrida += (int) Math.random()*(25-20)+20;
                System.out.println("competidor "+id+" percorreu "+distanciaPercorridaNaCorrida+" na corrida");
                sleep(30);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("competidor "+id+" terminou a corrida ");

        //Tiro ao alvo
        for (int qtdDisparos=0; qtdDisparos<numDisparosNoAoAlvo; qtdDisparos++){
            try {
                numArmas.acquire();
                pontosTiroAoAlvo+=(int) (Math.random()*10)+1;
                sleep((long) Math.random()*(3000-500)+500);//Tempo de disparo é de 0,5 a 3s
                System.out.println("competidor "+id+" obteve "+pontosTiroAoAlvo+" pontos no tiro ao alvo");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }finally {
                numArmas.release();
            }
        }
        System.out.println("competidor "+id+" terminou o tiro ao alvo");

        //Ciclismo
        while(distanciaPercorridaNoCiclismo<distanciaPercursoCiclismo){
            try {
                distanciaPercorridaNoCiclismo += (int) Math.random()*(40-30)+30;
                System.out.println("competidor "+id+" percorreu "+distanciaPercorridaNoCiclismo+" no ciclismo");
                sleep(40);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("competidor "+id+" terminou o ciclismo");

        pontuacaoFinal=(pontuacaoOrdemDeChegada+pontosTiroAoAlvo);

        // Salva resultado na lista compartilhada
        resultados.add(new Resultado(id, pontuacaoFinal));
    }
}
