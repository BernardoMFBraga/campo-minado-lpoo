package tabuleiros;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import celulas.Bomba;
import celulas.Celula;
import exceptions.CelulaComBandeiraException;

public class Tabuleiro implements TabuleiroInterface {
    int linhas;
    int colunas;
    int numeroBombas;
    JFrame frame;
    JButton[][] botoes;
    public Celula[][] jogoReal;
    int dificuldade;
    boolean gameOver;
    int jogadorAtual = 1;
    boolean jogoEmAndamento=true;


    public Tabuleiro(int dificuldade) {
        int largura;
        int altura;
        this.dificuldade = dificuldade;
        this.gameOver = false;

        switch (dificuldade) {
            case 1:
                this.numeroBombas = 10;
                linhas = 8;
                colunas = 10;
                largura = 500;
                altura = 500;
                botoes = new JButton[8][10];
                this.jogoReal = new Celula[8][10];
                break;
            case 2:
                this.numeroBombas = 40;
                linhas = 14;
                colunas = 18;
                largura = 780;
                altura = 780;
                botoes = new JButton[14][18];
                this.jogoReal = new Celula[14][18];
                break;
            default:
                this.numeroBombas = 99;
                linhas = 20;
                colunas = 24;
                largura = 1000;
                altura = 1000;
                botoes = new JButton[20][24];
                this.jogoReal = new Celula[20][24];
                break;
        }

        frame = new JFrame("Campo Minado");
        frame.setVisible(true);
        frame.setSize(largura, altura);
        frame.setLayout(new GridLayout(linhas, colunas));

        for (int i = 0; i < botoes.length; i++) {
            for (int j = 0; j < botoes[0].length; j++) {
                botoes[i][j] = new JButton();
                botoes[i][j].setBackground(Color.green);
                frame.add(botoes[i][j]);

                int linha = i;
                int coluna = j;

                // Adicionar ouvinte de mouse para cada botão
                botoes[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (gameOver) {
                            return;
                        }

                        // Verificar se foi um clique do botão esquerdo
                        if (SwingUtilities.isLeftMouseButton(e)) {
                            // Verificar se há uma bandeira na célula
                            if (jogoReal[linha][coluna].temBandeira()) {
                                throw new CelulaComBandeiraException("Não é possível abrir uma célula marcada com bandeira.");
                            } else if (jogoReal[linha][coluna] instanceof Bomba) {
                                abrirCampo();
                                if (jogadorAtual == 1) {
                                    System.out.println("Jogador 2 ganhou!");
                                } else {
                                    System.out.println("Jogador 1 ganhou!");
                                }
                                jogoEmAndamento = false;
                            } else {
                                verificarBombas(linha, coluna);
                            }
                            alternarJogador();
                        }
                        // Verificar se foi um clique do botão direito
                        else if (SwingUtilities.isRightMouseButton(e)) {
                        	 Celula celulaAtual = jogoReal[linha][coluna];
                             
                             // Lógica para colocar ou remover bandeira
                             if (!celulaAtual.temBandeira()) {
                                 celulaAtual.alternarBandeira();
                                 botoes[linha][coluna].setText("🚩");
                             } else {
                                 celulaAtual.alternarBandeira();
                                 botoes[linha][coluna].setText("");
                             }
                        }
                    }
                });
            }
            
        }
        iniciaTabuleiro();
    }
	public void iniciaTabuleiro() {
        preencherMatriz(jogoReal, " ");
        distribuirBombas();
        System.out.println("Começa o Jogador 1");
    }
	private void alternarJogador() {
	    jogadorAtual = (jogadorAtual == 1) ? 2 : 1;
	    if (jogoEmAndamento) {
	        System.out.println("Próxima jogada: Jogador " + jogadorAtual);
	    }
	}

    public void preencherMatriz(Celula[][] matriz, String valorPadrao) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                matriz[i][j] = new Celula();
                matriz[i][j].setValor(valorPadrao);
            }
        }
    }

    public void distribuirBombas() {
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
	 
	    public void verificarBombas(int linha, int coluna) {
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

	        jogoReal[linha][coluna].setValor(String.valueOf(bombas));
	        jogoReal[linha][coluna].setValidado(true);
	        botoes[linha][coluna].setText(String.valueOf(bombas));
	        botoes[linha][coluna].setBackground(Color.white);

	        int cont = 0;
	        for (Celula[] quadrados : jogoReal) {
	            for (int j = 0; j < jogoReal[0].length; j++) {
	                if (quadrados[j].getValidado()) {
	                    cont++;
	                }
	            }
	        }

	        if (cont == (linhas * colunas - numeroBombas)){
	            abrirCampo();
	        }

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
	        gameOver = true;
	    	for (int x = 0; x < botoes.length; x++) {
	            for (int y = 0; y < botoes[0].length; y++) {
	                if (jogoReal[x][y].getValor().equals("*")) {
	                    botoes[x][y].setBackground(Color.red);
	                    //botoes[x][y].setIcon(new ImageIcon("images/mina-jogo.png"));
	                } else {
	                    botoes[x][y].setEnabled(false);
	                }

	            }
	        }
	        }

}