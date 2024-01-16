package campominado.celula;

//Em campominado.celula.Bomba
public class Bomba extends Celula {
 private final String valor = "*";

 // Getter para o valor da célula
 public String getValor() {
     return valor;
 }

 // Método para verificar se a célula é uma bomba
 @Override
 public boolean isBomba() {
     return true;
 }

 // Método para criar uma cópia da célula
 @Override
 public Celula criarCopia() {
     Bomba copia = new Bomba();
     copia.setValidado(this.getValidado());
     return copia;
 }
 @Override
 public void setValor(String valor) {
     // Pode ser implementado se necessário, mas pode ser vazio, já que o valor de uma bomba é fixo
 }
 
}

