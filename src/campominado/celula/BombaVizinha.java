package campominado.celula;

public class BombaVizinha extends Celula {

    private int bombasVizinhas; // Número de bombas vizinhas

    // Construtor da classe
    public BombaVizinha(int bombasVizinhas) {
        this.bombasVizinhas = bombasVizinhas;
    }

    // Getter para o número de bombas vizinhas
    public int getBombasVizinhas() {
        return bombasVizinhas;
    }

    // Método para criar uma cópia da célula
    public BombaVizinha criarCopia() {
        BombaVizinha copia = new BombaVizinha(this.getBombasVizinhas());
        copia.setValor(this.getValor());
        copia.setValidado(this.getValidado());
        return copia;
    }
}
