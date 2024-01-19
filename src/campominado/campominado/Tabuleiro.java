package campominado;

import java.util.Scanner;

public class Tabuleiro{
	private Scanner teclado = new Scanner(System.in);
    private  int numeroBombas;
    public Celula[][] jogoReal;
    public  Celula[][] tela;
    private int ganhou = 0;
	
	public Tabuleiro() {
		this.numeroBombas = 10;
		this.jogoReal = new Celula[8][10];
        this.tela = new Celula[8][10];
        
        iniciaTabuleiro();
	}
	public void iniciaTabuleiro() {
        preencherMatriz(tela, "#");
        preencherMatriz(jogoReal, " ");
        distribuirBombas();
        menuJogo();
    }

    private void preencherMatriz(Celula[][] matriz, String valorPadrao) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                matriz[i][j] = new Celula();
                matriz[i][j].setValor(valorPadrao);
            }
        }
    }

    private void distribuirBombas() {
        for (int i = 0; i < numeroBombas; i++) {
            int x = (int) (jogoReal.length * Math.random());
            int y = (int) (jogoReal[0].length * Math.random());

            if (!(jogoReal[x][y] instanceof Bomba)) {
                jogoReal[x][y] = new Bomba();
            }else {
                    i--;
            }
        }
    }
	 
	 public void mostraJogo() {
	        System.out.print("     ");
	        //Loop para colocar os números da coordenada x no topo da matriz.
	        for (int i = 0; i < tela[0].length; i++) {
	            if (i < 10) {
	                System.out.print("0" + i + "  ");
	            } else {
	                System.out.print(i + "  ");
	            }
	        }
	        System.out.println("\n   ----------------------------------------");
	        //Loop para colocar os números da coordenada y no canto esquerdo da matriz.
	        for (int i = 0; i < tela.length; i++) {
	            if (i < 10) {
	                System.out.print("0" + i + " | ");
	            } else {
	                System.out.print(i + " | ");
	            }
	            //Loop que preenche a matriz para o usuário com '#'.
	            for (int j = 0; j < tela[0].length; j++) {
	                System.out.print(tela[i][j].getValor() + "   ");
	            }
	            System.out.println();
	        }
	    }
	       
	 public void voceGanhou() {
	        System.out.println("");
	        System.out.println("Você GANHOU!");
	        mostraJogo();
	    }
	 
	 public void telaGameOver() {
		    System.out.println("\nGAME OVER!");
		    System.out.println("Escolha uma opção:");
	        System.out.println("1. Reiniciar o jogo");
	        System.out.println("2. Voltar para o menu");
	        System.out.println("0. Sair do jogo");

	        int escolha = -1;

	        try {
	            escolha = Integer.parseInt(teclado.nextLine());
	        } catch (NumberFormatException e) {
	            escolha = -1;
	        }

	        switch (escolha) {
	            case 1:
	                iniciaTabuleiro(); // Reinicia o jogo
	                break;
	           
	            case 2:
	                iniciaTabuleiro(); 
	                menuJogo();
	                break;
	           
	            case 0:
	                System.exit(0); // Sai do programa
	                break;
	            
	            default:
	                System.out.println("Opção inválida. Saindo do jogo.");
	                System.exit(0);
	                break;
	        }
	    }

	    public int menuLinha() {
	        int linha;

	        System.out.println("Digite a linha:");
	        try {
	            linha = Integer.parseInt(teclado.nextLine());
	        } catch (NumberFormatException e) {
	            return -1;
	        }

	        return linha;
	    }

	    public int menuColuna() {
	        int coluna;

	        System.out.println("Digite a coluna:");
	        try {
	            coluna = Integer.parseInt(teclado.nextLine());
	        } catch (NumberFormatException e) {
	            return -1;
	        }

	        return coluna;
	    }
	 
	    //evitando erros de input
	    public boolean validaCampos(int linha, int coluna) {
	        if (linha == -1 && coluna == -1) {
	            System.out.println("Campos inválidos");
	            return false;
	        }

	        if (linha >= jogoReal.length || linha < 0) {
	            System.out.println("Linha inexistente");
	            return false;
	        }

	        if (coluna >= jogoReal[0].length || coluna < 0) {
	            System.out.println("Coluna inexistente");
	            return false;
	        }

	        if (jogoReal[linha][coluna].getValidado()) {
	            System.out.println("Coordenada já revelada");
	            return false;
	        }

	        return true;
	    }   
	 
	    private void verificarBombas(int linha, int coluna) {
	        if (jogoReal[linha][coluna].getValidado()) {
	            return;
	        }

	        int bombas = 0;
	        int minLinha;
	        int maxLinha;
	        int minColuna;
	        int maxColuna;


	        if (linha != 0 && linha != jogoReal.length - 1) {
	            minLinha = linha - 1;
	            maxLinha = linha + 1;
	        } else if (linha == 0) {
	            minLinha = linha;
	            maxLinha = linha + 1;
	        } else {
	            minLinha = linha - 1;
	            maxLinha = linha;
	        }

	        if (coluna != 0 && coluna != jogoReal[0].length - 1) {
	            minColuna = coluna - 1;
	            maxColuna = coluna + 1;
	        } else if (coluna == 0) {
	            minColuna = coluna;
	            maxColuna = coluna + 1;
	        } else {
	            minColuna = coluna - 1;
	            maxColuna = coluna;
	        }

	        for (int i = minLinha; i <= maxLinha; i++) {
	            for (int j = minColuna; j <= maxColuna; j++) {
	            	if (jogoReal[i][j] instanceof Bomba) {
	                    bombas++;
	                }
	            }
	        }

	        tela[linha][coluna].setValor(String.valueOf(bombas));
	        tela[linha][coluna].setValidado(true);
	        jogoReal[linha][coluna].setValor(String.valueOf(bombas));
	        jogoReal[linha][coluna].setValidado(true);
	        ganhou++;

	        if (bombas == 0) {
	            for (int i = minLinha; i <= maxLinha; i++) {
	                for (int j = minColuna; j <= maxColuna; j++) {
	                    if (i == linha && j == coluna) {
	                        continue;
	                    }

	                    verificarBombas(i, j);
	                }
	            }
	        }
	    }
	 
	    public void descobrirCampos(int linha, int coluna) {
	        if (jogoReal[linha][coluna].getValidado()) {
	            return;
	        }

	        int bombas = 0;
	        int minLinha;
	        int maxLinha;
	        int minColuna;
	        int maxColuna;


	        if (linha != 0 && linha != jogoReal.length - 1) {
	            minLinha = linha - 1;
	            maxLinha = linha + 1;
	        } else if (linha == 0) {
	            minLinha = linha;
	            maxLinha = linha + 1;
	        } else {
	            minLinha = linha - 1;
	            maxLinha = linha;
	        }

	        if (coluna != 0 && coluna != jogoReal[0].length - 1) {
	            minColuna = coluna - 1;
	            maxColuna = coluna + 1;
	        } else if (coluna == 0) {
	            minColuna = coluna;
	            maxColuna = coluna + 1;
	        } else {
	            minColuna = coluna - 1;
	            maxColuna = coluna;
	        }

	        for (int i = minLinha; i <= maxLinha; i++) {
	            for (int j = minColuna; j <= maxColuna; j++) {
	                if (jogoReal[i][j].getValor().equals("*")) {
	                    bombas++;
	                }
	            }
	        }

	        tela[linha][coluna].setValor(String.valueOf(bombas));
	        tela[linha][coluna].setValidado(true);
	        jogoReal[linha][coluna].setValor(String.valueOf(bombas));
	        jogoReal[linha][coluna].setValidado(true);
	    }
	    public void marcaCelula(int linha, int coluna) {
	        if (!tela[linha][coluna].getValidado()) {
	            tela[linha][coluna].setValor("F"); // Marcando a célula com bandeira
	        } else {
	            System.out.println("Essa célula já foi aberta. Não é possível marcar com bandeira.");
	        }
	    }

	    public void removeMarca(int linha, int coluna) {
	        if (tela[linha][coluna].getValor().equals("F")) {
	            tela[linha][coluna].setValor("#"); // Removendo a bandeira, restaurando o valor original
	        } else {
	            System.out.println("Essa célula não está marcada com bandeira.");
	        }
	    }

	    public boolean celulaMarcadaComBandeira(int linha, int coluna) {
	        return tela[linha][coluna].getValor().equals("F");
	    }
	    public void abrirCelula(int linha, int coluna) {
	        if (celulaMarcadaComBandeira(linha, coluna)) {
	            System.out.println("Remova a bandeira antes de abrir essa célula.");
	        } else {
	            if (!jogoReal[linha][coluna].getValidado()) {
	                Celula celulaAtual = jogoReal[linha][coluna];
	                celulaAtual.acaoAoAbrir(this, linha, coluna); // Chamar o método acaoAoAbrir

	                if (celulaAtual instanceof Bomba) {
	                	telaGameOver();
	                } else if (celulaAtual instanceof EspacoVazio) {
	                    abrirEspacosVazios(linha, coluna);
	                } else if (celulaAtual instanceof VizinhoBomba) {
	                }

	            } else {
	                System.out.println("Essa célula já foi aberta.");
	            }
	        }
	    }

	    private void abrirEspacosVazios(int linha, int coluna) {
	        if (jogoReal[linha][coluna].getValidado()) {
	            return;
	        }

	        tela[linha][coluna].setValidado(true);
	        jogoReal[linha][coluna].setValidado(true);
	        ganhou++;

	        int minLinha, maxLinha, minColuna, maxColuna;

	        if (linha != 0 && linha != jogoReal.length - 1) {
	            minLinha = linha - 1;
	            maxLinha = linha + 1;
	        } else if (linha == 0) {
	            minLinha = linha;
	            maxLinha = linha + 1;
	        } else {
	            minLinha = linha - 1;
	            maxLinha = linha;
	        }

	        if (coluna != 0 && coluna != jogoReal[0].length - 1) {
	            minColuna = coluna - 1;
	            maxColuna = coluna + 1;
	        } else if (coluna == 0) {
	            minColuna = coluna;
	            maxColuna = coluna + 1;
	        } else {
	            minColuna = coluna - 1;
	            maxColuna = coluna;
	        }

	        for (int i = minLinha; i <= maxLinha; i++) {
	            for (int j = minColuna; j <= maxColuna; j++) {
	                abrirCelula(i, j);
	            }
	        }
	    }

	 
	    public void menuJogo() {
	        try {
	            int linha = 0;
	            int coluna = 0;
	            int i = -1;

	            while (i != 0) {
	                mostraJogo();
	                System.out.println("\n(1) Abrir célula / (2) Marcar com bandeira / (3) Remover bandeira  / (0) Reiniciar ou sair do jogo");
	                int opcao = Integer.parseInt(teclado.nextLine());

	                switch (opcao) {
	                    
	                case 1:
	                        linha = menuLinha();
	                        coluna = menuColuna();
	                        final boolean validado = validaCampos(linha, coluna);
	                        if (validado && !jogoReal[linha][coluna].getValidado()) {
	                            abrirCelula(linha, coluna);
	                            if (jogoReal[linha][coluna].getValor().equals("*")) {
	                            	telaGameOver();
	                                i = 0;
	                            } else {
	                                verificarBombas(linha, coluna);
	                                if (ganhou == (jogoReal.length * jogoReal[0].length - numeroBombas)) {
	                                    voceGanhou();
	                                    i = 0;
	                                }
	                            }
	                        } else {
	                        }
	                        break;
	                    
	                    case 2:
	                        linha = menuLinha();
	                        coluna = menuColuna();
	                        marcaCelula(linha, coluna);
	                        break;
	                    
	                    case 3:
	                        linha = menuLinha();
	                        coluna = menuColuna();
	                        removeMarca(linha, coluna);
	                        break;
	                    
	                    case 0:
	                        System.out.println("Você deseja reiniciar ou sair jogo? (1: Reiniciar, 0: Sair)");
	                        int reiniciar = Integer.parseInt(teclado.nextLine());
	                        if (reiniciar == 1) {
	                            iniciaTabuleiro(); // Reinicia o jogo
	                        } else {
	                            i = 0; // Sai do loop
	                        }
	                        break;
	                    default:
	                    	
	                        System.out.println("Opção inválida.");
	                        break;
	                }
	            }
	        } finally {
	            teclado.close();
	        }
	    }


}
