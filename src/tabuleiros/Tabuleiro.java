package tabuleiros;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import celulas.Bomba;
import celulas.Celula;
import exceptions.CelulaComBandeiraException;
import ranking_jogador.Jogador;
import sistema.FimDeJogo;

public class Tabuleiro implements TabuleiroInterface {
    private int linhas;
    private int colunas;
    private int numeroBombas;
    private JFrame frame;
    private JButton[][] botoes;
    private Celula[][] jogoReal;
    private int dificuldade;
    private boolean gameOver;
    private int jogadorAtual = 1;
    private boolean jogoEmAndamento = true;
    private Jogador jogador;
    private boolean doisJogadores;
    private boolean modoMaluco;
    private double nivelMaluquice;

    public Tabuleiro(int dificuldade, Jogador jogador, Boolean doisJogadores, Boolean modoMaluco, double nivelMaluquice) {
        int largura;
        int altura;
        this.dificuldade = dificuldade;
        this.gameOver = false;
        this.jogador = jogador;
        this.doisJogadores = doisJogadores; 
        this.modoMaluco = modoMaluco;
        this.nivelMaluquice = (modoMaluco) ? nivelMaluquice : 0;

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
                             // Verificar se a célula já foi validada
                            if (!jogoReal[linha][coluna].getValidado()) {
                                 // Verificar se há uma bandeira na célula
                                if (jogoReal[linha][coluna].temBandeira()) {
                                    throw new CelulaComBandeiraException("Não é possível abrir uma célula marcada com bandeira.");
                                } else if (jogoReal[linha][coluna] instanceof Bomba) {
                                    abrirCampo();
                                    jogoEmAndamento = false;
                                } else {
                                    verificarBombas(linha, coluna);
                                }
                                // Alternar jogador somente se a célula não estiver validada
                                alternarJogador();
                                }
                                imprimirTabuleiro();
                        }
                        // Verificar se foi um clique do botão direito
                        else if (SwingUtilities.isRightMouseButton(e)) {
                        	 Celula celulaAtual = jogoReal[linha][coluna];
                              // Verificar se a célula é maluca e lidar com a mudança de estado
                              if (celulaAtual.getMaluca()) {
                                // Determinar se a célula muda de estado com base na probabilidade
                                if (Math.random() < nivelMaluquice) {
                                    // Alterar a célula para ser uma bomba ou não, conforme a probabilidade
                                    if (celulaAtual instanceof Bomba) {
                                        jogoReal[linha][coluna] = new Celula(); // Criar uma nova célula não-bomba na mesma posição
                                        jogoReal[linha][coluna].setMaluca(false); // Definir como não-maluca
                                        jogoReal[linha][coluna].setValor(" "); // Definir como não-bomba
                                        notificarVizinhosMalucos(linha, coluna);
                                    } else {
                                        jogoReal[linha][coluna] = new Bomba(); // Criar uma nova bomba na mesma posição
                                        jogoReal[linha][coluna].setMaluca(false); // Definir como não-maluca
                                        notificarVizinhosMalucos(linha, coluna);
                                    }
                                }
                            }
                             // Lógica para colocar ou remover bandeira
                             if (!celulaAtual.temBandeira()) {
                                 celulaAtual.alternarBandeira();
                                 botoes[linha][coluna].setText("🚩");
                             } else {
                                 celulaAtual.alternarBandeira();
                                 botoes[linha][coluna].setText("");
                             }
                             imprimirTabuleiro();
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
        if(doisJogadores){
        JOptionPane.showMessageDialog(frame, "Começa o Jogador 1", "Início do Jogo", JOptionPane.INFORMATION_MESSAGE);
        }
        if (modoMaluco) {
            definirCelulasMalucas();
        }
    }
	public void alternarJogador() {
        jogadorAtual = (jogadorAtual == 1) ? 2 : 1;
        if (doisJogadores && jogoEmAndamento) {
            JOptionPane.showMessageDialog(frame, "Próxima jogada: Jogador " + jogadorAtual, "Alternação de Jogadores", JOptionPane.INFORMATION_MESSAGE);
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
	    public void abrirCampo() {
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
            if(doisJogadores){
                if (jogadorAtual == 1) {
                    JOptionPane.showMessageDialog(frame, "Jogador 2 ganhou!", "Fim de Jogo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Jogador 1 ganhou!", "Fim de Jogo", JOptionPane.INFORMATION_MESSAGE);
                }
            }else{
                FimDeJogo fimDeJogo = new FimDeJogo(parentFrame, pontuacao, jogador);
            }
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

	        return pontuacao;
	    }
        public void definirCelulasMalucas() {
            for (int i = 0; i < linhas; i++) {
                for (int j = 0; j < colunas; j++) {
                    Celula celulaAtual = jogoReal[i][j];
                    celulaAtual.definirMaluquice();
                    if (celulaAtual.getMaluca()) {
                        //botoes[i][j].setBackground(Color.YELLOW);
                    }
                }
            }
        }
        //Metodo para eu visualizar se o modo maluco ta funcionando
        public void imprimirTabuleiro() {
            for (int i = 0; i < linhas; i++) {
                for (int j = 0; j < colunas; j++) {
                    System.out.print(jogoReal[i][j].getValor() + " ");
                }
                System.out.println(); // Nova linha para cada linha do tabuleiro
            }
        }
        // Metodos para o modo maluco
        private void notificarVizinhosMalucos(int linha, int coluna) {
            for (int i = linha - 1; i <= linha + 1; i++) {
                for (int j = coluna - 1; j <= coluna + 1; j++) {
                    if (i >= 0 && i < linhas && j >= 0 && j < colunas && (i != linha || j != coluna)) {
                        Celula vizinha = jogoReal[i][j];
                        // Verifica se a célula vizinha não foi validada
                        if (!vizinha.getValidado()) {
                            int bombasVizinhas = contarBombasVizinhas(i, j);
                            vizinha.setValor(String.valueOf(bombasVizinhas));
                        }
                    }
                }
            }
        }
        private int contarBombasVizinhas(int linha, int coluna) {
            int bombas = 0;
            for (int i = linha - 1; i <= linha + 1; i++) {
                for (int j = coluna - 1; j <= coluna + 1; j++) {
                    if (i >= 0 && i < linhas && j >= 0 && j < colunas && (i != linha || j != coluna)) {
                        if (jogoReal[i][j] instanceof Bomba) {
                            bombas++;
                        }
                    }
                }
            }
            return bombas;
        }

    }
