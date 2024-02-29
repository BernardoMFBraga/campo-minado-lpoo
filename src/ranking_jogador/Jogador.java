package ranking_jogador;

public class Jogador {

    private String nome;
    private int pontuacao;

    public Jogador(String nome, int pontuacao){
        
        this.nome = nome;
        this.pontuacao = pontuacao;
    }

    public Jogador(String nome){
        this.nome = nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public int getPontuacao() {
        return pontuacao;
    }

}