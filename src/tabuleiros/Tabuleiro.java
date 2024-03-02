package tabuleiros;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import sistema.FimDeJogo;
import celulas.Bomba;
import celulas.Celula;
import exceptions.CelulaComBandeiraException;
import ranking_jogador.Jogador;

public class Tabuleiro implements TabuleiroInterface {
    public int linhas;
    public int colunas;
    int numeroBombas;
    JFrame frame;
    JButton[][] botoes;
    public Celula[][] jogoReal;
    int dificuldade;
    boolean gameOver;
    int jogadorAtual = 1;
    boolean jogoEmAndamento=true;
    private Jogador jogador;
    private boolean modoUmJogador;
    private int jogadorSingle;
    private Jogador jogador2;

    public Tabuleiro(int dificuldade, Jogador jogador) {
        int largura;
        int altura;
        this.dificuldade = dificuldade;
        this.gameOver = false;
        this.jogador = jogador;
        
        //definindo o modo de jogo com um jogador
        this.modoUmJogador = true;
        this.jogadorAtual = 1;

        // criando o tabuleiro com base na dificuldade selecionada
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

        //interface gráfica do tabuleiro
        frame = new JFrame("Campo Minado");
        frame.setVisible(true);
        frame.setSize(largura, altura);
        frame.setLayout(new GridLayout(linhas, colunas));
        
        // adiona os botões e os ouvintes de mouse para cada célula do tabuleiro
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
        centralizarJanela();
    }
    
    private void centralizarJanela() {
        // Obtém o tamanho da tela
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Calcula a posição para centralizar a janela
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;

        // Define a posição da janela
        frame.setLocation(x, y);
    }
    
    public Tabuleiro(int dificuldade, Jogador jogador1, Jogador jogador2) {
        // Defina o modo de jogo como dois jogadores
        this.modoUmJogador = false;
        
        // Inicialize os jogadores
        this.jogador = jogador1;
        this.jogador2 = jogador2;
        
        // Defina o jogador atual como 1
        this.jogadorSingle = 1;
    }
    
	public Tabuleiro(int i, String nomeJogador1, String nomeJogador2) {
		// TODO Auto-generated constructor stub
	}

	public void iniciaTabuleiro() {
        preencherMatriz(jogoReal, " ");
        distribuirBombas();
        System.out.println("Começa o Jogador 1");
    }
	// Método para alternar entre os jogadores no modo dois jogadores
    private void alternarJogador() {
        if (!modoUmJogador) {
            jogadorSingle = (jogadorSingle == 1) ? 2 : 1;
            if (jogoEmAndamento) {
                System.out.println("Próxima jogada: Jogador " + jogadorAtual);
            }
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
	                    
	                } else {
	                    botoes[x][y].setEnabled(false);
	                }

	            }
	        }
	    	int pontuacao = calcularPontuacao();
	        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(frame);
	        FimDeJogo fimDeJogo = new FimDeJogo(parentFrame, pontuacao,this.jogador);
	        frame.dispose(); // Fecha o JFrame do tabuleiro
	        }
	    public int calcularPontuacao() {
	        int pontuacao = 0;
	        int celulasAbertas = 0;

	        for (int i = 0; i < jogoReal.length; i++) {
	            for (int j = 0; j < jogoReal[0].length; j++) {
	                // Verifique se a célula foi validada (aberta)
	                if (jogoReal[i][j].getValidado()) {
	                    celulasAbertas++;
	                }
	            }
	        }

	        pontuacao = celulasAbertas * 10;
	        
	        // Apenas retornar pontuação para o modo um jogador
	        if (modoUmJogador) {
	            return pontuacao;
	        } else {
	            // Pontuação final não é relevante para o modo dois jogadores
	            return -1;
	        }
	    }

}