package sistema;

import javax.swing.*;
import javax.swing.border.Border;

import ranking_jogador.Jogador;
import tabuleiros.Tabuleiro;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModosDeJogo {
    private JFrame frameMultiplayer;
	private Jogador jogador1;
    
    public ModosDeJogo(Jogador jogador) {
        frameMultiplayer = new JFrame();
        frameMultiplayer.setBounds(100, 100, 300, 200);
        frameMultiplayer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fecha apenas a janela dos modos de jogo
        frameMultiplayer.getContentPane().setLayout(new BorderLayout());

        this.jogador1 = jogador;

        // Calcula o tamanho da tela
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Define o tamanho da janela (largura x altura) como 400x300
        frameMultiplayer.setSize(600, 500);

        // Calcula a posição para centralizar a janela na tela
        int x = (screenSize.width - frameMultiplayer.getWidth()) / 2;
        int y = (screenSize.height - frameMultiplayer.getHeight()) / 2;

        // Define a posição da janela
        frameMultiplayer.setLocation(x, y);

        // Define o layout da janela
        frameMultiplayer.getContentPane().setLayout(new BorderLayout());

        // Título "Jogar"
        JLabel lblJogar = new JLabel("Escolha seu Modo de Jogo");
        lblJogar.setFont(new Font("Courier New", Font.BOLD, 35));
        lblJogar.setBackground(Color.DARK_GRAY);
        lblJogar.setForeground(Color.GREEN);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 12);
        lblJogar.setBorder(border);
        lblJogar.setOpaque(true); // Definindo opacidade do fundo como verdadeiro
        frameMultiplayer.getContentPane().add(lblJogar, BorderLayout.NORTH);
        lblJogar.setHorizontalAlignment(SwingConstants.CENTER);
        frameMultiplayer.getContentPane().add(lblJogar, BorderLayout.NORTH);

        // Painel para os botões de seleção de jogador
        JPanel panelSelecaoJogador = new JPanel();
        frameMultiplayer.getContentPane().add(panelSelecaoJogador, BorderLayout.CENTER);
        panelSelecaoJogador.setLayout(new GridLayout(3, 1, 0, 0)); // 3 linhas, 1 coluna, espaçamento vertical de 0

        // Botão para selecionar Um Jogador
        JButton btnUmJogador = new JButton("Um Jogador");
        btnUmJogador.setFont(new Font("Courier New", Font.ITALIC, 30));
        btnUmJogador.setForeground(Color.GREEN);
        btnUmJogador.setBackground(Color.DARK_GRAY);
        btnUmJogador.setFocusPainted(false);
        btnUmJogador.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirModoJogo(true); // Abre o modo de jogo para um jogador
            }
        });
        panelSelecaoJogador.add(btnUmJogador);

        // Botão para selecionar Dois Jogadores
        JButton btnDoisJogadores = new JButton("Dois Jogadores");
        btnDoisJogadores.setFont(new Font("Courier New", Font.ITALIC, 30));
        btnDoisJogadores.setForeground(Color.GREEN);
        btnDoisJogadores.setBackground(Color.DARK_GRAY);
        btnDoisJogadores.setFocusPainted(false);
        btnDoisJogadores.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirModoJogo(false); // Abre o modo de jogo para dois jogadores
            }
        });
        panelSelecaoJogador.add(btnDoisJogadores);

        // Botão para Modo Maluco (ainda em construção)
        JButton btnModoMaluco = new JButton("Modo Maluco");
        btnModoMaluco.setFont(new Font("Courier New", Font.ITALIC, 30));
        btnModoMaluco.setForeground(Color.GREEN);
        btnModoMaluco.setBackground(Color.DARK_GRAY);
        btnModoMaluco.setFocusPainted(false);
        btnModoMaluco.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frameMultiplayer, "Modo Maluco ainda está em construção!");
            }
        });
        panelSelecaoJogador.add(btnModoMaluco);

        // Exibe a janela dos modos de jogo
        frameMultiplayer.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Component frame;
    }

    // Método para abrir o painel de seleção de dificuldade, de acordo com o modo de jogo selecionado
    private void abrirModoJogo(boolean umJogador) {
        if (umJogador) {	 
            // Caixa de diálogo para obter o nome do jogador
            Component frame = null;
            String nomeJogador = JOptionPane.showInputDialog(frame, "Digite o nome do jogador:");
            // Verifica se o nome não está vazio ou nulo
            if (nomeJogador != null && !nomeJogador.isEmpty()) {
                Jogador jogador1 = new Jogador(nomeJogador);
                Dificuldade dificuldade = new Dificuldade(jogador1);
                frameMultiplayer.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "Nome do jogador inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
        	// Caixa de diálogo para obter os nomes dos jogadores
            Component frame = null;
            String nomeJogador1 = JOptionPane.showInputDialog(frame, "Digite o nome do Jogador 1:");
            String nomeJogador2 = JOptionPane.showInputDialog(frame, "Digite o nome do Jogador 2:");
            // Verifica se os nomes não estão vazios ou nulos
            if (nomeJogador1 != null && !nomeJogador1.isEmpty() && nomeJogador2 != null && !nomeJogador2.isEmpty()) {
                ModoDoisJogadores modoDoisJogadores = new ModoDoisJogadores(nomeJogador1, nomeJogador2);
                Dificuldade dificuldade = new Dificuldade(jogador1);
                frameMultiplayer.dispose(); // Fechar a janela de seleção de modo de jogo
            } else {
                JOptionPane.showMessageDialog(frame, "Nomes de jogadores inválidos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

