package campominado.celula;

public class Bomba extends Celula {

    private final String valor = "*";

    // Getter para o valor da célula
    public String getValor() {
        return valor;
    }

    // Método para criar uma cópia da célula
    public Bomba criarCopia() {
        Bomba copia = new Bomba();
        copia.setValor(this.getValor());
        copia.setValidado(this.getValidado());
        return copia;
    }
}
