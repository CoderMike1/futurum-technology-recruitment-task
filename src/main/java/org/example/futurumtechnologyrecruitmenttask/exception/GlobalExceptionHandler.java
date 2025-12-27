package org.example.futurumtechnologyrecruitmenttask.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<FieldError>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){

        List<FieldError> errorList = e.getBindingResult().getFieldErrors().stream().map(er-> new FieldError(er.getField(),er.getDefaultMessage())).toList();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorList);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e){

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));

    }


}
