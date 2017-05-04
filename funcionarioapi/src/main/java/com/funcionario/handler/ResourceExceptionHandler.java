package com.funcionario.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.funcionario.domain.ErroDetalhado;
import com.funcionario.services.exceptions.FuncionarioException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(FuncionarioException.class)
	public ResponseEntity<ErroDetalhado> handlerFuncionarioException(FuncionarioException e, HttpServletRequest request){
		
		ErroDetalhado erro = new ErroDetalhado();
		erro.setTitulo("O funcionario não encontrado.");
		erro.setStatus(404L);
		erro.setMensagemDesenvolvedor("http://erros.funcionario.com.br/404");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
}
