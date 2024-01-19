package campominado;

public class EspacoVazio extends Celula {
    public EspacoVazio() {
        super();
    }
	    @Override
	    public void acaoAoAbrir(Tabuleiro tabuleiro, int linha, int coluna) {
	        expandir(tabuleiro, linha, coluna);
	    }
			
		public void expandir(Tabuleiro tabuleiro, int linha, int coluna) {
            for (int i = linha - 1; i <= linha + 1; i++) {
                for (int j = coluna - 1; j <= coluna + 1; j++) {
                    if (validaCoordenadas(i, j, tabuleiro) && !tabuleiro.jogoReal[i][j].getValidado()) {
                        tabuleiro.abrirCelula(i, j);
                        if (tabuleiro.jogoReal[i][j] instanceof EspacoVazio) {
                            // Chama a expansão recursiva para espaços vazios adjacentes
                            expandir(tabuleiro, i, j);
                        }
                    }
                }
            }
		}
}