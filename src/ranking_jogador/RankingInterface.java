package ranking_jogador;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RankingInterface extends JFrame {

    private JLabel labelSuperior;
    private JPanel panelCentral;
    private ArrayList<String> nomes;
    private ArrayList<String> pontos;

    public RankingInterface() {
        setTitle("Ranking");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(420, 600));
        setResizable(false);

        nomes = new Ranking().getNomes();
        pontos = new Ranking().getPontos();

        labelSuperior = new JLabel("RANKING");
        labelSuperior.setFont(new Font("Arial", Font.BOLD, 40));
        labelSuperior.setHorizontalAlignment(JLabel.CENTER);

        panelCentral = new JPanel(new GridLayout(nomes.size(), 2, 10, 10));
        panelCentral.setBackground(Color.WHITE);

        for (int i = 0; i < nomes.size(); i++) {
            JLabel nomeLabel = new JLabel(nomes.get(i));
            nomeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
            nomeLabel.setHorizontalAlignment(JLabel.LEFT);

            JLabel pontosLabel = new JLabel(pontos.get(i) + " pts");
            pontosLabel.setFont(new Font("Arial", Font.PLAIN, 20));
            pontosLabel.setHorizontalAlignment(JLabel.RIGHT);

            panelCentral.add(nomeLabel);
            panelCentral.add(pontosLabel);
        }

        JScrollPane scrollPane = new JScrollPane(panelCentral);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(labelSuperior, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setVisible(true);
    }

}