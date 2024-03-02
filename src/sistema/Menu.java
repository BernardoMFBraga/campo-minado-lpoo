package sistema;

import javax.swing.*;
import javax.swing.border.Border;


import ranking_jogador.Jogador;
import ranking_jogador.RankingInterface;
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
        
        // Calcula o tamanho da tela
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Define o tamanho da janela (largura x altura) como 400x300
        frame.setSize(600, 500);

        // Calcula a posição para centralizar a janela na tela
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;

        // Define a posição da janela
        frame.setLocation(x, y);

        // Define o layout da janela
        frame.getContentPane().setLayout(new BorderLayout());
        
        // Nome do Jogo
        JLabel lblTitulo = new JLabel("Campo Minado");
        lblTitulo.setFont(new Font("Courier New", Font.BOLD, 64));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBackground(Color.DARK_GRAY); 
        lblTitulo.setForeground(Color.GREEN);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 12);
        lblTitulo.setBorder(border);
        lblTitulo.setOpaque(true); // Definindo opacidade do fundo como verdadeiro
        frame.getContentPane().add(lblTitulo, BorderLayout.NORTH);

        // Painel para os botões
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridLayout(2, 1, 0, 0)); // 2 linhas, 1 coluna, espaçamento

        // Botão Jogar
        JButton btnJogar = new JButton("Jogar");
        btnJogar.setFont(new Font("Courier New", Font.ITALIC, 40));
        btnJogar.setHorizontalAlignment(SwingConstants.CENTER);
        btnJogar.setBackground(Color.DARK_GRAY); 
        btnJogar.setForeground(Color.GREEN);
        btnJogar.setFocusPainted(false); // Remove a borda de foco
        
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
         btnRanking.setFont(new Font("Courier New", Font.ITALIC, 40));
         btnRanking.setHorizontalAlignment(SwingConstants.CENTER);
         btnRanking.setBackground(Color.DARK_GRAY); 
         btnRanking.setForeground(Color.GREEN);
         btnRanking.setFocusPainted(false); 
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
