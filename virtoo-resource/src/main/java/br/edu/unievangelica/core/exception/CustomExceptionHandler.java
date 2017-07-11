package br.edu.unievangelica.core.exception;


import br.edu.unievangelica.core.controller.JsonResponse;
import br.edu.unievangelica.core.controller.JsonResponseFactory;
import br.edu.unievangelica.core.controller.JsonResponseService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @Autowired
    protected JsonResponseService jsonResponseService;

    @Autowired
    protected JsonResponseFactory jsonResponseFactory;

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException( RuntimeException e){
        jsonResponseService.clearMessages();
        jsonResponseService.addError(e.getMessage());
        JsonResponse response = jsonResponseFactory.create(null, jsonResponseService.getMessageList());
        return new ResponseEntity<>(response, jsonResponseService.getHttpStatus());
    }

    @ExceptionHandler(CustomNotFoundException.class)
    public ResponseEntity<?> handleNotFoundException( RuntimeException e){
        jsonResponseService.clearMessages();
        jsonResponseService.addError(e.getMessage());
        jsonResponseService.setHttpStatus(HttpStatus.NOT_FOUND);
        JsonResponse response = jsonResponseFactory.create(null, jsonResponseService.getMessageList());
        return new ResponseEntity<>(response, jsonResponseService.getHttpStatus());
    }


}
