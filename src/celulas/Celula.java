package celulas;

import tabuleiros.Tabuleiro;

public class Celula extends CelulaAbstrata {
    protected String valor; // Valor da célula ('*', '0', '#', etc.)
    protected boolean validado; // Indica se a célula foi revelada ou não
    protected boolean temBandeira;
    protected boolean maluca;
    
    // Construtor da classe
    public Celula() {
        this.validado = false; // Por padrão, a célula não está validada (não revelada)
        this.temBandeira = false; // Por padrão, a célula não está marcada com bandeira
        this.maluca = false; // Por padrão, a célula não é maluca
    }

    // Getter para o valor da célula
    public String getValor() {
        return valor;
    }

    // Getter para o estado de validação da célula
    public boolean getValidado() {
        return validado;
    }

    // Getter para o estado de marcação com bandeira
    public boolean temBandeira() {
        return temBandeira;
    }

    public void alternarBandeira() {
        temBandeira = !temBandeira;
    }
    // Setter para o valor da célula
    public void setValor(String valor) {
        this.valor = valor;
    }

    // Setter para o estado de validação da célula
    public void setValidado(boolean validado) {
        this.validado = validado;
    }
    public boolean getMaluca() {
        return maluca;
    }

    public void setMaluca(boolean maluca) {
        this.maluca = maluca;
    }
    public void definirMaluquice() {
        if (Math.random() < 0.5) {
            this.maluca = true;
        
        }
    }
   
    // Método para ação ao abrir a célula
    public void acaoAoAbrir(Tabuleiro tabuleiro, int linha, int coluna) {
    // sem utilidade na interface grafica
    }
}
