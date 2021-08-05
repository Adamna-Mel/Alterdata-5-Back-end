package br.com.alterdata.pack.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE)
public class UnsupportedMediaTypeException extends RuntimeException {

	public UnsupportedMediaTypeException() {
		super("Tipo de Mídia não suportada");
	}

	public UnsupportedMediaTypeException(String mensagem) {
		super(mensagem);
	}

}