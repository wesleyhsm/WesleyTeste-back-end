package com.funcionario.services.exceptions;

public class FuncionarioException extends RuntimeException {

	private static final long serialVersionUID = 593623720673044742L;

	public FuncionarioException(String mensagem) {
		super(mensagem);
	}
	
	public FuncionarioException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
