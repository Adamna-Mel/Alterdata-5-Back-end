package br.com.alterdata.pack.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_GATEWAY)
public class BadGatewayException extends RuntimeException {

	public BadGatewayException() {
		super("Recurso externo não pode ser acessado");
	}

	public BadGatewayException(String mensagem) {
		super(mensagem);
	}

}
