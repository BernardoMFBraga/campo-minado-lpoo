package exceptions;

//exceção lançada quando uma tentativa é feita para abrir uma célula que foi marcada com uma bandeira. 
public class CelulaComBandeiraException extends RuntimeException {
    public CelulaComBandeiraException(String mensagem) {
        super(mensagem);
    }
}
