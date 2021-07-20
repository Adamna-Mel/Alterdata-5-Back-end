package br.com.alterdata.pack.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

package br.com.alterdata.pack.exception.ErrorMessage;

@ControllerAdvice
public class ExceptionHandler{

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<?> handlerNotFoundException(NotFoundException exception){
		
		ErrorMessage errorMessage = new ErrorMessage(
				"Not Found",
				404,
				exception.getMessage());	
		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ResourceBadRequestException.class)
	public ResponseEntity<?> handlerResourceBadRequestException(ResourceBadRequestException exception){
		ErrorMessage errorMessage = new ErrorMessage(
				"Bad Request",
				400,
				exception.getMessage());
		
		return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceUnauthorizedException.class)
	public ResponseEntity<?> handlerResourceUnauthorizedException(ResourceBadRequestException exception){
		ErrorMessage errorMessage = new ErrorMessage(
				"Unauthorized",
				403,
				exception.getMessage());
		
		return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handlerInternalServerError(Exception exception){
		ErrorMessage errorMessage = new ErrorMessage(
				"Internal Server Error",
				500,
				exception.getMessage());
		
		return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
