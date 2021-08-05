package br.com.alterdata.pack.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler{

	@Override
	protected ResponseEntity<Object> handleBindException(
			BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> erros = ex.getBindingResult()
		.getAllErrors()
		.stream()
		.map(err -> err.getDefaultMessage())
		.collect(Collectors.toList());

		return new ResponseEntity<>(erros, HttpStatus.BAD_REQUEST);	
	}

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
	public ResponseEntity<?> handlerUnauthorizedException(UnauthorizedException exception){
		ErrorMessage errorMessage = new ErrorMessage(
				"Unauthorized",
				401,
				exception.getMessage());
		
		return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(UnsupportedMediaTypeException.class)
	public ResponseEntity<?> handlerUnsupportedMediaTypeException(UnsupportedMediaTypeException exception){
		ErrorMessage errorMessage = new ErrorMessage(
				"Unsupported Media Type",
				HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),
				exception.getMessage());
		
		return new ResponseEntity<>(errorMessage, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	@ExceptionHandler(BadGatewayException.class)
	public ResponseEntity<?> handlerBadGatewayException(BadGatewayException exception){
		ErrorMessage errorMessage = new ErrorMessage(
				"Bad Gateway",
				HttpStatus.BAD_GATEWAY.value(),
				exception.getMessage());
		
		return new ResponseEntity<>(errorMessage, HttpStatus.BAD_GATEWAY);
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
