package ranking_jogador;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.util.ArrayList;

public class RankingInterface extends JFrame {

    private JLabel labelSuperior;
    private JPanel panelCentral;
    private ArrayList<String> nomes;
    private ArrayList<String> pontos;

    public RankingInterface() {
        setTitle("Ranking");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// Fecha apenas a janela, não a aplicação inteira
        setPreferredSize(new Dimension(420, 600));
        setResizable(false); // Impede o redimensionamento da janela

        nomes = new Ranking().getNomes();
        pontos = new Ranking().getPontos();

        labelSuperior = new JLabel("RANKING");
        labelSuperior.setFont(new Font("Courier New", Font.BOLD, 40));
        labelSuperior.setBackground(Color.DARK_GRAY); 
        labelSuperior.setForeground(Color.GREEN);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 12);
        labelSuperior.setBorder(border);
        labelSuperior.setOpaque(true);
        labelSuperior.setHorizontalAlignment(JLabel.CENTER);

        panelCentral = new JPanel(new GridLayout(nomes.size(), 2, 10, 10));
        panelCentral.setBackground(Color.DARK_GRAY);
        
                for (int i = 0; i < nomes.size(); i++) {
            JLabel nomeLabel = new JLabel(nomes.get(i));
            nomeLabel.setFont(new Font("Courier New", Font.BOLD, 25));
            nomeLabel.setForeground(Color.GREEN);
            nomeLabel.setHorizontalAlignment(JLabel.LEFT);

            JLabel pontosLabel = new JLabel(pontos.get(i) + " pts");
            pontosLabel.setFont(new Font("Courier New", Font.ITALIC, 25));
            pontosLabel.setForeground(Color.WHITE);
            pontosLabel.setHorizontalAlignment(JLabel.RIGHT);

            panelCentral.add(nomeLabel);
            panelCentral.add(pontosLabel);
        }

        JScrollPane scrollPane = new JScrollPane(panelCentral);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(labelSuperior, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        pack(); // Ajusta o tamanho da janela para que todos os componentes sejam exibidos adequadamente
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setVisible(true);
    }
}