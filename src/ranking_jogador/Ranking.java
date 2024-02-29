package ranking_jogador;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;


public class Ranking {

    private FileInputStream arquivo; //o arquivo que sera lido
    private InputStreamReader input;
    private BufferedReader leitor;

    private FileOutputStream arquivoEscrever;//arquivo onde vai se escrever
    private PrintWriter escritor; // quem escreve no arquivo

    private ArrayList<String> nomes;
    private ArrayList<String> pontos;

    public Ranking(Jogador jogador){
        try{
    
        this.arquivo = new FileInputStream("ranking.txt"); //o arquivo que sera lido
        this.input = new InputStreamReader(arquivo); //quem vai ler o arquivo
        this.leitor = new BufferedReader(input); 

        String linha;
            ArrayList<String> nomes = new ArrayList<>();
            ArrayList<String> pontos = new ArrayList<>();

            do{
                linha = leitor.readLine();
                if(linha != null){
                    String [] dadosBrutos = linha.split(";");
                    
                    for(int i = 0; i < dadosBrutos.length; i++){
                        if(i%2 == 0){
                            nomes.add(dadosBrutos[i]);
                        }
                        if(i%2 == 1){
                            pontos.add(dadosBrutos[i]);
                        }
                    }
                }
            }while(linha != null);

            this.nomes = nomes;
            this.pontos = pontos;
            leitor.close();
        }catch(Exception e){
            System.out.println("Falha na leitura");
        }

        try{

            this.arquivoEscrever = new FileOutputStream("ranking.txt");
            this.escritor = new PrintWriter(arquivoEscrever);

            Jogador[] jogadores = new Jogador[nomes.size() + 1];
            for(int i = 0; i < nomes.size() ; i++)
            {
                jogadores[i] = new Jogador(nomes.get(i), Integer.parseInt(pontos.get(i)));
            }
            jogadores[nomes.size()] = jogador; 

            //ordenação decrescente
            Arrays.sort(jogadores, Comparator.comparingInt(Jogador::getPontuacao).reversed());

            // Gravação dos dados atualizados no arquivo:
            for (Jogador jogadorAtualizado : jogadores) {
                escritor.println(jogadorAtualizado.getNome() + ";" + jogadorAtualizado.getPontuacao());
            }
            escritor.close();

        }catch(Exception e){
            System.out.println("erro na gravação");
        }


        System.out.println("ranking atualizado com sucesso");
    }

    public Ranking(){
        
        try{
            this.arquivo = new FileInputStream("ranking.txt"); //o arquivo que sera lido
            this.input = new InputStreamReader(arquivo); //quem vai ler o arquivo
            this.leitor = new BufferedReader(input); 

            String linha;
                ArrayList<String> nomes = new ArrayList<>();
                ArrayList<String> pontos = new ArrayList<>();

                do{
                    linha = leitor.readLine();
                    if(linha != null){
                        String [] dadosBrutos = linha.split(";");
                        
                        for(int i = 0; i < dadosBrutos.length; i++){
                            if(i%2 == 0){
                                nomes.add(dadosBrutos[i]);
                            }
                            if(i%2 == 1){
                                pontos.add(dadosBrutos[i]);
                            }
                        }
                    }
                }while(linha != null);

                this.nomes = nomes;
                this.pontos = pontos;
                leitor.close();
            }catch(Exception e){
                System.out.println("Falha na leitura");
            }

    }

    public ArrayList<String> getNomes() {
        return nomes;
    }

    public ArrayList<String> getPontos() {
        return pontos;
    }

}