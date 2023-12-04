package campominado.celula;

public class Celula {

    private boolean validado; // Indica se a célula foi revelada ou não
    private String valor; // Valor da célula
    protected Celula[][] jogoReal;
    protected Celula[][] tela;

    // Construtor da classe
    public Celula() {
        this.validado = false; // Por padrão, a célula não está validada (não revelada)
    }

    // Getter para o estado de validação da célula
    public boolean getValidado() {
        return validado;
    }

    // Setter para o estado de validação da célula
    public void setValidado(boolean validado) {
        this.validado = validado;
    }

    // Getter para o valor da célula
    public String getValor() {
        return valor;
    }

    // Setter para o valor da célula
    public void setValor(String valor) {
        this.valor = valor;
    }

    // Método para criar uma cópia da célula
    public Celula criarCopia() {
        Celula copia = new Celula();
        copia.setValor(this.getValor());
        copia.setValidado(this.getValidado());
        return copia;
    }
}
