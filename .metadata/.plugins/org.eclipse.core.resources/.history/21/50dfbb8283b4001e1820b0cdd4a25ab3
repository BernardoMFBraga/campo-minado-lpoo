package campominado;

import java.util.Scanner;
import campominado.celula.Celula;
import campominado.celula.EspacoVazio;

public class Tabuleiro {
    private Scanner teclado = new Scanner(System.in);
    private int numeroBombas;
    protected Celula[][] jogoReal;
    protected Celula[][] tela;
    private int ganhou = 0;

    public Tabuleiro() {
        this.numeroBombas = 10;
        this.jogoReal = new Celula[8][10];
        this.tela = new Celula[8][10];

        iniciaTabuleiro();
    }

    private void preencherMatriz(Celula[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                if (matriz[i][j] == null) {
                    matriz[i][j] = new EspacoVazio();
                } else {
                    matriz[i][j] = matriz[i][j].criarCopia();
                }
            }
        }
    }

    public void iniciaTabuleiro() {
        preencherMatriz(tela);
        preencherMatriz(jogoReal);
        distribuirBombas();
        menuJogo();
    }


    private void distribuirBombas() {
        for (int i = 0; i < numeroBombas; i++) {
            int x = (int) (jogoReal.length * Math.random());
            int y = (int) (jogoReal[0].length * Math.random());

            // Verifique se o valor da célula não é null antes de comparar
            if (jogoReal[x][y].getValor() == null || !jogoReal[x][y].getValor().equals("*")) {
                jogoReal[x][y].setValor("*");
            } else {
                // Se o valor já for "*", escolha novas coordenadas
                i--; // Decrementa i para tentar novamente na próxima iteração
            }
        }
    }

    public void mostraJogo() {
        System.out.print("     ");
        // Loop para colocar os números da coordenada x no topo da matriz.
        for (int i = 0; i < tela[0].length; i++) {
            if (i < 10) {
                System.out.print("0" + i + "  ");
            } else {
                System.out.print(i + "  ");
            }
        }
        System.out.println("\n   ---------------------------------------------------------------------------------------------");
        // Loop para colocar os números da coordenada y no canto esquerdo da matriz.
        for (int i = 0; i < tela.length; i++) {
            if (i < 10) {
                System.out.print("0" + i + " | ");
            } else {
                System.out.print(i + " | ");
            }
            // Loop que preenche a matriz para o usuário com '#'.
            for (int j = 0; j < tela[0].length; j++) {
                // Modificação para ocultar os caracteres '#' quando uma célula é aberta ou marcada
                if (tela[i][j].getValidado()) {
                    if (tela[i][j].getValor().equals("F")) {
                        System.out.print("F # ");
                    } else {
                        System.out.print(tela[i][j].getValor() + " # ");
                    }
                } else {
                    System.out.print("# # ");
                }
            }
            System.out.println();
        }
    }

    public void vocePerdeu() {
        System.out.println("");
        System.out.println("Você PERDEU!");
        abrirCampo();
        mostraJogo();
    }

    public void voceGanhou() {
        System.out.println("");
        System.out.println("Você GANHOU!");
        mostraJogo();
    }

    public int menuLinha() {
        int linha;

        System.out.println("Digite a linha:");
        try {
            linha = Integer.parseInt(teclado.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }

        return linha;
    }

    public int menuColuna() {
        int coluna;

        System.out.println("Digite a coluna:");
        try {
            coluna = Integer.parseInt(teclado.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }

        return coluna;
    }

    public boolean validaCampos(int linha, int coluna) {
        if (linha == -1 && coluna == -1) {
            System.out.println("Campos inválidos");
            return false;
        }

        if (linha >= jogoReal.length || linha < 0) {
            System.out.println("Linha inexistente");
            return false;
        }

        if (coluna >= jogoReal[0].length || coluna < 0) {
            System.out.println("Coluna inexistente");
            return false;
        }

        if (jogoReal[linha][coluna].getValidado()) {
            System.out.println("Coordenada já usada");
            return false;
        }

        return true;
    }

    private void verificarBombas(int linha, int coluna) {
        if (jogoReal[linha][coluna].getValidado()) {
            return;
        }

        int bombas = 0;
        int minLinha = Math.max(0, linha - 1);
        int maxLinha = Math.min(jogoReal.length - 1, linha + 1);
        int minColuna = Math.max(0, coluna - 1);
        int maxColuna = Math.min(jogoReal[0].length - 1, coluna + 1);

        for (int i = minLinha; i <= maxLinha; i++) {
            for (int j = minColuna; j <= maxColuna; j++) {
                if (i == linha && j == coluna) {
                    continue;
                }

                if (jogoReal[i][j].isBomba()) {
                    bombas++;
                }
            }
        }

        tela[linha][coluna].setValor(String.valueOf(bombas));
        tela[linha][coluna].setValidado(true);
        jogoReal[linha][coluna].setValor(String.valueOf(bombas));
        jogoReal[linha][coluna].setValidado(true);
        ganhou++;

        if (bombas == 0) {
            for (int i = minLinha; i <= maxLinha; i++) {
                for (int j = minColuna; j <= maxColuna; j++) {
                    if (i == linha && j == coluna) {
                        continue;
                    }

                    verificarBombas(i, j);
                }
            }
        }
    }

    private void abrirCampo() {
        for (int x = 0; x < jogoReal.length; x++) {
            for (int y = 0; y < jogoReal[0].length; y++) {
                if (jogoReal[x][y].getValor().equals("*")) {
                    tela[x][y].setValor("*");
                    continue;
                }

                descobrirCampos(x, y);
            }
        }
    }

    public void descobrirCampos(int linha, int coluna) {
        if (jogoReal[linha][coluna].getValidado()) {
            return;
        }

        int bombas = 0;
        int minLinha;
        int maxLinha;
        int minColuna;
        int maxColuna;

        if (linha != 0 && linha != jogoReal.length - 1) {
            minLinha = linha - 1;
            maxLinha = linha + 1;
        } else if (linha == 0) {
            minLinha = linha;
            maxLinha = linha + 1;
        } else {
            minLinha = linha - 1;
            maxLinha = linha;
        }

        if (coluna != 0 && coluna != jogoReal[0].length - 1) {
            minColuna = coluna - 1;
            maxColuna = coluna + 1;
        } else if (coluna == 0) {
            minColuna = coluna;
            maxColuna = coluna + 1;
        } else {
            minColuna = coluna - 1;
            maxColuna = coluna;
        }

        for (int i = minLinha; i <= maxLinha; i++) {
            for (int j = minColuna; j <= maxColuna; j++) {
                if (jogoReal[i][j].getValor().equals("*")) {
                    bombas++;
                }
            }
        }

        tela[linha][coluna].setValor(String.valueOf(bombas));
        tela[linha][coluna].setValidado(true);
        jogoReal[linha][coluna].setValor(String.valueOf(bombas));
        jogoReal[linha][coluna].setValidado(true);
    }

    public void marcaCelula(int linha, int coluna) {
        if (!tela[linha][coluna].getValidado()) {
            tela[linha][coluna].setValor("F"); // Marcando a célula com bandeira
        } else {
            System.out.println("Essa célula já foi aberta. Não é possível marcar com bandeira.");
        }
    }

    public void removeMarca(int linha, int coluna) {
        if (!tela[linha][coluna].getValidado() && tela[linha][coluna] instanceof EspacoVazio) {
            tela[linha][coluna].setValor("#"); // Removendo a bandeira, restaurando o valor original
        } else {
            System.out.println("Essa célula não está marcada com bandeira ou já foi aberta.");
        }
    }

    public boolean celulaMarcadaComBandeira(int linha, int coluna) {
        return tela[linha][coluna].getValor().equals("F");
    }

    public void abrirCelula(int linha, int coluna) {
        if (celulaMarcadaComBandeira(linha, coluna)) {
            System.out.println("Remova a bandeira antes de abrir essa célula.");
        } else {
            if (!jogoReal[linha][coluna].getValidado()) {
                Celula celulaAtual = jogoReal[linha][coluna];
                if (celulaAtual.getValor().equals("*")) {
                    vocePerdeu();
                } else {
                    verificarBombas(linha, coluna);
                    if (ganhou == (jogoReal.length * jogoReal[0].length - numeroBombas)) {
                        voceGanhou();
                    }
                }
            } else {
                System.out.println("Essa célula já foi aberta.");
            }
        }
    }

    public void menuJogo() {
        try {
            int i = -1;

            while (i != 0) {
                mostraJogo();
                System.out.println("\n(1) Abrir célula / (2) Marcar com bandeira / (3) Remover bandeira / (0) Sair");
                int opcao = Integer.parseInt(teclado.nextLine());

                switch (opcao) {
                    case 1:
                        int linha = menuLinha();
                        int coluna = menuColuna();

                        if (validaCampos(linha, coluna)) {
                            abrirCelula(linha, coluna);

                            if (jogoReal[linha][coluna].getValor().equals("*")) {
                                vocePerdeu();
                                i = 0;
                            } else {
                                verificarBombas(linha, coluna);

                                if (ganhou == (jogoReal.length * jogoReal[0].length - numeroBombas)) {
                                    voceGanhou();
                                    i = 0;
                                }
                            }
                        } else {
                            System.out.println("Coordenada inválida ou já utilizada.");
                        }
                        break;
                    case 2:
                        linha = menuLinha();
                        coluna = menuColuna();
                        marcaCelula(linha, coluna);
                        break;
                    case 3:
                        linha = menuLinha();
                        coluna = menuColuna();
                        removeMarca(linha, coluna);
                        break;
                    case 0:
                        System.out.println("Saindo do jogo. Obrigado por jogar!");
                        i = 0; // Adicionado para corrigir o loop infinito
                        break;
                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}