public class Resultado {
    int id;
    int pontos;

    public Resultado(int id, int pontos) {
        this.id = id;
        this.pontos = pontos;
    }

    @Override
    public String toString() {
        return "Competidor " + id + " - Pontos: " + pontos;
    }
}