package campominado;

import javax.swing.*;

import ranking_jogador.Jogador;
import ranking_jogador.RankingInterface;
import tabuleiros.Tabuleiro;
import tabuleiros.TabuleiroInterface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {

    private JFrame frame;
    private Jogador jogador;

    public Menu() {
        frame = new JFrame();
        frame.setBounds(100, 100, 300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        // Nome do Jogo
        JLabel lblTitulo = new JLabel("Campo Minado");
        lblTitulo.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        frame.getContentPane().add(lblTitulo, BorderLayout.NORTH);

        // Painel para os botões
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridLayout(2, 1, 0, 10)); // 2 linhas, 1 coluna, espaçamento vertical de 10

        // Botão Jogar
        JButton btnJogar = new JButton("Jogar");
        btnJogar.addActionListener(new ActionListener() {
        	 public void actionPerformed(ActionEvent e) {
                 // Caixa de diálogo para obter o nome do jogador
                 String nomeJogador = JOptionPane.showInputDialog(frame, "Digite o nome do jogador:");

                 // Verifica se o nome não está vazio ou nulo
                 if (nomeJogador != null && !nomeJogador.isEmpty()) {
                     jogador = new Jogador(nomeJogador);
                     ModosDeJogo modosdejogo = new ModosDeJogo(jogador);
                 } else {
                     JOptionPane.showMessageDialog(frame, "Nome do jogador inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                 }
             }
         });
         panel.add(btnJogar);

        // Botão Ranking
        JButton btnRanking = new JButton("Ranking");
        btnRanking.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	RankingInterface rankingInterface = new RankingInterface();
            }
        });
        panel.add(btnRanking);

        // Exibe a janela
        frame.setVisible(true);
    }
}

