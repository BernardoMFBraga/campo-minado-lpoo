package celulas;

import tabuleiros.Tabuleiro;

public abstract class CelulaAbstrata {
    // Método abstrato para ação ao abrir a célula
    public abstract void acaoAoAbrir(Tabuleiro tabuleiro, int linha, int coluna);
}
