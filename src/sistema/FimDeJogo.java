package sistema;

import javax.swing.*;
import ranking_jogador.Jogador;
import ranking_jogador.Ranking;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FimDeJogo extends JDialog {
    private int pontuacao;
    private Jogador jogador;

    public FimDeJogo(JFrame parent, int pontuacao, Jogador jogador) {
        super(parent, "Fim de Jogo", true);
        setLayout(new BorderLayout());
        this.pontuacao = pontuacao;
        this.jogador = jogador;

        JLabel mensagemLabel = new JLabel("Fim de Jogo! Pontuação: " + pontuacao);
        mensagemLabel.setHorizontalAlignment(JLabel.CENTER);
        add(mensagemLabel, BorderLayout.CENTER);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a janela de diálogo
                jogador.setPontuacao(pontuacao);
                Ranking ranking = new Ranking(jogador); 
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(300, 150);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}