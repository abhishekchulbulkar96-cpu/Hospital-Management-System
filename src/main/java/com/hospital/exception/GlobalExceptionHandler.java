package com.hospital.exception;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 🔴 Handle custom exception
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String,String>> handleGeneric1(Exception ex) {

	    ex.printStackTrace();   // ADD THIS

	    Map<String,String> response = new HashMap<>();
	    response.put("status", "error");
	    response.put("message", ex.getMessage());

	    return new ResponseEntity<>(response,
	            HttpStatus.INTERNAL_SERVER_ERROR);
	}

    
}