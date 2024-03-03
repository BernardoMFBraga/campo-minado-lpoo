package sistema;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ranking_jogador.Jogador;
import tabuleiros.Tabuleiro;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModosDeJogo {
    private JFrame frameMultiplayer;
    private Jogador jogador;
    private boolean modoMalucoSelecionado;
    private double nivelMaluquice;

    public ModosDeJogo(Jogador jogador) {
        frameMultiplayer = new JFrame();
        frameMultiplayer.setBounds(100, 100, 600, 500);
        frameMultiplayer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        frameMultiplayer.getContentPane().setLayout(new BorderLayout());

        this.jogador = jogador;
        this.modoMalucoSelecionado = false; 

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - frameMultiplayer.getWidth()) / 2;
        int y = (screenSize.height - frameMultiplayer.getHeight()) / 2;
        frameMultiplayer.setLocation(x, y);
        frameMultiplayer.getContentPane().setLayout(new BorderLayout());

        JLabel lblJogar = new JLabel("Escolha seu Modo de Jogo");
        lblJogar.setFont(new Font("Courier New", Font.BOLD, 35));
        lblJogar.setBackground(Color.DARK_GRAY);
        lblJogar.setForeground(Color.GREEN);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 12);
        lblJogar.setBorder(border);
        lblJogar.setOpaque(true);
        lblJogar.setHorizontalAlignment(SwingConstants.CENTER);
        frameMultiplayer.getContentPane().add(lblJogar, BorderLayout.NORTH);

        JPanel panelSelecaoJogador = new JPanel();
        frameMultiplayer.getContentPane().add(panelSelecaoJogador, BorderLayout.CENTER);
        panelSelecaoJogador.setLayout(new GridLayout(5, 1, 0, 20)); 

        JRadioButton radioUmJogador = new JRadioButton("Um Jogador", true);
        radioUmJogador.setFont(new Font("Courier New", Font.ITALIC, 30));
        radioUmJogador.setForeground(Color.GREEN);
        radioUmJogador.setBackground(Color.DARK_GRAY);
        radioUmJogador.setFocusPainted(false);
        panelSelecaoJogador.add(radioUmJogador);

        JRadioButton radioDoisJogadores = new JRadioButton("Dois Jogadores");
        radioDoisJogadores.setFont(new Font("Courier New", Font.ITALIC, 30));
        radioDoisJogadores.setForeground(Color.GREEN);
        radioDoisJogadores.setBackground(Color.DARK_GRAY);
        radioDoisJogadores.setFocusPainted(false);
        panelSelecaoJogador.add(radioDoisJogadores);

        JCheckBox checkModoMaluco = new JCheckBox("Modo Maluco");
        checkModoMaluco.setFont(new Font("Courier New", Font.ITALIC, 30));
        checkModoMaluco.setForeground(Color.GREEN);
        checkModoMaluco.setBackground(Color.DARK_GRAY);
        checkModoMaluco.setFocusPainted(false);
        panelSelecaoJogador.add(checkModoMaluco);

        ButtonGroup group = new ButtonGroup();
        group.add(radioUmJogador);
        group.add(radioDoisJogadores);

        JPanel panelBotoes = new JPanel(new GridLayout(1, 3));
        panelBotoes.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        frameMultiplayer.getContentPane().add(panelBotoes, BorderLayout.SOUTH);

        JButton btnFacil = new JButton("Fácil");
        btnFacil.setFont(new Font("Courier New", Font.ITALIC, 30));
        btnFacil.setForeground(Color.GREEN);
        btnFacil.setBackground(Color.DARK_GRAY);
        btnFacil.setFocusPainted(false);
        panelBotoes.add(btnFacil);

        JButton btnMedio = new JButton("Médio");
        btnMedio.setFont(new Font("Courier New", Font.ITALIC, 30));
        btnMedio.setForeground(Color.GREEN);
        btnMedio.setBackground(Color.DARK_GRAY);
        btnMedio.setFocusPainted(false);
        panelBotoes.add(btnMedio);

        JButton btnDificil = new JButton("Difícil");
        btnDificil.setFont(new Font("Courier New", Font.ITALIC, 30));
        btnDificil.setForeground(Color.GREEN);
        btnDificil.setBackground(Color.DARK_GRAY);
        btnDificil.setFocusPainted(false);
        panelBotoes.add(btnDificil);

        JSlider sliderNivelMaluquice = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        sliderNivelMaluquice.setMajorTickSpacing(10);
        sliderNivelMaluquice.setMinorTickSpacing(1);
        sliderNivelMaluquice.setPaintTicks(true);
        sliderNivelMaluquice.setPaintLabels(true);
        sliderNivelMaluquice.setForeground(Color.GREEN);
        sliderNivelMaluquice.setBackground(Color.DARK_GRAY);
        panelSelecaoJogador.add(sliderNivelMaluquice);

        sliderNivelMaluquice.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                nivelMaluquice = sliderNivelMaluquice.getValue() / 100.0; // Convertendo para um valor entre 0 e 1
            }
        });

        btnFacil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iniciarTabuleiro(1, radioDoisJogadores.isSelected());
            }
        });

        btnMedio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iniciarTabuleiro(2, radioDoisJogadores.isSelected());
            }
        });

        btnDificil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iniciarTabuleiro(3, radioDoisJogadores.isSelected());
            }
        });

        checkModoMaluco.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modoMalucoSelecionado = checkModoMaluco.isSelected();
                nivelMaluquice = 0.5;
            }
        });

        frameMultiplayer.setVisible(true);
    }

    private void iniciarTabuleiro(int dificuldade, boolean doisJogadores) {
        Tabuleiro tabuleiro = new Tabuleiro(dificuldade, jogador, doisJogadores, modoMalucoSelecionado, nivelMaluquice);
        frameMultiplayer.dispose();
    }
}