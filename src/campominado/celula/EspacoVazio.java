package campominado.celula;
//Em campominado.celula.EspacoVazio
public class EspacoVazio extends Celula {
 private final String valor = " ";

 // Getter para o valor da célula
 public String getValor() {
     return valor;
 }

 // Método para verificar se a célula é uma bomba
 @Override
 public boolean isBomba() {
     return false;
 }

 // Método para criar uma cópia da célula
 @Override
 public Celula criarCopia() {
     EspacoVazio copia = new EspacoVazio();
     copia.setValidado(this.getValidado());
     return copia;
 }
 @Override
 public void setValor(String valor) {
     // Pode ser implementado se necessário, mas pode ser vazio, já que o valor de um espaço vazio pode ser dinâmico
 }
}
