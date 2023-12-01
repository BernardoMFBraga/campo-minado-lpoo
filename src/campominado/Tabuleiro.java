
import java.util.Scanner;

public class Tabuleiro {
	private Scanner teclado = new Scanner(System.in);
    private  int numeroBombas;
    private Quadrado[][] jogoReal;
    private  Quadrado[][] tela;
    private int ganhou = 0;
	
	public Tabuleiro() {
		this.numeroBombas = 10;
		this.jogoReal = new Quadrado[8][10];
        this.tela = new Quadrado[8][10];
        
        iniciaTabuleiro();
	}
	 public void iniciaTabuleiro() {
	        System.out.println();

	        //Loop que setta a matriz 'tela' com '#'.
	        for (int i = 0; i < tela.length; i++) {
	            for (int j = 0; j < tela[0].length; j++) {
	                tela[i][j] = new Quadrado();
	                tela[i][j].setValor("#");
	            }
	        }
	        //Loop que setta a matriz 'jogo real' com ' '.
	        for (int i = 0; i < tela.length; i++) {
	            for (int j = 0; j < jogoReal[0].length; j++) {
	                jogoReal[i][j] = new Quadrado();
	                jogoReal[i][j].setValor(" ");
	            }
	        }

	        //Loop que setta na matriz 'jogo real' as bombas em posições aleatórias.
	        for (int i = 0; i < numeroBombas; i++) {
	            final int x = (int) (jogoReal.length * Math.random());
	            final int y = (int) (jogoReal[0].length * Math.random());

	            if (!jogoReal[x][y].getValor().equals("*")) {
	                jogoReal[x][y].setValor("*");
	            }
	        }

	        menuJogo();
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
	        System.out.println("\n   ---------------------------------------------------------------------------------------------");
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
	 
	 public void vocePerdeu() {
	        System.out.println("");
	        System.out.println("Você PERDEU!");
	        abrirCampo();
	        mostraJogo();
	    }
	 public void voceGanhou() {
	        System.out.println("");
	        System.out.println("Você GANHOU!");
	        mostraJogo();
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
	            System.out.println("Coordenada já usada");
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
	                if (jogoReal[i][j].getValor().equals("*")) {
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
	 
	    private void abrirCampo() {
	        for (int x = 0; x < jogoReal.length; x++) {
	            for (int y = 0; y < jogoReal[0].length; y++) {
	                if (jogoReal[x][y].getValor().equals("*")) {
	                    tela[x][y].setValor("*");
	                    continue;
	                }

	                descobrirCampos(x, y);
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
	 
	 public void menuJogo() {
		 try {
		        int linha = 0;
		        int coluna = 0;
		        int i = -1;
		        while (i != 0) {
		            mostraJogo();
		            System.out.println("\n-------------------------");

		            linha = menuLinha();
		            coluna = menuColuna();

		            final boolean validado = validaCampos(linha, coluna);

		            if (validado) {
		                if (jogoReal[linha][coluna].getValor().equals("*")) {
		                    vocePerdeu();
		                    i = 0;
		                } else {
		                    verificarBombas(linha, coluna);
		                    if (ganhou == (jogoReal.length * jogoReal[0].length - numeroBombas)) {
		                        voceGanhou();
		                        i = 0;
		                    }
		                }
		            } else {
		                System.out.println("Tente mais uma vez\n");
		            }
		        }
		    } finally {
		        teclado.close();
		    }
		}
}
