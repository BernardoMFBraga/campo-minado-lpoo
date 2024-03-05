package sistema;

import javax.swing.*;
import javax.swing.border.Border;

import ranking_jogador.Jogador;
import ranking_jogador.Ranking;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FimDeJogo extends JDialog {
    private int pontuacao;
    private Jogador jogador;

    public FimDeJogo(JFrame parent, int pontuacao, Jogador jogador) {
        super(parent, "Fim de Jogo", true); // Configurações do diálogo
        setLayout(new BorderLayout());
        this.pontuacao = pontuacao;
        this.jogador = jogador;

        JLabel mensagemLabel = new JLabel("Fim de Jogo! Pontuação: " + pontuacao);
        mensagemLabel.setFont(new Font("Courier New", Font.BOLD, 20));
        mensagemLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mensagemLabel.setBackground(Color.DARK_GRAY); 
        mensagemLabel.setForeground(Color.GREEN);
        mensagemLabel.setHorizontalAlignment(JLabel.CENTER);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 6);
        mensagemLabel.setBorder(border);
        
        add(mensagemLabel, BorderLayout.CENTER);
        mensagemLabel.setOpaque(true);

        JButton okButton = new JButton("OK");
        okButton.setFont(new Font("Courier New", Font.BOLD, 20));
        okButton.setHorizontalAlignment(SwingConstants.CENTER);
        okButton.setBackground(Color.DARK_GRAY); 
        okButton.setForeground(Color.GREEN);
        okButton.setFocusPainted(false); // Remove a borda de foco
        mensagemLabel.setOpaque(true);
        okButton.setHorizontalAlignment(JLabel.CENTER);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a janela de diálogo
                jogador.setPontuacao(pontuacao);
                Ranking ranking = new Ranking(jogador); // Atualiza o ranking com o jogador atual 
            }
        });
        
        okButton.setPreferredSize(new Dimension(150, 50 ));


        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0; // Preenchimento horizontal completo
        buttonPanel.add(okButton, gbc);

        add(buttonPanel, BorderLayout.SOUTH);

        setSize(400, 200);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}