package campominado.celula;

public class EspacoVazio extends Celula {

    private final String valor = " ";

    // Getter para o valor da célula
    public String getValor() {
        return valor;
    }

    // Método para criar uma cópia da célula
    public EspacoVazio criarCopia() {
        EspacoVazio copia = new EspacoVazio();
        copia.setValor(this.getValor());
        copia.setValidado(this.getValidado());
        return copia;
    }
}
