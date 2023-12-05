package campominado.celula;

//Em campominado.celula.Celula
public abstract class Celula {
 private boolean validado; // Indica se a célula foi revelada ou não

 // Construtor da classe
 public Celula() {
     this.validado = false; // Por padrão, a célula não está validada (não revelada)
 }

 // Getter para o estado de validação da célula
 public boolean getValidado() {
     return validado;
 }

 // Setter para o estado de validação da célula
 public void setValidado(boolean validado) {
     this.validado = validado;
 }

 // Método para verificar se a célula é uma bomba
//Método para verificar se a célula é uma bomba
 public abstract boolean isBomba();

 // Método para obter o valor da célula
 public abstract String getValor();

 // Método para definir o valor da célula
 public abstract void setValor(String valor);

 // Método para criar uma cópia da célula
 public abstract Celula criarCopia();
}
