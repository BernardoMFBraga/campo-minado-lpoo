package sistema;

import celulas.Celula;
import tabuleiros.Tabuleiro;

public class ModoDoisJogadores {
	private String jogador1;
    private String jogador2;
    private Tabuleiro tabuleiro;

    public ModoDoisJogadores(String jogador1, String jogador2) {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
    	this.tabuleiro = new Tabuleiro(1, jogador1, jogador2);
    }
    
    public void iniciarJogo() {
        // Inicializa a matriz jogoReal
        tabuleiro = new Tabuleiro(1, jogador1, jogador2);
        tabuleiro.jogoReal = new Celula[tabuleiro.linhas][tabuleiro.colunas];
        // Inicia o tabuleiro
        tabuleiro.iniciaTabuleiro();
    }
}

