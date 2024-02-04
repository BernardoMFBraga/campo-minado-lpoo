package campominado;

public interface TabuleiroInterface {
    void iniciaTabuleiro();
    void preencherMatriz(Celula[][] matriz, String valorPadrao);
    void distribuirBombas();
    void verificarBombas(int linha, int coluna);
  
}

