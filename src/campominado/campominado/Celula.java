package campominado;

public class Celula {
    protected String valor; // Valor da célula ('*', '0', '#', etc.)
    protected boolean validado; // Indica se a célula foi revelada ou não

    // Construtor da classe
    public Celula() {
        this.validado = false; // Por padrão, a célula não está validada (não revelada)
    }

    // Getter para o valor da célula
    public String getValor() {
        return valor;
    }

    // Getter para o estado de validação da célula
    public boolean getValidado() {
        return validado;
    }

    // Setter para o valor da célula
    public void setValor(String valor) {
        this.valor = valor;
    }

    // Setter para o estado de validação da célula
    public void setValidado(boolean validado) {
        this.validado = validado;
    }
    
    public void acaoAoAbrir(Tabuleiro tabuleiro, int linha, int coluna) {
        // Lógica padrão ao abrir uma célula
        if (!validaCoordenadas(linha, coluna, tabuleiro)) {
            return;
        }

        if (tabuleiro.jogoReal[linha][coluna] instanceof Bomba) {
            tabuleiro.telaGameOver();
        }
    }

    protected boolean validaCoordenadas(int linha, int coluna, Tabuleiro tabuleiro) {
        return linha >= 0 && linha < tabuleiro.jogoReal.length &&
               coluna >= 0 && coluna < tabuleiro.jogoReal[0].length;
    }
}
    

