public class Quadrado {
    private String valor; // Valor do quadrado ('*', '0', '#', etc.)
    private boolean validado; // Indica se o quadrado foi revelado ou não

    // Construtor da classe
    public Quadrado() {
        this.validado = false; // Por padrão, o quadrado não está validado (não revelado)
    }

    // Getter para o valor do quadrado
    public String getValor() {
        return valor;
    }

    // Getter para o estado de validação do quadrado
    public boolean getValidado() {
        return validado;
    }

    // Setter para o valor do quadrado
    public void setValor(String valor) {
        this.valor = valor;
    }

    // Setter para o estado de validação do quadrado
    public void setValidado(boolean validado) {
        this.validado = validado;
    }
}
