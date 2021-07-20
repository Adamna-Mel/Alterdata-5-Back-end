package br.com.alterdata.pack.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

<<<<<<< HEAD:src/main/java/br/com/alterdata/pack/exception/APIExceptionHandler.java

=======
>>>>>>> 65aea55daca4c8bc1e88da6c5a75fcc5a057a5d4:pack/src/main/java/br/com/alterdata/pack/exception/ExceptionHandler.java
@ControllerAdvice
public class APIExceptionHandler{

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<?> handlerNotFoundException(NotFoundException exception){
		
		ErrorMessage errorMessage = new ErrorMessage(
				"Not Found",
				404,
				exception.getMessage());	
		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<?> handlerBadRequestException(BadRequestException exception){
		ErrorMessage errorMessage = new ErrorMessage(
				"Bad Request",
				400,
				exception.getMessage());
		
		return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<?> handlerUnauthorizedException(BadRequestException exception){
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
