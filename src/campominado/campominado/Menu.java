package campominado;

import javax.swing.*;

import tabuleiros.Tabuleiro;
import tabuleiros.TabuleiroInterface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {

    private JFrame frame;

    public Menu() {
        frame = new JFrame();
        frame.setBounds(100, 100, 300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JLabel lblNewLabel = new JLabel("Campo Minado");
        lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.getContentPane().add(lblNewLabel, BorderLayout.NORTH);

        // Adiciona um painel para os botões
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridLayout(3, 1, 0, 10)); // 3 linhas, 1 coluna, espaçamento vertical de 10

        // Botão para o modo Fácil
        JButton btnFacil = new JButton("Fácil");
        btnFacil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iniciarJogo(ModoJogo.FACIL);
            }
        });
        panel.add(btnFacil);

        // Botão para o modo Médio
        JButton btnMedio = new JButton("Médio");
        btnMedio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iniciarJogo(ModoJogo.MEDIO);
            }
        });
        panel.add(btnMedio);

        // Botão para o modo Difícil
        JButton btnDificil = new JButton("Difícil");
        btnDificil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iniciarJogo(ModoJogo.DIFICIL);
            }
        });
        panel.add(btnDificil);

        // Exibe a janela
        frame.setVisible(true);
    }

    // Método para iniciar o jogo com base no modo selecionado
    private void iniciarJogo(ModoJogo modo) {
    	TabuleiroInterface tabuleiro;
    	
    	switch (modo) {
    	    case FACIL:
    	        tabuleiro = new Tabuleiro(1);
    	        break;
    	    case MEDIO:
    	        tabuleiro = new Tabuleiro(2);
    	        break;
    	    case DIFICIL:
    	        tabuleiro = new Tabuleiro(3);
    	        break;
    	    default:
    	        throw new IllegalArgumentException("Modo de jogo inválido");
    	}

    	// Agora, você pode iniciar a interface gráfica ou lógica do jogo com o tabuleiro criado

    }

    // Enum para representar os modos de jogo
    public enum ModoJogo {
        FACIL, MEDIO, DIFICIL
    }
}

