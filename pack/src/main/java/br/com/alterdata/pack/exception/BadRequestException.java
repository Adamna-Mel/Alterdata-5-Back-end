package br.com.alterdata.pack.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ResourceBadRequestException extends RuntimeException{
	
	public BadRequestException(){
		super("Recurso fora dos parâmetros da API");
	}
	
	public BadRequestException(String mensagem){
		super(mensagem);
	}
}