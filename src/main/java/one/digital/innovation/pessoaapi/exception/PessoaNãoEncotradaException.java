package one.digital.innovation.pessoaapi.exception;

public class PessoaNãoEncotradaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PessoaNãoEncotradaException(String mensagem) {
		super(mensagem);
	}
}
