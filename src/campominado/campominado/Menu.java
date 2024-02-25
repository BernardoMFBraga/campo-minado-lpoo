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
                abrirModosDeJogo();
            }
        });
        panel.add(btnJogar);

        // Botão Ranking
        JButton btnRanking = new JButton("Ranking");
        btnRanking.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Adicione aqui o código para abrir a tela de ranking, se aplicável
                JOptionPane.showMessageDialog(frame, "Em construção...");
            }
        });
        panel.add(btnRanking);

        // Exibe a janela
        frame.setVisible(true);
    }

    // Método para abrir a tela de modos de jogo
    private void abrirModosDeJogo() {
        // Criando uma nova janela para os modos de jogo
        JFrame frameModosDeJogo = new JFrame();
        frameModosDeJogo.setBounds(100, 100, 300, 200);
        frameModosDeJogo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fecha apenas a janela dos modos de jogo
        frameModosDeJogo.getContentPane().setLayout(new BorderLayout());

        // Título "Dificuldade"
        JLabel lblDificuldade = new JLabel("Dificuldade");
        lblDificuldade.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
        lblDificuldade.setHorizontalAlignment(SwingConstants.CENTER);
        frameModosDeJogo.getContentPane().add(lblDificuldade, BorderLayout.NORTH);

        // Painel para os botões de dificuldade
        JPanel panelDificuldade = new JPanel();
        frameModosDeJogo.getContentPane().add(panelDificuldade, BorderLayout.CENTER);
        panelDificuldade.setLayout(new GridLayout(3, 1, 0, 10)); // 3 linhas, 1 coluna, espaçamento vertical de 10

        // Botão para o modo Fácil
        JButton btnFacil = new JButton("Fácil");
        btnFacil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frameModosDeJogo.dispose(); // Fecha a janela dos modos de jogo
                iniciarJogo(ModoJogo.FACIL); // Inicia o jogo no modo Fácil
            }
        });
        panelDificuldade.add(btnFacil);

        // Botão para o modo Médio
        JButton btnMedio = new JButton("Médio");
        btnMedio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frameModosDeJogo.dispose(); // Fecha a janela dos modos de jogo
                iniciarJogo(ModoJogo.MEDIO); // Inicia o jogo no modo Médio
            }
        });
        panelDificuldade.add(btnMedio);

        // Botão para o modo Difícil
        JButton btnDificil = new JButton("Difícil");
        btnDificil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frameModosDeJogo.dispose(); // Fecha a janela dos modos de jogo
                iniciarJogo(ModoJogo.DIFICIL); // Inicia o jogo no modo Difícil
            }
        });
        panelDificuldade.add(btnDificil);

        // Exibe a janela dos modos de jogo
        frameModosDeJogo.setVisible(true);
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
    }
    public enum ModoJogo {
        FACIL, MEDIO, DIFICIL
    }
}

