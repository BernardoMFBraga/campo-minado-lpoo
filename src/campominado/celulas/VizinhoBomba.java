package celulas;

import tabuleiros.Tabuleiro;
//com a interface gráfica, não estamos utilizando diretamente essas classes que herdam de celula
public class VizinhoBomba extends Celula {
    public VizinhoBomba() {
        super();
    }
    @Override
    public void acaoAoAbrir(Tabuleiro tabuleiro, int linha, int coluna) {
        System.out.println("Célula vizinha à bomba aberta!"); //nao esta sendo implementado
    }
}
