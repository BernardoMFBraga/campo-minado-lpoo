package sistema;

import javax.swing.*;
import javax.swing.border.Border;

import ranking_jogador.Jogador;
import tabuleiros.Tabuleiro;
import tabuleiros.TabuleiroInterface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ModosDeJogo {
    private JFrame frameModosDeJogo;
    private Jogador jogador;
    public ModosDeJogo(Jogador jogador) {
        frameModosDeJogo = new JFrame();
        frameModosDeJogo.setBounds(100, 100, 300, 200);
        frameModosDeJogo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fecha apenas a janela dos modos de jogo
        frameModosDeJogo.getContentPane().setLayout(new BorderLayout());
        
        this.jogador = jogador;
        
        frameModosDeJogo.setBounds(100, 100, 300, 200);
        frameModosDeJogo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameModosDeJogo.getContentPane().setLayout(new BorderLayout());
        
        // Calcula o tamanho da tela
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Define o tamanho da janela (largura x altura) como 400x300
        frameModosDeJogo.setSize(600, 500);

        // Calcula a posição para centralizar a janela na tela
        int x = (screenSize.width - frameModosDeJogo.getWidth()) / 2;
        int y = (screenSize.height - frameModosDeJogo.getHeight()) / 2;

        // Define a posição da janela
        frameModosDeJogo.setLocation(x, y);

        // Define o layout da janela
        frameModosDeJogo.getContentPane().setLayout(new BorderLayout());
        
        // Título "Dificuldade"
        JLabel lblDificuldade = new JLabel("Dificuldade");
        lblDificuldade.setFont(new Font("Courier New", Font.BOLD, 64));
        lblDificuldade.setBackground(Color.DARK_GRAY); 
        lblDificuldade.setForeground(Color.GREEN);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 12);
        lblDificuldade.setBorder(border);
        lblDificuldade.setOpaque(true); // Definindo opacidade do fundo como verdadeiro
        frameModosDeJogo.getContentPane().add(lblDificuldade, BorderLayout.NORTH);
        lblDificuldade.setHorizontalAlignment(SwingConstants.CENTER);
        frameModosDeJogo.getContentPane().add(lblDificuldade, BorderLayout.NORTH);

        // Painel para os botões de dificuldade
        JPanel panelDificuldade = new JPanel();
        frameModosDeJogo.getContentPane().add(panelDificuldade, BorderLayout.CENTER);
        panelDificuldade.setLayout(new GridLayout(3, 1, 0, 0)); // 3 linhas, 1 coluna, espaçamento vertical de 10

        // Botão para o modo Fácil
        JButton btnFacil = new JButton("Fácil");
        btnFacil.setFont(new Font("Courier New", Font.ITALIC, 40));
        btnFacil.setForeground(Color.GREEN);
        btnFacil.setBackground(Color.DARK_GRAY); 
        btnFacil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iniciarJogo(ModoJogo.FACIL); // Inicia o jogo no modo Fácil
            }
        });
        panelDificuldade.add(btnFacil);

        // Botão para o modo Médio
        JButton btnMedio = new JButton("Médio");
        btnMedio.setFont(new Font("Courier New", Font.ITALIC, 40));
        btnMedio.setForeground(Color.GREEN);
        btnMedio.setBackground(Color.DARK_GRAY);
        btnMedio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iniciarJogo(ModoJogo.MEDIO); // Inicia o jogo no modo Médio
            }
        });
        panelDificuldade.add(btnMedio);

        // Botão para o modo Difícil
        JButton btnDificil = new JButton("Difícil");
        btnDificil.setFont(new Font("Courier New", Font.ITALIC, 40));
        btnDificil.setForeground(Color.GREEN);
        btnDificil.setBackground(Color.DARK_GRAY);
        btnDificil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
                tabuleiro = new Tabuleiro(1, this.jogador);
                break;
            case MEDIO:
                tabuleiro = new Tabuleiro(2, this.jogador);
                break;
            case DIFICIL:
                tabuleiro = new Tabuleiro(3, this.jogador);
                break;
            default:
                throw new IllegalArgumentException("Modo de jogo inválido");
        }

        frameModosDeJogo.dispose(); // Fecha a janela dos modos de jogo
    }

    // Enum para representar os modos de jogo
    public enum ModoJogo {
        FACIL, MEDIO, DIFICIL
    }
}
